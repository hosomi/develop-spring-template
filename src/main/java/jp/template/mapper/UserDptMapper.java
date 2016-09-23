package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.template.domain.UserDpt;

/**
 * UserDpt Mapper
 * 
 * @author hosomi.
 */
@Mapper
public interface UserDptMapper {

	/**
	 * 全件取得する。
	 * 
	 * @return 全件。
	 */
	List<UserDpt> selectAll();
	
	/**
	 * 1 件追加する。
	 * 
	 * @param entity {@link UserDpt}
	 * @return 追加件数（1）
	 */
	int insert(UserDpt entity);
	
	/**
	 * ユーザ ID単位で削除する。
	 * 
	 * @param entity {@link UserDpt}
	 * @return 削除件数
	 */
	int deleteFromloginuserid(UserDpt entity);
}
