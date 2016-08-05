package jp.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	
	// 初期オープン時。
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelAndView modelAndView) throws Exception {



		return "menu";
	}
}
