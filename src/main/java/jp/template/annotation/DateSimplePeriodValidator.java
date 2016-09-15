package jp.template.annotation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 期間チェックアノテーションの実装。
 * 
 * 
 * @author hosomi.
 */
public class DateSimplePeriodValidator implements ConstraintValidator<DateSimplePeriod, Object> {

	/** フィールド名・開始日。*/
	private String fieldNameFrom;
	/** フィールド名・終了日。*/
	private String fieldNameTo;
	/** 期間チェックエラー時のメッセージ。*/
	private String message;
	
	/** 日付書式。*/
	private String format;
	
	@Override
	public void initialize(DateSimplePeriod annotation) {
		this.fieldNameFrom = annotation.fieldNameFrom();
		this.fieldNameTo = annotation.fieldNameTo();
		this.format = annotation.format();
		this.message = annotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		// 入力値受け取り。
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String from = (String) beanWrapper.getPropertyValue(fieldNameFrom);
		String to = (String) beanWrapper.getPropertyValue(fieldNameTo);

		// 開始日、終了日の両方が空の場合 OK とする。
		if (StringUtils.isBlank(from) && StringUtils.isBlank(to)) {
			return true;
		}

		// 開始日が空の場合。
		if (StringUtils.isBlank(from)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{jp.template.validator.constraints.DateSimplePeriod.blank.from.message}")
				.addPropertyNode(fieldNameFrom)
				.addConstraintViolation();
			return false;
		}
		
		// 終了日が空の場合。
		if (StringUtils.isBlank(to)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{jp.template.validator.constraints.DateSimplePeriod.blank.from.message}")
				.addPropertyNode(fieldNameTo)
				.addConstraintViolation();
			return false;
		}
		
		// 日付のフォーマットチェック & 期間チェックに Java8 TimeAPI を利用。
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDate ldFrom = null,ldTo = null;

		// 日付のフォーマットチェック・開始日
		try {
			ldFrom = LocalDate.parse(from, dtf);
		} catch(DateTimeParseException e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{jp.template.validator.constraints.DateSimplePeriod.format.message}")
				.addPropertyNode(fieldNameFrom)
				.addConstraintViolation();
		}

		// 日付のフォーマットチェック・終了日
		try {
			ldTo = LocalDate.parse(to, dtf);
		} catch(DateTimeParseException e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{jp.template.validator.constraints.DateSimplePeriod.format.message}")
				.addPropertyNode(fieldNameTo)
				.addConstraintViolation();
		}

		// 開始日、終了日の初期化に失敗 = フォーマットエラーの場合、エラーとして返す。
		if (Objects.isNull(ldFrom) || Objects.isNull(ldTo)) {
			return false;
		}

		// 期間チェック（開始日 <= 終了日）。
		Period period = Period.between(ldFrom, ldTo);
		if (period.getDays() < 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(fieldNameTo)
				.addConstraintViolation();
			return false;
		}

		return true;
	}
}
