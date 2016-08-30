package jp.template.config;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * システムエラー時の共通処理。 
 * <pre>
 * ・ログを出力（error レベルで出力）。
 * ・エラー共通ページへ遷移。
 * </pre>
 * 
 * @author hosomi
 */
@ControllerAdvice
public class ExceptionAdvice {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * システムエラー時のエラーログ生成。
	 * <p>/resources/error.html がデフォルトです。</p>
	 * 
	 * @param principal 認証情報。
	 * @param request {@link HttpServletRequest}
	 * @param e キャッチした例外
	 */
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public void defaultErrorHandler(Principal principal, HttpServletRequest request, Exception e) {
		logger.error("error-url:{},loginid:{}", request.getRequestURL(), principal.getName(), e);
	}
}
