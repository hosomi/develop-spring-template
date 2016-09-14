package jp.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 日付アノテーション。 年(4桁)、月（2桁）、日（2桁）を想定しています。
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

	String message() default "{jp.template.validator.constraints.DateSimple.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/** 日付書式*/
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
