package jp.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.template.service.UserDetailsServiceImpl;
/**
 * Spring Security設定クラス.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers(
        					"/static/**",
                            "/images/**",
                            "/css/**",
                            "/javascript/**",
                            "/webjars/**",
                            "/test");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        // 認可の設定
        http.authorizeRequests()
            .antMatchers("/", "/login","test").permitAll() // 全ユーザーアクセス許可
            .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
            .loginProcessingUrl("/login")   // 認証処理のパス
            .loginPage("/login")            // ログインフォームのパス
//            .failureHandler(new SampleAuthenticationFailureHandler())       // 認証失敗時に呼ばれるハンドラクラス
            .failureUrl("/login-error")
            .defaultSuccessUrl("/menu")     // 認証成功時の遷移先
            .usernameParameter("login_id").passwordParameter("login_password")  // ユーザー名、パスワードのパラメータ名
            .and();

        // ログアウト設定
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
            .logoutSuccessUrl("/login");                                        // ログアウト完了時のパス
*/
    	
    	
    	
    	   http.authorizeRequests()
           // アクセス権限の設定
           // staticディレクトリにある、'/css/','fonts','/js/'は制限なし
           .antMatchers("/css/**", "/fonts/**", "/js/**","/test").permitAll()
           // '/admin/'で始まるURLには、'ADMIN'ロールのみアクセス可
           //.antMatchers("/admin/**").hasRole("ADMIN")
           // 他は制限なし
           .anyRequest().authenticated()
         .and()
           // ログイン処理の設定
           .formLogin()
             // ログイン処理のURL
             .loginPage("/login")
             .failureUrl("/login?error=authentication")
             .defaultSuccessUrl("/menu")
             // usernameのパラメタ名
             .usernameParameter("username")
             // passwordのパラメタ名
             .passwordParameter("password")
             
             .permitAll()
         .and()
           // ログアウト処理の設定
           .logout()
             // ログアウト処理のURL
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             // ログアウト成功時の遷移先URL
             .logoutSuccessUrl("/login")
             // ログアウト時に削除するクッキー名
             .deleteCookies("JSESSIONID")
             // ログアウト時のセッション破棄を有効化
             .invalidateHttpSession(true)
             .permitAll()
         ;
    }

    @Configuration
    protected static class AuthenticationConfiguration  extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsServiceImpl userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService)
            // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            //.passwordEncoder(new BCryptPasswordEncoder())
            ;

        }
    }
}
