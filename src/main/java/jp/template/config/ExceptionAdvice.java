package jp.template.config;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
	
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ModelAndView defaultErrorHandler(Principal principal, HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("error");

		logger.error("error-url:{},loginid:{}", request.getRequestURL(), principal.getName(), e);

		mav.addObject("datetime", new Date());
		mav.addObject("exception", e);

		mav.addObject("url", request.getRequestURL());
		return mav;
	}
	
}
