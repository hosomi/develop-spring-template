package jp.template.form;

import java.util.List;

import javax.validation.Valid;

import jp.template.domain.UserDptExtension;

/**
 * ユーザ・所属(UserDptExtension)フォーム。
 * 
 * @author hosomi.
 */
public class SampleMasterUserDptForm {

	/** ユーザ・所属(UserDptExtension) 一覧（検索結果）*/
	@Valid
	private List<UserDptExtension> list;

	/** 選択行（削除）*/
	private int selectRow;

	/**
	 * @return the list
	 */
	public List<UserDptExtension> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<UserDptExtension> list) {
		this.list = list;
	}

	/**
	 * @return the selectRow
	 */
	public int getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}
}
