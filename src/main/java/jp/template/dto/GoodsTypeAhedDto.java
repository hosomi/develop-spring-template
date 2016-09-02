package jp.template.dto;

import java.util.List;

import jp.template.domain.Goods;

/**
 * Goods TypeAhead dto
 * 
 * <p>
 * キーワード 用 token 追加。
 * </p>
 * 
 * @author hosomi.
 */
public class GoodsTypeAhedDto extends Goods {

	/** キーワード用 */
	private List<String> tokens;

	/**
	 * キーワード用を取得します。
	 * 
	 * @return キーワード用
	 */
	public List<String> getTokens() {
		return tokens;
	}

	/**
	 * キーワード用を設定します。
	 * 
	 * @param tokens キーワード用
	 */
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
}
