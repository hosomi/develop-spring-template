package jp.template.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class Pagination {

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final String URL_PARAM_PAGE = "page";
	public static final String URL_PARAM_PERPAGE = "max";

	public static final int LEFT_EDGE = 2;
	public static final int RIGHT_EDGE = 2;
	public static final int LEFT_CURRENT = 2;
	public static final int RIGHT_CURRENT = 3;

	private final long page;
	private final long perPage;
	private final long totalCount;

	public Pagination(long page, long perPage, long totalCount) {
		this.page = page;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}

	public long getPage() {
		return this.page;
	}

	public long getPerPage() {
		return this.perPage;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public long getTotalPage() {
		return (long) Math.ceil(this.totalCount / (double) this.perPage);
	}

	public boolean hasPrev() {
		return this.page > 1;
	}

	public boolean hasNext() {
		return this.page < getTotalPage();
	}

	public long getFirstPage() {
		return this.totalCount == 0 ? 0 : ((this.page - 1) * this.perPage) + 1;
	}

	public long getLastPage() {
		long last = this.page * this.perPage;
		return last < this.totalCount ? last : this.totalCount;
	}

	public List<Long> getPages() {
		return getPages(LEFT_EDGE, LEFT_CURRENT, RIGHT_CURRENT, RIGHT_EDGE);
	}

	public List<Long> getPages(int leftEdge, int leftCurrent, int rightCurrent, int rightEdge) {
		long last = 0;
		long pages = getTotalPage();
		List<Long> result = new LinkedList<>();
		for (long i = 1; i <= pages; i++) {
			if ((i <= leftEdge) || ((i > (this.page - leftCurrent - 1)) && (i < (this.page + rightCurrent)))
					|| (i > pages - rightEdge)) {
				if (last + 1 != i) {
					result.add(-1L);
				}
				result.add(i);
				last = i;
			}
		}
		return result;
	}

	public String getUrlForOtherPage(HttpServletRequest request, long page) throws UnsupportedEncodingException {
		return getUrlForOtherPage(request, page, DEFAULT_ENCODING);
	}

	public String getUrlForOtherPage(HttpServletRequest request, long page, String encoding)
			throws UnsupportedEncodingException {
		Map<String, String[]> params = request.getParameterMap();
		StringBuilder builder = new StringBuilder();
		builder.append(request.getRequestURI());
		builder.append("?");
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String key = entry.getKey();
			for (String value : entry.getValue()) {
				if (!StringUtils.equals(URL_PARAM_PAGE, key)) {
					builder.append(URLEncoder.encode(key, encoding));
					builder.append("=");
					builder.append(URLEncoder.encode(value, encoding));
					builder.append("&");
				}
			}
		}
		builder.append(URL_PARAM_PAGE);
		builder.append("=");
		builder.append(page);
		return builder.toString();
	}
}
