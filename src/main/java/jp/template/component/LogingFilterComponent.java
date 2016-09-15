package jp.template.component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * ロギング用フィルタコンポーネント。
 * <p>@Component アノテーションで起動時に自動登録されます。</p>
 * 
 * <pre>
 * ・log4j2 の info 以上をログ出力対象として設定のこと。
 * ・複数フィルタでの運用は想定していません。（複数登録する場合は FilterRegistrationBean を利用し再登録してください。）
 * 
 * ロギング対象：
 * ・info: ページアクセス情報。
 * ・trace: リクエス情報、処理結果（HTTP ステータスコードなど）
 * ログフォーマット：
 * [ログインID][UUID]*任意のログ
 * 
 * ログインID
 * ・認証外の場合、null になります。
 * 
 * UUID
 * 一意のID 詳しくは下記へ
 * ・https://docs.oracle.com/javase/jp/8/docs/api/java/util/UUID.html
 * 
 * *任意のログ 
 * ・URI: パス
 * ・リクエスト内容
 * ・HTTP ステータスコード
 * ・処理経過時間
 * 
 * </pre>
 * 
 * @author hosomi.
 */
@Component
public class LogingFilterComponent  implements Filter {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = ((HttpServletRequest)request);
		String uri = httpReq.getRequestURI();
		if (isStatic(uri)) {
			chain.doFilter(request, response);
			return;
		}
 
		long start = System.currentTimeMillis();
		UUID uuid = UUID.randomUUID();
		String userName = httpReq.getRemoteUser();
		String requestIdentifier = "[" + userName + "]" + "[" + uuid + "]";
 
		logger.trace(String.format("%s start", requestIdentifier));
		logger.info(String.format("%s URI: %s", requestIdentifier, uri));

		// リクエスト（POST 内容）
		Map<String, String[]> params = httpReq.getParameterMap();
		for (Entry<String, String[]> param : params.entrySet()) {
			logger.trace(String.format("%s PARAM_KEY: %s, PARAM_VALUE: %s"
					, requestIdentifier
					, param.getKey()
					, Arrays.toString(param.getValue())));
		}
 
		chain.doFilter(request, response);
 
		int status = ((HttpServletResponse)response).getStatus();
 
		logger.trace(String.format("%s end in %d millisec. STATUS %d", requestIdentifier, System.currentTimeMillis() - start, status));
	}

	@Override
	public void destroy() { }

	/**
	 * ログ対象外 URI
	 * 
	 * @param uri 対象 URI (コンテキスト以下)
	 * @return true:ロギング対象外、false:ロギング対象
	 */
	private boolean isStatic(String uri) {
		return uri.contains("/login")
		|| uri.contains("/static/")
		|| uri.contains("/webjars/")
		|| uri.contains("/fonts/");
	}
}
