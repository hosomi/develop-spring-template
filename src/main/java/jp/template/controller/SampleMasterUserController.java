package jp.template.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.template.domain.User;
import jp.template.form.SampleMasterUserForm;
import jp.template.mapper.UserMapper;

@Controller
@RequestMapping(value = "/sample/master/user")
public class SampleMasterUserController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * マスタサンプル。
	 * 
	 * @return ビュー名。
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleMasterUserForm form, Model model) {


		
		return "sample/master/user";
	}
	
	/**
	 * 照会ボタン押下。
	 * 
	 * @return ビュー名。
	 */
	@RequestMapping(method = RequestMethod.POST, params = "doInquiry")
	public String inquiry(SampleMasterUserForm form, Model model) {

		
		logger.info(form.getLoginUserId());
		List<User> list;
		if (StringUtils.isBlank(form.getLoginUserId())) {
			list = userMapper.selectAll();
		} else {
			list = new ArrayList<User>();
			User user = userMapper.select(form.getLoginUserId());
			if (!Objects.isNull(user)) {
				list.add(user);
			}
		}
		form.setLoginUserIdPost(form.getLoginUserId());
		form.setList(list);
		
		
		
		return "sample/master/user";
	}
	
	
}
