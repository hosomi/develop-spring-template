package jp.template.form;

import java.util.List;

import jp.template.domain.Dpt;

/**
 * 商品選択 iframe フォーム
 * 
 * @author hosomi.
 */
public class SampleIframeDptForm {

	/** Dpt 一覧。*/
	private List<Dpt> list;

	/**
	 * @return the list
	 */
	public List<Dpt> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Dpt> list) {
		this.list = list;
	}
}
