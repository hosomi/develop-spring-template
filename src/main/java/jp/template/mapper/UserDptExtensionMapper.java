package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.template.domain.UserDptExtension;

/**
 * UserDptEntension Mapper.
 * 
 * <pre>
 * User 単位で UserDpt Dpt をそれぞれ外部結合し取得。
 * </pre>
 * 
 * @author hosomi.
 */
@Mapper
public interface UserDptExtensionMapper {

	/**
	 * User 単位で UserDpt, Dpt をそれぞれ外部結合し取得する。
	 * 
	 * @return User 単位の UserDpt, Dpt。
	 */
	List<UserDptExtension> selectUserAll();
}
