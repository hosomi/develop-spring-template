package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.template.domain.Goods;


/**
 * Goods Mapper.
 * 
 * @author hosomi.
 */
@Mapper
public interface GoodsMapper {

	/**
	 * TypeAhed 用曖昧検索。
	 * 
	 * @param keyword 入力キーワード（code,name,kana）。
	 * @return キーワードに紐づく goods 一覧を返します、最大２０件まで。
	 */
	@Select("SELECT rowno, id, code, name, kana, note FROM (SELECT ROWNUM() as rowno, id, code, name, kana, note FROM goods WHERE code LIKE '%' || #{keyword} || '%' ESCAPE '~' OR name LIKE '%' || #{keyword} || '%' ESCAPE '~' OR kana LIKE '%' || #{keyword} || '%' ESCAPE '~') WHERE rowno BETWEEN 1 AND 20")
	List<Goods> selectListTypeAhead(@Param("keyword") String keyword);
	
	/**
	 * 全件を取得する。
	 * 
	 * @return Goods を全件取得します。
	 */
	@Select("SELECT  id, code, name, kana, note  FROM goods")
	List<Goods> selectAll();

}
