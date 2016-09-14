package jp.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { DateSimplePeriodValidator.class })
public @interface DateSimplePeriod {

	String message() default "{jp.template.validator.constraints.DateSimplePeriod.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/** 開始日のフィールド名。 */
	String fieldNameFrom() default "from";

	/** 終了日のフィールド名。 */
	String fieldNameTo() default "to";

	/** 日付書式*/
	String format() default "yyyy/MM/dd";
	
	@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		DateSimplePeriod[] value();
	}
}
