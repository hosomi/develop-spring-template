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
	 * マスタサンプル。
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

		// 自身の ID は検索時に省く。
		List<User> list;
		if (StringUtils.isBlank(form.getLoginUserId())) {
			list = userMapper.selectAllNotExistsSystemLoginId(loginUser.getUsername());
		} else {
			list = new ArrayList<User>();
			User user = userMapper.selectNotExistsSystemLoginId(form.getLoginUserId(), loginUser.getUsername());
			if (!Objects.isNull(user)) {
				list.add(user);
			}
		}
		form.setLoginUserIdPost(form.getLoginUserId());
		form.setList(list);

		if (list.isEmpty()) {
			
			// 該当なし（0 件）のメッセージ。
			model.addAttribute("warning_message", messageSource.getMessage("jp.template.global.warning.retrieved.condition.notfound", null, locale));
		}
		
		return "sample/master/user";
	}
	
	/**
	 * 保存ボタン押下。
	 * <p>画面内容を全て更新する。</p>
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterUserForm}
	 * @param result {@link BindingResult}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/master/user.html
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doSave")
	public String save(@Validated SampleMasterUserForm form, BindingResult result, Model model, Locale locale) {

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
				userMapper.update(entity);
			}
		}

		
		// 保存完了メッセージ。
		model.addAttribute("info_message", messageSource.getMessage("jp.template.global.info.save.completion.message", null, locale));
		
		return "sample/master/user";
	}
}
