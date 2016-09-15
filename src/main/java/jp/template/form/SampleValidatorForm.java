package jp.template.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import jp.template.annotation.DateSimple;
import jp.template.annotation.DateSimplePeriod;
import jp.template.domain.Goods;

/**
 * 入力検証サンプルフォーム。
 * 
 * @author hosomi.
 */
@DateSimplePeriod(fieldNameFrom="from", fieldNameTo="to")
public class SampleValidatorForm {

	/** 文字・必須チェック*/
	@NotBlank
	private String notblank;
	
	/** 文字・サイズチェック(最大のみ)*/
	@Size(max=10)
	private String maxlength;
	
	/** 文字・サイズチェック(最小のみ)*/
	@Size(min=3)
	private String minlength;
	
	/** 文字・サイズチェック(最小、最大両方)*/
	@Size(min=3, max=10)
	private String mixlength;
	
	/** 数値精度（整数部、小数部 ※両方指定必須）*/
	@Digits(integer = 8, fraction = 2)
	private BigDecimal digits;
	
	/** カスタム・日付フォーマット(yyyy/MM/dd)*/
	@DateSimple
	private String dateformat;

	/** カスタム・日付フォーマット(yyyyMMdd)*/
	@NotBlank
	@DateSimple(format="yyyyMMdd")
	private String dateformat2;

	/** 開始日*/
	private String from;
	
	/** 終了日*/
	private String to;

	/** 商品。*/
	private Goods goods;
	
	/**
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}

	/**
	 * @param goods the goods to set
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the dateformat2
	 */
	public String getDateformat2() {
		return dateformat2;
	}

	/**
	 * @param dateformat2 the dateformat2 to set
	 */
	public void setDateformat2(String dateformat2) {
		this.dateformat2 = dateformat2;
	}

	/**
	 * @return the dateformat
	 */
	public String getDateformat() {
		return dateformat;
	}

	/**
	 * @param dateformat the dateformat to set
	 */
	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	/**
	 * @return the notblank
	 */
	public String getNotblank() {
		return notblank;
	}

	/**
	 * @param notblank the notblank to set
	 */
	public void setNotblank(String notblank) {
		this.notblank = notblank;
	}

	/**
	 * @return the maxlength
	 */
	public String getMaxlength() {
		return maxlength;
	}

	/**
	 * @param maxlength the maxlength to set
	 */
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * @return the minlength
	 */
	public String getMinlength() {
		return minlength;
	}

	/**
	 * @param minlength the minlength to set
	 */
	public void setMinlength(String minlength) {
		this.minlength = minlength;
	}

	/**
	 * @return the mixlength
	 */
	public String getMixlength() {
		return mixlength;
	}

	/**
	 * @param mixlength the mixlength to set
	 */
	public void setMixlength(String mixlength) {
		this.mixlength = mixlength;
	}

	/**
	 * @return the digits
	 */
	public BigDecimal getDigits() {
		return digits;
	}

	/**
	 * @param digits the digits to set
	 */
	public void setDigits(BigDecimal digits) {
		this.digits = digits;
	}



}
