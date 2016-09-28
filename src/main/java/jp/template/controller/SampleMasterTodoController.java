package jp.template.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.template.form.SampleMasterTodoForm;
import jp.template.mapper.TodoMapper;
import jp.template.utils.Pagination;

/**
 * ToDo ページングサンプル。
 * <UL>
 *   <LI>ページング機能（GET専用、共通化なし）</LI>
 * </UL>
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/sample/master/todo")
public class SampleMasterTodoController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private TodoMapper todoMapper;

	/**
	 * マスタサンプルオープン時のイベント処理。
	 * 
	 * @param page 表示ページ（GET パラメータ）
	 * @param form マスタサンプルフォーム {@link SampleMasterTodoForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/master/todo.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(@RequestParam(required = false) String page, SampleMasterTodoForm form, Model model) {

		logger.debug(page);
		
		Pagination pagination = new Pagination(StringUtils.isBlank(page) ? 1 : new Long(page), 5, todoMapper.count());

		logger.debug(pagination.getFirstPage());
		logger.debug(pagination.getLastPage());
		logger.debug(pagination.getTotalPage());
		
		form.setList(todoMapper.pagingCurrent(pagination.getFirstPage(), pagination.getLastPage()));
		
		model.addAttribute("pagination", pagination);

		return "sample/master/todo";
	}
}
