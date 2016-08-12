package jp.template.form;

import java.util.List;

import jp.template.domain.User;

public class SampleMasterUserForm {

	/** ログインユーザID（検索条件）*/
	private String loginUserId;

	/** ログインユーザID（検索条件送信後）*/
	private String loginUserIdPost;
	
	/**
	 * @return the loginUserIdPost
	 */
	public String getLoginUserIdPost() {
		return loginUserIdPost;
	}

	/**
	 * @param loginUserIdPost the loginUserIdPost to set
	 */
	public void setLoginUserIdPost(String loginUserIdPost) {
		this.loginUserIdPost = loginUserIdPost;
	}

	/** ユーザ一覧（検索結果）*/
	private List<User> list;
	
	/**
	 * @return the loginUserId
	 */
	public String getLoginUserId() {
		return loginUserId;
	}

	/**
	 * @param loginUserId the loginUserId to set
	 */
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	/**
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<User> list) {
		this.list = list;
	}


}
