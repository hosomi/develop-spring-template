package jp.template.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 
 * セッションタイムアウト時の EntryPoint の実装。
 * <p>
 * 実際の EntryPoint 登録は {@link SecurityConfig#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} で行う。
 * </p>
 * 
 * @author hosomi.
 */
public class SessionExpiredDetectingLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	
	/**
	 * コンストラクタ。
	 * ログインURLを指定。
	 * 
	 * @param loginFormUrl ログインURL(/login)
	 */
	public SessionExpiredDetectingLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}
 
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			// フロントの Ajax 側ではステータスコード 401 で判定しタイムアウトを検知する。
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
			return;
		}
 
		super.commence(request, response, authException);
	}
 
	@Override
	protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
 
		String redirectUrl = super.buildRedirectUrlToLoginPage(request, response, authException);
		if (isRequestedSessionInvalid(request)) {
			redirectUrl += redirectUrl.contains("?") ? "&" : "?";
			redirectUrl += "warn=timeout";
		}
		return redirectUrl;
	}
 
	/**
	 * セッションを無効として判断する内容を実装する。
	 * 
	 * @param request {@link org.apache.catalina.servlet4preview.http.HttpServletRequest}
	 * @return true ... 無効（ セッション ID が null 以外 and {@link HttpServletRequest#isRequestedSessionIdValid()} == false）
	 */
	private boolean isRequestedSessionInvalid(HttpServletRequest request) {
		
		// セッション ID が null 以外 and {@link HttpServletRequest#isRequestedSessionIdValid()} == false
		return request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
	}
}
