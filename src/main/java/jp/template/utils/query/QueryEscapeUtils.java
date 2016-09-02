package jp.template.utils.query;

public final class QueryEscapeUtils {
	private static final LikeConditionEscape WITH_FULL_WIDTH = LikeConditionEscape.withFullWidthWildcardsEscape();

	private static final LikeConditionEscape WITHOUT_FULL_WIDTH = LikeConditionEscape.withoutFullWidthWildcardsEscape();

	private QueryEscapeUtils() {
	}

	public static StringBuilder toLikeCondition(String condition, StringBuilder likeCondition) {
		return WITHOUT_FULL_WIDTH.toLikeCondition(condition, likeCondition);
	}

	public static String toLikeCondition(String condition) {
		return WITHOUT_FULL_WIDTH.toLikeCondition(condition);
	}

	public static String toStartingWithCondition(String condition) {
		return WITHOUT_FULL_WIDTH.toStartingWithCondition(condition);
	}

	public static String toEndingWithCondition(String condition) {
		return WITHOUT_FULL_WIDTH.toEndingWithCondition(condition);
	}

	public static String toContainingCondition(String condition) {
		return WITHOUT_FULL_WIDTH.toContainingCondition(condition);
	}

	public static LikeConditionEscape withFullWidth() {
		return WITH_FULL_WIDTH;
	}
}
