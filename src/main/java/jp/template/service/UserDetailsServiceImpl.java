package jp.template.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.template.domain.User;
import jp.template.mapper.UserMapper;
import jp.template.security.LoginUser;

/**
 * <pre>
 * UserDetailsServiceの実装クラス。
 * Spring Securityでのユーザー認証に使用する。
 * </pre>
 * 
 * @author hosomi.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String login_id) throws UsernameNotFoundException {

		// 認証を行うユーザー情報を格納する。
		User user = null;
		try {
			// 入力したユーザーIDから認証を行うユーザー情報を取得する。
			user = userMapper.select(login_id);
			if (Objects.isNull(user)) {
				throw new UsernameNotFoundException("User not found for login id: " + login_id);
			}

			// ユーザー情報が取得できたらSpring Securityで認証できる形で戻す。
			return new LoginUser(user);
		} catch (Exception e) {
			// 取得時にExceptionが発生した場合
			throw new UsernameNotFoundException("It can not be acquired User");
		}
	}

}