package jp.template.component;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import jp.template.config.MessageResourcesConfig;

/**
 * 入力値検証コンポーネント
 * 
 * @author hosomi.
 */
@Component
public class ValidateComponent {

	/** {@link BindingResult} */
	private BindingResult result;

	/** MessageSource */
	private MessageSource messageSource;

	/** objectName */
	private String objectName;

	/** ロケール*/
	private Locale locale;
	
	/**
	 * デフォルトコンストラクタ。
	 */
	public ValidateComponent() {
		// DI 用デフォルトコンストラクタ。
	}
	
	/**
	 * {@link BindingResult} と {@link MessageSource} を設定します。
	 * 
	 * @param result {@link BindingResult}
	 * @param messageSource  {@link MessageSource} 
	 * @param locale {@link Locale} ロケールの設定は（{@link MessageResourcesConfig}）を参照してください。
	 */
	public void setDefault(BindingResult result, MessageSource messageSource, Locale locale) {
		this.result = result;
		this.messageSource = messageSource;
		this.locale = locale;
		this.objectName = "form";
	}
	
	/**
	 * {@link BindingResult} を取得します。
	 * 
	 * @return {@link BindingResult}
	 */
	public BindingResult getResult() {
		return result;
	}

	/**
	 * {@link BindingResult} を設定します。
	 * 
	 * @param result  {@link BindingResult}
	 */
	public void setResult(BindingResult result) {
		this.result = result;
	}

	/**
	 * {@link MessageSource} を取得します。
	 * 
	 * @return {@link MessageSource}
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * {@link MessageSource} を設定します。
	 * 
	 * @param messageSource {@link MessageSource}
	 */
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * objectName を取得します。
	 * 
	 * @return objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * objectName を設定します。
	 * 
	 * @param objectName objectName
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * {@link BindingResult} にフィールドエラーを追加
	 * 
	 * @param field フィールドID
	 * @param rejectedValue 画面項目値（要因となった（入力）値）
	 * @param code メッセージコード
	 */
	public void addFieldError(String field, String rejectedValue, String code) {
		result.addError(
			new FieldError(objectName, field, rejectedValue, false, null, null, 
				messageSource.getMessage(code, null, locale))
		);
	}

	/**
	 * {@link BindingResult} にフィールドエラーを追加(引数あり)
	 * 
	 * @param field フィールドID
	 * @param rejectedValue 画面項目値（要因となった（入力）値）
	 * @param code メッセージコード
	 * @param args メッセージに渡す引数
	 */
	public void addFieldError(String field, String rejectedValue, String code, Object[] args) {
		
		result.addError(
			new FieldError(objectName, field, rejectedValue, false, null, null, 
				messageSource.getMessage(code, args, locale))
		);
	}

	/**
	 * メッセージ内容からフィールドエラーを生成。
	 * 
	 * @param field フィールドID
	 * @param rejectedValue 画面項目値（要因となった（入力）値）
	 * @param message メッセージ内容
	 */
	public void addFieldErrorMessage(String field, String rejectedValue, String message) {
		result.addError(
			new FieldError(objectName, field, rejectedValue, false, null, null, message)
		);
	}

	/**
	 * {@link BindingResult} にオブジェクトエラーを追加
	 * 
	 * @param code メッセージコード
	 */
	public void addObjectError(String code) {
		result.addError(
			new ObjectError(objectName, 
				messageSource.getMessage(code, null, locale)
			)
		);
	}

	/**
	 * {@link BindingResult} にオブジェクトエラーを追加(引数あり)
	 * 
	 * @param code メッセージコード
	 * @param args メッセージに渡す引数
	 */
	public void addObjectError(String code, Object[] args) {
		result.addError(
			new ObjectError(objectName, 
				messageSource.getMessage(code, args, locale)
			)
		);
	}
}
