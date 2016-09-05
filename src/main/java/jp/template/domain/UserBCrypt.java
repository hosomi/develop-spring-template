package jp.template.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * User エンティティ.
 * <p>パスワードをハッシュ化して格納します、その他は  {@link User} と同じです。</p>
 * 
 * @author hosomi.
 */
public class UserBCrypt extends User {

	/**
	 * ユーザID、パスワードを設定するコンストラクタ。
	 * 
	 * @param loginUserId ユーザID
	 * @param password パスワード
	 */
	public UserBCrypt(String loginUserId, String password) {
		super.setLoginUserId(loginUserId);
		super.setPassword(new BCryptPasswordEncoder().encode(password));
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(new BCryptPasswordEncoder().encode(password));
	}
}
