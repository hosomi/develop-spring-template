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
}
