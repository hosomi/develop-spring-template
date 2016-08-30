package jp.template.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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

import jp.template.config.MessageResourcesConfig;
import jp.template.domain.User;
import jp.template.form.SampleMasterUserForm;
import jp.template.mapper.UserMapper;
import jp.template.security.LoginUser;

/**
 * ユーザマスタサンプル。
 * <UL>
 *   <LI>検索機能</LI>
 *   <LI>更新機能</LI>
 *   <LI>入力チェック(validation)</LI>
 * </UL>
 * 
 * 
 * @author hosomi.
 *
 */
@Controller
@RequestMapping(value = "/sample/master/user")
public class SampleMasterUserController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	UserMapper userMapper;

	/**
	 * マスタサンプルオープン時のイベント処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleMasterUserForm form, Model model) {

		return "sample/master/user";
	}

	/**
	 * デフォルトのサブミット(照会処理を実行します)。
	 * 
	 * @param loginUser 認証情報
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String showPost(@AuthenticationPrincipal LoginUser loginUser, SampleMasterUserForm form, Model model, Locale locale) {

		return inquiry(loginUser,form, model, locale);
	}
	
	/**
	 * 照会ボタン押下。
	 * 
	 * @param loginUser 認証情報
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doInquiry")
	public String inquiry(@AuthenticationPrincipal LoginUser loginUser, SampleMasterUserForm form, Model model, Locale locale) {

		// 現在の検索条件を保持。
		form.setLoginUserIdPost(form.getLoginUserId());

		List<User> list = retrieve(form, loginUser);
		form.setList(list);

		if (list.isEmpty()) {

			// 該当なし（0 件）のメッセージ。
			model.addAttribute("warning_message", messageSource.getMessage("jp.template.global.warning.retrieved.condition.notfound", null, locale));
		}
		
		return "sample/master/user";
	}
	
	/**
	 * 追加ボタン押下。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doAdd")
	public String add(SampleMasterUserForm form, Model model) {

		if (!Objects.isNull(form.getList())) {
			form.getList().add(new User());
		} else {
			List<User> list = new ArrayList<User>();
			list.add(new User());
			form.setList(list);
		}

		return "sample/master/user";
	}
	
	
	/**
	 * 保存ボタン押下。
	 * <p>画面内容を全て更新する。</p>
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param loginUser 認証情報
	 * @param result {@link BindingResult}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doSave")
	public String save(@AuthenticationPrincipal LoginUser loginUser, @Validated SampleMasterUserForm form, BindingResult result, Model model, Locale locale) {

		logger.debug(result.hasErrors());
		
		// フィールドエラーチェック判定
		if (result.hasErrors()) {
			return "sample/master/user";
		}
		
		// 独自の業務エラーチェックの実装..
		
		
		// 更新処理。
		if (! form.getList().isEmpty()) {
			
			// 全件更新。
			for (User entity : form.getList()) {
				
				if (entity.getId() != 0) {
					userMapper.update(entity);
				} else {
					userMapper.insert(entity);
				}
				
			}
		}

		// 更新後の再検索処理。
		form.setList(retrieve(form, loginUser));


		// 保存完了メッセージ。
		model.addAttribute("info_message", messageSource.getMessage("jp.template.global.info.save.completion.message", null, locale));
		
		return "sample/master/user";
	}
	
	/**
	 * 検索処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param loginUser 認証情報
	 * @return 検索結果一覧。
	 */
	private List<User> retrieve(SampleMasterUserForm form, LoginUser loginUser) {
		
		List<User> list;
		if (StringUtils.isBlank(form.getLoginUserIdPost())) {
			list = userMapper.selectAllNotExistsSystemLoginId(loginUser.getUsername());
		} else {
			list = new ArrayList<User>();
			User user = userMapper.selectNotExistsSystemLoginId(form.getLoginUserIdPost(), loginUser.getUsername());
			if (!Objects.isNull(user)) {
				list.add(user);
			}
		}

		return list;
	}
	
}
