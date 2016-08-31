package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.template.domain.User;

/**
 * User Mapper.
 * 
 * <pre>
 * 認証情報としても利用。
 * </pre>
 * 
 * @author hosomi.
 */
@Mapper
public interface UserMapper {

	/**
	 * User テーブルに１件挿入する。
	 * <p>id カラムは自動採番。</p>
	 * 
	 * @param user {@link User}
	 */
	@Insert("INSERT INTO USER (loginUserId, password) VALUES (#{loginUserId}, #{password})")
	void insert(User user);

	/**
	 * User テーブルに１件更新する。
	 * <p>パスワードのみ</p>
	 * 
	 * @param user {@link User}
	 */
	@Update("UPDATE	USER SET password = #{password} WHERE id = #{id}")
	void update(User user);

	/**
	 * loginUserId から検索する。
	 * 
	 * @param loginUserId ログインユーザID。
	 * @return {@link User}
	 */
	@Select("SELECT id, loginUserId, password  FROM USER WHERE loginUserId = #{loginUserId}")
	User select(@Param("loginUserId") String loginUserId);

	/**
	 * loginUserId から検索する。
	 *  ログインユーザID（サイン済み）を除く。
	 * 
	 * @param loginUserId ログインユーザID。
	 * @param systemLoginId ログインユーザID（サイン済み）。
	 * @return {@link User}
	 */
	@Select("SELECT id, loginUserId, password  FROM USER WHERE loginUserId = #{loginUserId} AND NOT EXISTS (SELECT * FROM USER USER_INNER WHERE USER_INNER.loginUserId = USER.loginUserId AND loginUserId = #{systemLoginId} )")
	User selectNotExistsSystemLoginId(@Param("loginUserId") String loginUserId, @Param("systemLoginId") String systemLoginId);

	/**
	 * 全件を取得する。
	 * 
	 * @return ユーザを全件取得します。
	 */
	@Select("SELECT id, loginUserId, password  FROM USER")
	List<User> selectAll();

	/**
	 * systemLoginId を除く全件を取得する。
	 * 
	 * @param systemLoginId ログインユーザID（サイン済み）。
	 * @return ログインユーザを除くユーザを全件取得します。
	 */
	@Select("SELECT id, loginUserId, password  FROM USER WHERE NOT EXISTS (SELECT * FROM USER USER_INNER WHERE USER_INNER.loginUserId = USER.loginUserId AND loginUserId = #{systemLoginId})")
	List<User> selectAllNotExistsSystemLoginId(@Param("systemLoginId") String systemLoginId);

	/**
	 * 対象ID（PKEY） を指定して削除する。
	 * 
	 * @param id ID（PKEY）
	 * @return　削除件数（１件））
	 */
	@Delete("DELETE FROM USER WHERE ID = #{id}")
	int delete(@Param("id") int id);

}
