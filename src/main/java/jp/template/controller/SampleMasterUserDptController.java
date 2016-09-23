package jp.template.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.template.component.ValidateComponent;
import jp.template.config.MessageResourcesConfig;
import jp.template.domain.Dpt;
import jp.template.domain.UserDptExtension;
import jp.template.form.SampleMasterUserDptForm;
import jp.template.mapper.DptMapper;
import jp.template.mapper.UserDptExtensionMapper;
import jp.template.mapper.UserDptMapper;
import jp.template.security.LoginUser;

/**
 * ユーザ・所属(UserDpt)マスタサンプル。
 * <UL>
 *   <LI>他のマスタ(UserDpt, User, Dpt)との結合サンプル</LI>
 * </UL>
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/sample/master/user/dpt")
public class SampleMasterUserDptController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private MessageSource message;
	
	/** 画面検索用。*/
	@Autowired
	private UserDptExtensionMapper userDptExtensionMapper;

	/** データ登録用。*/
	@Autowired
	private UserDptMapper userDptMapper;
	
	/** 部門マスタ存在チェック用*/
	@Autowired
	private DptMapper dptMapper;
	
	/** 入力検証コンポーネント*/
	@Autowired
	private ValidateComponent validate;

	/**
	 * マスタサンプルオープン時のイベント処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserDptForm}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/userdpt.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleMasterUserDptForm form, Model model, Locale locale) {
		
		// 初期検索。
		retrieve(form, model, locale);

		return "sample/master/userdpt";
	}
	
	/**
	 * 保存ボタン押下。
	 * <p>画面内容を全て更新する。</p>
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserDptForm}
	 * @param loginUser 認証情報
	 * @param result {@link BindingResult}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/userdpt.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doSave")
	public String save(@AuthenticationPrincipal LoginUser loginUser, @Validated SampleMasterUserDptForm form, BindingResult result, Model model, Locale locale) {

		customValidate(form, result, locale);
		if (result.hasErrors()) {
			return "sample/master/userdpt";
		}

		// 更新処理(delete -> insert)。
		for (UserDptExtension entity : form.getList()) {

			userDptMapper.deleteFromloginuserid(entity);
			entity.setDtupdate(Timestamp.valueOf(LocalDateTime.now())); // Time API -> Timestamp
			logger.debug(entity);
			userDptMapper.insert(entity);
		}
		

		// 保存完了メッセージ。
		model.addAttribute("info_message", message.getMessage("jp.template.global.info.save.completion.message", null, locale));
		
		// 再検索。
		retrieve(form, model, locale);
		
		return "sample/master/userdpt";
	}

	/**
	 * 検索処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserDptForm}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 */
	private void retrieve(SampleMasterUserDptForm form, Model model, Locale locale) {

		List<UserDptExtension> list = userDptExtensionMapper.selectUserAll();
		if (list.isEmpty()) {
			// 該当なし（0 件）のメッセージ。
			model.addAttribute("warning_message", message.getMessage("jp.template.global.warning.retrieved.condition.notfound.message", null, locale));
		}
		form.setList(list);
	}
	
	/**
	 * 当機能固有の検証。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserDptForm}
	 * @param result {@link BindingResult}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 */
	private void customValidate(SampleMasterUserDptForm form,BindingResult result, Locale locale) {

		validate.setDefault(result, message, locale);
		int i = 0;
		for (UserDptExtension entity : form.getList()) {
			if (StringUtils.isNotBlank(entity.getCddpt())) {
				// コードが入力されている場合、マスタの存在チェックを行う。
				Dpt dpt = new Dpt();
				dpt.setCddpt(entity.getCddpt());
				if (dptMapper.countByPrimaryKey(dpt) == 0) {
						validate.addFieldError(
						String.format("list[%d].cddpt", i)
						,entity.getCddpt() // エラーフィールドの値（基本はそのまま返す）
						,"jp.template.validator.dpt.code.notfound.message" // エラーメッセージ
						,new Object[]{entity.getCddpt()} // エラーメッセージに対応するメッセージの引数。
					);
				}
			}
			i++;
		}
	}
}
