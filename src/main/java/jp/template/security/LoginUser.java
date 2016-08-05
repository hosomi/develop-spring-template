package jp.template.security;

import org.springframework.security.core.authority.AuthorityUtils;

import jp.template.domain.User;

/**
 * <pre>
 * 認証時のユーザ情報を提供します。
 * ・Spring 専用のユーザ情報ではなく、DB に実装しているデータを利用します。
 * </pre>
 * 
 * @author hosomi.
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

	/** serialVersionUID. */
	private static final long serialVersionUID = 5995980685832853146L;

	/** ログインユーザ */
	private final User user;

	/**
	 * ログインユーザ を取得します。
	 * 
	 * @return ログインユーザ。
	 */
	public User getUser() {

		return user;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param user {@link org.springframework.security.core.userdetails.User}
	 */
	public LoginUser(User user) {

		// スーパークラスのユーザーID、パスワードに値をセットする。
		// 実際の認証はスーパークラスのユーザーID、パスワードで行われる。
		super(user.getLoginUserId(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}
}
