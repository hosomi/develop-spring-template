package jp.template.controller;

import java.lang.reflect.InvocationTargetException;
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

import jp.template.component.ValidateComponent;
import jp.template.config.MessageResourcesConfig;
import jp.template.domain.User;
import jp.template.domain.UserBCrypt;
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
	private MessageSource message;
	
	@Autowired
	UserMapper userMapper;

	/** 入力検証コンポーネント*/
	@Autowired
	ValidateComponent validate;
	
	
	/**
	 * マスタサンプルオープン時のイベント処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleMasterUserForm form, Model model) {

		logger.info(validate);
		
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
			model.addAttribute("warning_message", message.getMessage("jp.template.global.warning.retrieved.condition.notfound.message", null, locale));
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
	public String save(@AuthenticationPrincipal LoginUser loginUser, @Validated SampleMasterUserForm form, BindingResult result, Model model, Locale locale) throws IllegalAccessException, InvocationTargetException {

		logger.debug(result.hasErrors());
		
		// 独自の業務エラーチェックの実装..
		customValidate(form, result, locale);
		
		logger.debug(result.hasErrors());
		
		// フィールドエラーチェック判定
		if (result.hasErrors()) {
			return "sample/master/user";
		}

		// 更新処理。
		// コミットは必要ありません。（システムエラー時はロールバックします、トランザクション管理は {#link jp.template.config.TxAdviceConfig} を参照してください。）
		if (! form.getList().isEmpty()) {
			for (User entity : form.getList()) {
				if (entity.getId() != 0) {
					if (StringUtils.isNotBlank(entity.getRePassword())) {
						// パスワード再設定がされている場合のみ更新。
						userMapper.update(new UserBCrypt(entity.getId(),entity.getLoginUserId(), entity.getRePassword()));
					}
				} else {
					userMapper.insert(new UserBCrypt(entity.getLoginUserId(), entity.getPassword()));
				}
			}
		}

		// 更新後の再検索処理。
		form.setList(retrieve(form, loginUser));

		// 保存完了メッセージ。
		model.addAttribute("info_message", message.getMessage("jp.template.global.info.save.completion.message", null, locale));
		
		return "sample/master/user";
	}

	/**
	 * 当機能固有の検証(実装サンプル)。
	 * <p>極力、当処理は実装せずにアノテーションで検証してください。</p>
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param result {@link BindingResult}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 */
	private void customValidate(SampleMasterUserForm form,BindingResult result, Locale locale) {

		validate.setDefault(result, message, locale);
		int i=0;
		for (User entity : form.getList()) {
			if (entity.getId() != 0) {
				if (StringUtils.isNotBlank(entity.getRePassword())) {
					// 入力されていた場合、桁数チェック（8~32桁以内）
					// メッセージコードは流用。
					int lengrh = entity.getRePassword().getBytes().length;
					if (lengrh < 8 || lengrh > 32) {
						validate.addFieldError(
							String.format("list[%d].rePassword", i) // エラー対象のフィールド、一覧の場合、form の一覧の項目名 + [中の要素位置] + 項目名、一覧でない場合、項目名のみになる。
							,entity.getRePassword() // エラーフィールドの値（基本はそのまま返す）
							,"javax.validation.constraints.Size.message" // resources/i18n/messages_[ja_jp].properties の定義済みコード
							,new Object[]{"",32,8} // メッセージに引数がある場合指定、なければ引数から外してください。 {"使用しない",最大値桁, 最小値桁} 参考：https://jira.spring.io/browse/SPR-6730
						);
					}
					
				}
				
				
			}
			i++;
		}
	}
	
	
	/**
	 * 削除ボタン押下。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param loginUser 認証情報
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doDelete")
	public String delete(@AuthenticationPrincipal LoginUser loginUser, SampleMasterUserForm form, Model model, Locale locale) {
		
		// 選択された行が無ければ何も行わない。
		if (form.getSelectRow() <= 0) {
			return "sample/master/user";
		}
		
		// 一覧が無ければ何も行わない。
		if (Objects.isNull(form.getList()) || form.getList().isEmpty()) {
			return "sample/master/user";
		}
		
		// 一覧のサイズより選択行が大きい場合何も行わない。
		if (form.getSelectRow() > form.getList().size()) {
			return "sample/master/user";
		}
		
		User entity = form.getList().get(form.getSelectRow()-1);
		
		
		if (entity.getId() > 0) {
		
			// delete 文発行。
			userMapper.delete(entity.getId());
		}

		// 画面の一覧から行を削除。
		form.getList().remove(form.getSelectRow()-1);

		// 削除完了メッセージ。
		model.addAttribute("info_message", message.getMessage("jp.template.global.info.delete.choice.completion.message", null, locale));

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
