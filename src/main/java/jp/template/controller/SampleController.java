package jp.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * サンプル用コントローラ.
 * 
 * @author hosomi.
 *
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {

	/**
	 * コントロールサンプル。
	 * 
	 * @return ビュー名。
	 */
	@RequestMapping(value = "/controlles/datetimepicker", method = RequestMethod.GET)
	public String show() {

		return "sample/controlles/datetimepicker";
	}
}
