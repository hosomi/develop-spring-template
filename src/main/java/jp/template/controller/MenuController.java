package jp.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * メニューコントローラ.
 * 
 * @author hosomi.
 *
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	
	/**
	 * 初期オープン時のマッピング。
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show() throws Exception {

		return "menu";
	}
}
