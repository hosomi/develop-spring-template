package jp.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 日付範囲チェックアノテーション（バリデーション）。
 * 利用する場合はフィールドではなくクラスに開始日と終了日を指定し定義します。
 * 
 * <p>例：</p>
 * <pre>@DateSimplePeriod(fieldNameFrom="from", fieldNameTo="to")
 * public class SampleValidatorForm {
 *   ・・・
 * }</pre>
 * 
 * 
 * @author hosomi.
 */
@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { DateSimplePeriodValidator.class })
public @interface DateSimplePeriod {

	/**
	 * メッセージ内容。
	 * <p>リソース：src/main/resources/i18n/messages_[lang]_[locale].properties</p>
	 * 
	 * @return キー：jp.template.validator.constraints.DateSimplePeriod.message
	 */
	String message() default "{jp.template.validator.constraints.DateSimplePeriod.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 開始日のフィールド名
	 * 
	 * @return デフォルト："from"
	 */
	String fieldNameFrom() default "from";

	/**
	 * 終了日のフィールド名
	 * 
	 * @return デフォルト："to"
	 */
	String fieldNameTo() default "to";

	/**
	 * フォーマット書式。
	 * @return デフォルト："yyyy/MM/dd"
	 */
	String format() default "yyyy/MM/dd";

	@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		DateSimplePeriod[] value();
	}
}
