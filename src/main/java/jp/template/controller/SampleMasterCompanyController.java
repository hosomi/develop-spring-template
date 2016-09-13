package jp.template.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.template.domain.Company;
import jp.template.form.SampleMasterCompanyForm;
import jp.template.mapper.CompanyMapper;

/**
 * 会社マスタサンプル。
 * <UL>
 *   <LI>MyBatis XML Mapper</LI>
 *   <LI>XML : src/main/resources/jp/template/mapper/CompanyMapper.xml</LI>
 *   <LI>interface : src/main/java/jp/template/mapper/CompanyMapper.java</LI>
 * </UL>
 * ※利用する時はアノテーション定義で DML 文を定義している場合と何も変わりありません。
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/sample/master/company")
public class SampleMasterCompanyController {

	@Autowired
	CompanyMapper companyMapper;

	/**
	 * マスタサンプルオープン時のイベント処理。
	 * 
	 * @param form マスタサンプルフォーム {@link SampleMasterCompanyForm}
	 * @param model {@link Model}
	 * @return /resources/templates/sample/master/company.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(SampleMasterCompanyForm form, Model model) {

		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSSSSSSSS");
		// insert sample
		// セットしていない項目は null(id 以外)
		Company entity = new Company();
		entity.setName("株式会社てすと".concat(f.format(d)));
		companyMapper.insert(entity);
		
		// 初期検索（全件）。
		form.setList(companyMapper.selectAll());

		return "sample/master/company";
	}
	
}
