package jp.template.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * ページング
 * 
 * <ul>
 * <li>全体の件数、１ページあたりの表示件数からページ数を計算。</li>
 * 
 * </ul>
 * 
 * @author hosomi.
 */
public class Pagination {

	/** {@link URLEncoder#encode(String, String)} の文字コード。*/
	public static final String DEFAULT_ENCODING = "UTF-8";

	/** GET パラメータ・page。*/
	public static final String URL_PARAM_PAGE = "page";
	
//	/** GET パラメータ・max。*/
//	public static final String URL_PARAM_PERPAGE = "max";

	/** ページ総数が多い場合に開始ページを表示するページ数。*/
	public static final int LEFT_EDGE = 2;
	
	/** ページ総数が多い場合に終了ページを表示するページ数。*/
	public static final int RIGHT_EDGE = 2;
	
	/** 現在ページの位置から開始位置の生成ページと判定する位置。*/
	public static final int LEFT_CURRENT = 2;
	
	/** 現在ページの位置から終了位置の生成ページと判定する位置。*/
	public static final int RIGHT_CURRENT = 3;

	/** 現在のページ。*/
	private final long page;
	
	/** 1ページあたりの表示件数。*/
	private final long perPage;
	
	/** 全体の件数。*/
	private final long totalCount;

	/**
	 * コンストラクタ。
	 * 
	 * @param page 現在のページ。
	 * @param perPage 1ページあたりの表示件数。
	 * @param totalCount 全体の件数。
	 */
	public Pagination(long page, long perPage, long totalCount) {
		this.page = page;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}

	/**
	 * 現在のページを取得する。
	 * 
	 * @return 現在のページ。
	 */
	public long getPage() {
		return this.page;
	}

	/**
	 * 1ページあたりの表示件数を取得する。
	 * 
	 * @return 1ページあたりの表示件数。
	 */
	public long getPerPage() {
		return this.perPage;
	}

	/**
	 * 全体の件数を取得する。
	 * 
	 * @return 全体の件数。
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 全体ページ数({@link #totalCount} ÷ {@link #perPage})を取得する。
	 * 
	 * @return 全体ページ数。
	 */
	public long getTotalPage() {
		return (long) Math.ceil(this.totalCount / (double) this.perPage);
	}

	/**
	 * 前ページが存在するか判定する。
	 * 
	 * @return true:前ページが存在。
	 */
	public boolean hasPrev() {
		return this.page > 1;
	}

	/**
	 * 次ページが存在するか判定する。
	 * 
	 * @return true:次ページが存在。
	 */
	public boolean hasNext() {
		return this.page < getTotalPage();
	}

	/**
	 * 現在のページから表示するデータの件数の開始位置を取得する。
	 * 
	 * @return 開始位置。
	 */
	public long getFirstPage() {
		return this.totalCount == 0 ? 0 : ((this.page - 1) * this.perPage) + 1;
	}

	/**
	 * 現在のページから表示するデータの件数の終了位置を取得する。
	 * 
	 * @return 終了位置。
	 */
	public long getLastPage() {
		long last = this.page * this.perPage;
		return last < this.totalCount ? last : this.totalCount;
	}

	/**
	 * 生成するページ番号を全て取得する。
	 * 
	 * @return 生成するページ番号を全て。
	 */
	public List<Long> getPages() {
		return getPages(LEFT_EDGE, LEFT_CURRENT, RIGHT_CURRENT, RIGHT_EDGE);
	}

	/**
	 * 生成するページ番号を全て取得する。
	 * 
	 * @param leftEdge ページ総数が多い場合に開始ページを表示するページ数。
	 * @param leftCurrent 現在ページの位置から開始位置の生成ページと判定する位置。
	 * @param rightCurrent 現在ページの位置から終了位置の生成ページと判定する位置。
	 * @param rightEdge ページ総数が多い場合に終了ページを表示するページ数。
	 * @return 生成するページ番号を全て。
	 */
	public List<Long> getPages(int leftEdge, int leftCurrent, int rightCurrent, int rightEdge) {
		long last = 0;
		long pages = getTotalPage();
		List<Long> result = new LinkedList<>();
		for (long i = 1; i <= pages; i++) {
			if ((i <= leftEdge) || ((i > (this.page - leftCurrent - 1)) && (i < (this.page + rightCurrent))) || (i > pages - rightEdge)) {
				if (last + 1 != i) {
					result.add(-1L);
				}
				result.add(i);
				last = i;
			}
		}
		return result;
	}

	/**
	 * ページ番号の href を生成する。
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param page ページ番号（遷移先ページ番号）
	 * @return ページ番号の href
	 * @throws UnsupportedEncodingException {@link UnsupportedEncodingException}
	 */
	public String getUrlForOtherPage(HttpServletRequest request, long page) throws UnsupportedEncodingException {
		return getUrlForOtherPage(request, page, DEFAULT_ENCODING);
	}

	/**
	 * ページ番号の href を生成する。
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param page ページ番号（遷移先ページ番号）
	 * @param encoding エンコーディングの文字コード名。
	 * @return ページ番号の href
	 * @throws UnsupportedEncodingException {@link UnsupportedEncodingException}
	 */
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
