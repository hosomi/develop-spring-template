package jp.template.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * User エンティティ拡張.
 * <p>{@link #UserBCrypt(String, String)}コンストラクタがパスワードをハッシュ化して格納します、その他は  {@link User} と同じです。</p>
 * 
 * @author hosomi.
 */
public class UserBCrypt extends User {

	/**
	 * ユーザID、パスワード(BCrypt)を設定するコンストラクタ。
	 * 
	 * @param loginUserId ユーザID
	 * @param password パスワード
	 */
	public UserBCrypt(String loginUserId, String password) {
		super.setLoginUserId(loginUserId);
		super.setPassword(new BCryptPasswordEncoder().encode(password));
	}
	
	/**
	 * ID、ユーザID、パスワード(BCrypt)を設定するコンストラクタ。
	 * 
	 * @param id ID
	 * @param loginUserId ユーザID
	 * @param password パスワード
	 */
	public UserBCrypt(int id, String loginUserId, String password) {
		
		super.setId(id);
		super.setLoginUserId(loginUserId);
		super.setPassword(new BCryptPasswordEncoder().encode(password));
	}
}
