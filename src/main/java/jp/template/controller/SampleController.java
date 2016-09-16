package jp.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.template.domain.Goods;
import jp.template.dto.GoodsTypeAhedDto;
import jp.template.form.SampleModalForm;
import jp.template.form.SampleTypeAheadForm;
import jp.template.mapper.GoodsMapper;
import jp.template.utils.Pagination;
import jp.template.utils.query.QueryEscapeUtils;

/**
 * サンプル用コントローラ.
 * 
 * @author hosomi.
 *
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {

	/** Goods DI*/
	@Autowired
	GoodsMapper goodsMapper;

	/**
	 * コントロールサンプル（DateTimePicker）。
	 * 
	 * @return /resources/templates/sample/controlles/datetimepicker.html
	 */
	@RequestMapping(value = "/controlles/datetimepicker", method = RequestMethod.GET)
	public String datetimepicker() {

		return "sample/controlles/datetimepicker";
	}

	/**
	 * コントロールサンプル（TypeAhead）。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleTypeAheadForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/controlles/typeahead.html
	 */
	@RequestMapping(value = "/controlles/typeahead", method = RequestMethod.GET)
	public String typeahead(SampleTypeAheadForm form, Model model) {
		
		form.setList(goodsMapper.selectAll());
		
		return "sample/controlles/typeahead";
	}

	/**
	 * コントロールサンプル（Modal）。
	 * 
	 * @param page 表示ページ（GET パラメータ）
	 * @param form Modal サンプルフォーム {@link SampleModalForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/controlles/modal.html
	 */
	@RequestMapping(value = "/controlles/modal", method = RequestMethod.GET)
	public String modal(@RequestParam(required = false) String page,SampleModalForm form, Model model) {

		Pagination pagination = new Pagination(StringUtils.isBlank(page) ? 1 : new Long(page), 5, goodsMapper.count());
		form.setList(goodsMapper.pagingCurrent(pagination.getFirstPage(), pagination.getLastPage()));
		model.addAttribute("pagination", pagination);

		return "sample/controlles/modal";
	}

	/**
	 * コントロールサンプル（BackToTop）。
	 * 
	 * @return /resources/templates/sample/controlles/backtotop.html
	 */
	@RequestMapping(value = "/controlles/backtotop", method = RequestMethod.GET)
	public String backtotop() {

		return "sample/controlles/backtotop";
	}

	/**
	 * コントロールサンプル（Bootstrap Switch）。
	 * 
	 * @return /resources/templates/sample/controlles/bootstrap-switch.html
	 */
	@RequestMapping(value = "/controlles/bootstrap-switch", method = RequestMethod.GET)
	public String bootstrapSwitch() {

		return "sample/controlles/bootstrap-switch";
	}
	
	/**
	 * Goods Rest コントローラ (*Type Ahed 用を想定しています)。
	 * <pre>
	 * 一件あたりの JSON 生データ内容：
	 * [{
	 *   "id":1,
	 *   "code":"0000000001",
	 *   "name":"商品名１",
	 *   "kana":"ショウヒンメイイチ",
	 *   "note":"商品名１の備考など",
	 *   "tokens":["0000000001","商品名１","ショウヒンメイイチ"]
	 *  }]
	 * </pre>
	 * @param queryStr 検索キーワード。
	 * @return 検索キーワードに一致する、Goods 一覧。（※最終的には JSON 形式に変換されます。）
	 */
	@ResponseBody
	@RequestMapping(value = "/controlles/typeahead/{queryStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GoodsTypeAhedDto> restTypeahead(@PathVariable String queryStr) {

		List<GoodsTypeAhedDto> list = new ArrayList<GoodsTypeAhedDto>();

		List<Goods> dataList;
		if (StringUtils.isNotBlank(queryStr)) {
			// キーワードが 1 文字以上入力された場合のみクエリ発行。
			dataList = goodsMapper.selectListTypeAhead(QueryEscapeUtils.toContainingCondition(queryStr)); // Escape を適切に行う(SQL インジェクション対応)。
		} else {
			// キーワードが未入力の場合、空を返す。
			dataList = new ArrayList<Goods>();
		}

		GoodsTypeAhedDto dto;
		List<String> tokens;
		for (Goods entity : dataList) {

			// TypeAhead 用トークン（入力されたキーワードと一致させる項目）。
			tokens = new ArrayList<String>();
			tokens.add(entity.getCode());
			tokens.add(entity.getName());
			tokens.add(entity.getKana());

			// JSON として返す値、 Bean の場合、項目全て設定するのが望ましい。
			dto = new GoodsTypeAhedDto();
			dto.setId(entity.getId());
			dto.setCode(entity.getCode());
			dto.setName(entity.getName());
			dto.setKana(entity.getKana());
			dto.setNote(entity.getNote());
			dto.setTokens(tokens);

			list.add(dto);
		}
		return list;
	}
}
