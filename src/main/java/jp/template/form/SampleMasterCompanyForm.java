package jp.template.form;

import java.util.List;

import jp.template.domain.Company;

/**
 * Company フォーム。
 * 
 * @author hosomi.
 */
public class SampleMasterCompanyForm {

	/** Company 一覧（検索結果）*/
	private List<Company> list;

	/**
	 * @return the list
	 */
	public List<Company> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Company> list) {
		this.list = list;
	}
}
