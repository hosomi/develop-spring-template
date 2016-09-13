package jp.template.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.template.domain.Company;

/**
 * Company Mapper
 * 
 * @author hosomi.
 */
@Mapper
public interface CompanyMapper {

	/**
	 * 全件取得する。
	 * 
	 * @return 全件。
	 */
	List<Company> selectAll();
	
	/**
	 * 1 件追加する。
	 * 
	 * @param entity {@link Company}
	 * @return 追加件数（1）
	 */
	int insert(Company entity);
}
