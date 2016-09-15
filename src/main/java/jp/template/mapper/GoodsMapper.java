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
	@Select("SELECT id, code, name, kana, note  FROM goods")
	List<Goods> selectAll();
	
	/**
	 * 全体の件数を取得する。
	 * 
	 * @return 全体の件数。
	 */
	@Select("SELECT count(id) FROM goods")
	int count();

	/**
	 * 商品コードの件数を取得する。
	 * 
	 * @param entity {@link Goods}
	 * @return 商品コードの件数。
	 */
	@Select("SELECT count(id) FROM goods where code = #{entity.code}")
	int countFromCode(@Param("entity") Goods entity);
	
	/**
	 * ページ毎のデータを取得する。
	 * 
	 * @param first 開始位置
	 * @param last 終了位置
	 * @return ページ毎の一覧。
	 */
	@Select("SELECT id, code, name, kana, note FROM (SELECT ROWNUM() AS rowno, id, code, name, kana, note FROM goods) WHERE rowno BETWEEN #{first} AND #{last}")
	List<Goods> pagingCurrent(@Param("first") long first, @Param("last") long last);
}
