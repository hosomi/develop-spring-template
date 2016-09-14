package jp.template.annotation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * @DateSimple の値検証の実装。
 * 
 * @author hosomi.
 */
public class DateSimpleValidator implements ConstraintValidator<DateSimple, String> {

	/** 書式。*/
	String format;

	@Override
	public void initialize(DateSimple annotation) {
		format = annotation.format();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext paramConstraintValidatorContext) {

		// 空は OK とする。
		if (StringUtils.isBlank(value)) {
			return true;
		}

		// Java8 Time API にて日付書式のチェック。
		try {
			LocalDate.parse(value, DateTimeFormatter.ofPattern(format));
		} catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
}
