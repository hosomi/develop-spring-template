package jp.template.basic.web;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.template.Application;

/**
 * 非セキュアなリソースへのテスト。
 * （static, webjars など）
 * 
 * @author hosomi.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class NonSecureResourcesTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).addFilter(springSecurityFilterChain).build();
	}
	
	
	/**
	 * リソース(static)にアクセスできるか確認。
	 * 
	 * @throws Exception リソース(static)にアクセスできない。
	 */
	@Test
	public void testResourceStaticAccess() throws Exception {
	
		ResultActions actions = this.mvc.perform(get("/static/css/bootstrap.overwrite.css"))
			.andExpect(SecurityMockMvcResultMatchers.unauthenticated())
			.andExpect(status().isOk());

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}
	
	/**
	 * リソース(webjars)にアクセスできるか確認。
	 * 
	 * @throws Exception リソース(webjars)にアクセスできない。
	 */
	@Test
	public void testResourceWebjarsAccess() throws Exception {

		// webjars の bootstrap(バージョン隠蔽されているかも確認)
		ResultActions actions = this.mvc.perform(get("/webjars/bootstrap/css/bootstrap.css"))
			.andExpect(SecurityMockMvcResultMatchers.unauthenticated())
			.andExpect(status().isOk())
		;
		
		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}
	
}
