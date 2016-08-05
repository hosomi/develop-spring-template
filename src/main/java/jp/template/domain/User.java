package jp.template.domain;

/**
 * User エンティティ.
 * 
 * @author hosomi.
 */
public class User {

	/** ログインユーザID*/
	private String loginUserId;
	
	/** パスワード（とりあえず平文）*/
	private String password;

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
