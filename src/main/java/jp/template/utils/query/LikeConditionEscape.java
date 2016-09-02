package jp.template.utils.query;

public class LikeConditionEscape {

	private final boolean escapeFullWithWildcards;

	public static final char LIKE_ESC_CHAR = '~';

	private LikeConditionEscape(boolean escapeFullWithWildcards) {
		this.escapeFullWithWildcards = escapeFullWithWildcards;
	}

	public static LikeConditionEscape withFullWidthWildcardsEscape() {
		return new LikeConditionEscape(true);
	}

	public static LikeConditionEscape withoutFullWidthWildcardsEscape() {
		return new LikeConditionEscape(false);
	}

	public StringBuilder toLikeCondition(String condition, StringBuilder likeCondition) {
		StringBuilder storingLikeCondition = likeCondition;
		if (storingLikeCondition == null) {
			storingLikeCondition = new StringBuilder();
		}
		if (condition == null) {
			return storingLikeCondition;
		}
		for (int i = 0; i < condition.length(); i++) {
			char c = condition.charAt(i);
			if (c == LIKE_ESC_CHAR) {
				storingLikeCondition.append(LIKE_ESC_CHAR);
			} else if (c == '%' || c == '_') {
				storingLikeCondition.append(LIKE_ESC_CHAR);
			} else if (escapeFullWithWildcards && (c == '＿' || c == '％')) {
				storingLikeCondition.append(LIKE_ESC_CHAR);
			}
			storingLikeCondition.append(c);
		}
		return storingLikeCondition;
	}

	public String toLikeCondition(String condition) {
		if (condition == null) {
			return null;
		}
		return toLikeCondition(condition, new StringBuilder()).toString();
	}

	public String toStartingWithCondition(String condition) {
		if (condition == null) {
			return null;
		}
		return toLikeCondition(condition, new StringBuilder()).append("%").toString();
	}

	public String toEndingWithCondition(String condition) {
		if (condition == null) {
			return null;
		}
		return toLikeCondition(condition, new StringBuilder("%")).toString();
	}

	public String toContainingCondition(String condition) {
		if (condition == null) {
			return null;
		}
		return toLikeCondition(condition, new StringBuilder("%")).append("%").toString();
	}
}
