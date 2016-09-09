package jp.template.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.template.form.SampleIframeGoodsForm;
import jp.template.mapper.GoodsMapper;
import jp.template.utils.Pagination;

/**
 * iframe コンテンツ用コントローラ。
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/iframe")
public class IframeController {

	/** Goods DI*/
	@Autowired
	GoodsMapper goodsMapper;
	
	/**
	 * goods 一覧ページ生成。
	 * 
	 * @param page ページ番号
	 * @param form {@link SampleIframeGoodsForm}
	 * @param model {@link Model}
	 * @return /resources/template/iframe/goods.html
	 */
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public String iframeGoods(@RequestParam(required = false) String page,SampleIframeGoodsForm form, Model model) {

		Pagination pagination = new Pagination(StringUtils.isBlank(page) ? 1 : new Long(page), 5, goodsMapper.count());
		form.setList(goodsMapper.pagingCurrent(pagination.getFirstPage(), pagination.getLastPage()));
		model.addAttribute("pagination", pagination);

		return "/iframe/goods";
	}
}
