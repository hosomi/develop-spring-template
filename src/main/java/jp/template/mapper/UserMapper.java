package jp.template.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.template.domain.User;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (loginUserId, password) VALUES (#{loginUserId}, #{password})")
    void insert(User user);

    @Select("SELECT loginUserId, password  FROM user WHERE loginUserId = #{loginUserId}")
    User select(String loginUserId);
	
}
