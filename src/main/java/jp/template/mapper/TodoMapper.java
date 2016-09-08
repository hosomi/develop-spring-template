package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.template.domain.Todo;

/**
 * Todo Mapper.
 * 
 * @author hosomi.
 */
@Mapper
public interface TodoMapper {

	@Insert("INSERT INTO todo (title, details, finished) VALUES (#{title}, #{details}, #{finished})")
	@Options(useGeneratedKeys = true)
	void insert(Todo todo);

	@Select("SELECT id, title, details, finished FROM todo WHERE id = #{id}")
	Todo select(int id);

	@Select("SELECT id, title, details, finished FROM todo")
	List<Todo> selectAll();
	
	@Select("SELECT count(id) FROM todo")
	int count();
	
	@Select("SELECT id, title,details FROM (SELECT ROWNUM() AS rowno,id,title,details FROM todo) WHERE rowno BETWEEN #{first} AND #{last}")
	List<Todo> pagingCurrent(@Param("first") long first, @Param("last") long last);
	
}
