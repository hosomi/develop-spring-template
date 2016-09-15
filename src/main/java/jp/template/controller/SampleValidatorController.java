package jp.template.controller;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.template.component.ValidateComponent;
import jp.template.config.MessageResourcesConfig;
import jp.template.domain.Goods;
import jp.template.form.SampleValidatorForm;
import jp.template.mapper.GoodsMapper;

/**
 * 入力値検証サンプル。
 * <UL>
 *   <LI>用意されているアノテーションでのチェック</LI>
 *   <LI>アノテーションなしの独自チェック</LI>
 *   <LI>カスタムアノテーションを利用しチェック</LI>
 * </UL>
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/sample/validator")
public class SampleValidatorController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private MessageSource message;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	/** 入力検証コンポーネント*/
	@Autowired
	private ValidateComponent validate;
	
	/**
	 * 入力値検証初期オープン時。
	 * 
	 * @param form 入力検証サンプルフォーム {@link SampleValidatorForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/validator.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleValidatorForm form, Model model) {

		form.setNotblank("");
		form.setMinlength("");
		form.setMaxlength("12345678901");
		form.setMixlength("12345678901");
		form.setDigits(new BigDecimal("123456789.123"));
		form.setDateformat("20160102");
		form.setDateformat2("");
		form.setFrom("2016/07/02");
		form.setTo("2016/07/01");
		
		Goods goods = new Goods();
		goods.setCode("000000");
		form.setGoods(goods);

		return "sample/validator";
	}

	/**
	 * 入力チェックボタン押下。
	 * 
	 * @param form 入力検証サンプルフォーム {@link SampleValidatorForm}
	 * @param result {@link BindingResult}
	 * @param model {@link Model}
	 * @param locale 現在のロケール（{@link MessageResourcesConfig}）
	 * @return /resources/templates/sample/validator.html
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String validate(@Validated SampleValidatorForm form, BindingResult result, Model model, Locale locale) {

		logger.debug(result.hasErrors());

		// 個別チェックアノテーションでは処理できない業務チェックなど。
		if (StringUtils.isNotBlank(form.getGoods().getCode())) {

			if (goodsMapper.countFromCode(form.getGoods()) == 0) {
				validate.setDefault(result, message, locale);
				validate.addFieldError(
					"goods.code" // 対象のフィールド
					,form.getGoods().getCode() // エラーフィールドの値（基本はそのまま返す）
					,"jp.template.validator.goods.code.notfound.message" // エラーメッセージ
					,new Object[]{form.getGoods().getCode()} // エラーメッセージに対応するメッセージの引数。
				);
			}
		} else {
			form.setGoods(new Goods());
		}

		// フィールドエラーチェック判定（個別チェック判定含む。）
		if (result.hasErrors()) {
			return "sample/validator";
		}

		// 入力エラー無しのメッセージ。
		model.addAttribute("info_message", message.getMessage("jp.template.global.info.validate.completion.message", null, locale));
		
		return "sample/validator";
	}
}
