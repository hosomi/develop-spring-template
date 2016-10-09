package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.template.domain.Dpt;

/**
 * Dpt Mapper
 * 
 * @author hosomi.
 */
@Mapper
public interface DptMapper {

	/**
	 * 全件取得する。
	 * 
	 * @return 全件。
	 */
	List<Dpt> selectAll();
	
	/**
	 * TypeAhed 用曖昧検索。
	 * 
	 * @param keyword 入力キーワード。
	 * @return キーワードに紐づく Dpt 一覧を返します、最大２０件まで。
	 */
	List<Dpt> selectListTypeAhead(@Param("keyword") String keyword);

	/**
	 * 部門コードから一件を取得。
	 * 
	 * @param cddpt 部門コード。
	 * @return cddpt に一致する部門
	 */
	Dpt selectByCddpt(@Param("cddpt") String cddpt);

	
	/**
	 * 全体の件数を取得する。
	 * 
	 * @return 全体の件数。
	 */
	int count();

	/**
	 * プライマリキーから件数を取得(1 or 0)。
	 * 
	 * @param entity 検索条件。
	 * @return プライマリキーから件数を取得したカウント値(1 or 0)。
	 */
	int countByPrimaryKey(Dpt entity);
	
	/**
	 * ページ毎のデータを取得する。
	 * 
	 * @param first 開始位置
	 * @param last 終了位置
	 * @return ページ毎の一覧。
	 */
	List<Dpt> pagingCurrent(@Param("first") long first, @Param("last") long last);
}
