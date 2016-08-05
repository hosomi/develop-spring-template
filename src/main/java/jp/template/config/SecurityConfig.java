package jp.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.template.service.UserDetailsServiceImpl;

/**
 * Spring Security の実装。
 * 
 * @author hosomi.
 */
@Configuration
@EnableWebSecurity // デフォルト でCSRF が有効。
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {

		// セキュリティ設定を無視するリクエスト設定
		// 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
		web.ignoring().antMatchers(
				"/", // / -> /login にリダイレクトした場合の挙動がおかしい（上手く認証できない現象） / アクセスで access denied になっていた。
				"/static/**", 
				"/images/**", 
				"/css/**", 
				"/javascript/**", 
				"/webjars/**");
	}

	 @Override
	protected void configure(HttpSecurity http) throws Exception {

		// Spring Security の設定
		http.authorizeRequests()
				// アクセス権限の設定
				// staticディレクトリにある、'/css/','fonts','/js/'は制限なし
				.antMatchers("/css/**", "/fonts/**", "/js/**", "/test").permitAll()
				// '/admin/'で始まるURLには、'ADMIN'ロールのみアクセス可
				// .antMatchers("/admin/**").hasRole("ADMIN")
				// ↑コメント（ロールでの運用なしとして今回は設定する。）ロールが必要な場合、コメントを外してロール名を設定する。
				// 他は制限なし
				.anyRequest().authenticated().and()
				// ログイン処理の設定
				.formLogin()
					.loginPage("/login")	// ログイン処理の URL
					.loginProcessingUrl("/login/auth")
					.failureUrl("/login?error=authentication") // 認証失敗時の URL
					.defaultSuccessUrl("/menu") // 認証後の URL （リダイレクト時はリダイレクト先）
					.usernameParameter("username") // username のパラメタ名(ユーザIDの form input name と一致させる。)
					.passwordParameter("password") // password のパラメタ名(パスワードの form input name と一致させる。)
				.permitAll().and()
				// ログアウト処理の設定
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理のURL
					.logoutSuccessUrl("/login") // ログアウト成功時の遷移先URL
					.deleteCookies("JSESSIONID") // ログアウト時に削除するクッキー名
					.invalidateHttpSession(true).permitAll(); // ログアウト時のセッション破棄を有効化
	}

	/**
	 * <pre>
	 * ユーザ認証の設定の実装。
	 * ・任意のエンティティからユーザ認証を行う。
	 * </pre>
	 * 
	 * @author hosomi.
	 */
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		UserDetailsServiceImpl userDetailsService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			// 認証するユーザーを設定する
			auth.userDetailsService(userDetailsService)
			// 入力値をbcryptでハッシュ化した値でパスワード認証を行う（平文なのでコメント、↓ BCrypt へアクセスする場合、コメント解除）
			// .passwordEncoder(new BCryptPasswordEncoder())
			;
		}
	}
}
