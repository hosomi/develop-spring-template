package jp.template.domain;

import javax.validation.constraints.Size;

/**
 * User エンティティ.
 * 
 * @author hosomi.
 */
public class User {

	/** id。*/
	private int id;
	
	/** ログインユーザID*/
//	@NotBlank
	@Size(min=3,max=10) // 最小サイズを指定している場合、@NotBlank は不要。
	private String loginUserId;
	
	/** パスワード*/
//	@NotBlank
	@Size(min=8,max=50)
	private String password;

	/** パスワード再設定*/
	private String rePassword;

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
	
	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}

	/**
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
}
