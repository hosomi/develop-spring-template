package jp.template.basic.data;

import jp.template.domain.UserBCrypt;
import jp.template.security.LoginUser;

/**
 * テストクラスで使用する認証ユーザーの情報。
 * 
 * @author hosomi.
 */
public class LoginUserTestData {

	/** 認証情報ダミー（実在していなくても可）*/
	private static final UserBCrypt user = new UserBCrypt(
			"user1","user1" 
	);

	/** 認証済みの情報として格納*/
	private static final LoginUser loginUser = new LoginUser(user);

	/**
	 * Mock からの呼び出し用コンストラクタ
	 * 
	 * @return　ログイン情報。
	 */
	public static LoginUser getLoginUserData() {
		return loginUser;
	}
}