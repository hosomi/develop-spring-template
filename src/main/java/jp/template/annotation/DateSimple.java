package jp.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 日付チェックアノテーション（バリデーション）。 年(4桁)、月（2桁）、日（2桁）を想定しています。
 * <ul>
 * <li>デフォルト書式：yyyy/MM/dd = @DateSimple</li>
 * <li>記号なし書式：yyyyMMdd = @DateSimple(format="yyyyMMdd")</li>
 * </ul>
 * 
 * @author hosomi.
 */
@Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD,
		java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR,
		java.lang.annotation.ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={DateSimpleValidator.class})
public @interface DateSimple {

	/**
	 * メッセージ内容。
	 * <p>リソース：src/main/resources/i18n/messages_[lang]_[locale].properties</p>
	 * 
	 * @return キー：jp.template.validator.constraints.DateSimple.message
	 */
	String message() default "{jp.template.validator.constraints.DateSimple.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * フォーマット書式。
	 * @return デフォルト："yyyy/MM/dd"
	 */
	String format() default "yyyy/MM/dd";

	@Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD,
			java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR,
			java.lang.annotation.ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		DateSimple[] value();
	}
}
