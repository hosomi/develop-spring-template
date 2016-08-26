package jp.template.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * User エンティティ.
 * 
 * @author hosomi.
 */
public class User {

	/** id。*/
	private int id;
	
	/** ログインユーザID*/
	@NotBlank
	private String loginUserId;
	
	/** パスワード（とりあえず平文）*/
	@NotBlank
	private String password;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
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
