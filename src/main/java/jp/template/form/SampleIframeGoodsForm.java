package jp.template.form;

import java.util.List;

import jp.template.domain.Goods;

/**
 * 商品選択 iframe フォーム
 * 
 * @author hosomi.
 */
public class SampleIframeGoodsForm {

	/** Goods 一覧。*/
	private List<Goods> list;

	/**
	 * @return the list
	 */
	public List<Goods> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Goods> list) {
		this.list = list;
	}
}
