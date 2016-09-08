package jp.template.form;

import java.util.List;

import jp.template.domain.Todo;

/**
 * Todo フォーム。
 * 
 * @author hosomi.
 */
public class SampleMasterTodoForm {


	/** ユーザ一覧（検索結果）*/
	private List<Todo> list;

	/**
	 * @return the list
	 */
	public List<Todo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Todo> list) {
		this.list = list;
	}


	

}
