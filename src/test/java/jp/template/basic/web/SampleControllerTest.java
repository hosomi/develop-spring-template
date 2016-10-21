package jp.template.basic.web;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.template.Application;
import jp.template.basic.data.LoginUserTestData;

/**
 * サンプル用コントローラテスト。
 * 
 * @author hosomi.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SampleControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	/**
	 * 認証済みとして設定する。
	 * 
	 * @throws Exception 認証済みとして設定できない。
	 */
	@Before
	public void setUp() throws Exception {

		// 認証済みとする。
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
		SecurityContext securityContext;
		Authentication authentication = new UsernamePasswordAuthenticationToken(LoginUserTestData.getLoginUserData(),
		LoginUserTestData.getLoginUserData().getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	/**
	 * 認証済みで Datetimepicker サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  Datetimepicker サンプル画面にアクセスできない。
	 */
	@Test
	public void testDatetimepickerIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/datetimepicker"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/datetimepicker"))
			.andExpect(xpath("/html/head/title").string("コントロール:DateTimePicker"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	/**
	 * 認証済みで TypeAhead サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  TypeAhead サンプル画面にアクセスできない。
	 */
	@Test
	public void testTypeaheadIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/typeahead"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/typeahead"))
			.andExpect(xpath("/html/head/title").string("コントロール:TypeAhead"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	

	/**
	 * 認証済みで Top に戻る サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  Top に戻る サンプル画面にアクセスできない。
	 */
	@Test
	public void testBacktotopIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/backtotop"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/backtotop"))
			.andExpect(xpath("/html/head/title").string("コントロール:Top に戻る"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	/**
	 * 認証済みで Bootstrap Switch サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  Bootstrap Switch サンプル画面にアクセスできない。
	 */
	@Test
	public void testBootstrapSwitchIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/bootstrap-switch"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/bootstrap-switch"))
			.andExpect(xpath("/html/head/title").string("コントロール:Bootstrap Switch"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}



	/**
	 * 認証済みで Modal サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  Modal サンプル画面にアクセスできない。
	 */
	@Test
	public void testModalIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/modal"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/modal"))
			.andExpect(xpath("/html/head/title").string("コントロール:Modal"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	/**
	 * 認証済みで TypeAheadTag サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  TypeAheadTag サンプル画面にアクセスできない。
	 */
	@Test
	public void testTypeaheadTagIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/controlles/typeahead/tag"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/typeaheadtag"))
			.andExpect(xpath("/html/head/title").string("コントロール:TypeAheadTag"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	/**
	 * 認証済みで Typeahed の Rest レスポンスをテストする。
	 * 
	 * @throws Exception 認証済みで Typeahed の Rest レスポンスが取得できない。
	 */
	@Test
	public void testRestTypeahead() throws Exception {

		ResultActions actions = mvc.perform(get("/sample/controlles/typeahead/{queryStr}","0000000001"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{\"id\":1,\"code\":\"0000000001\",\"name\":\"商品名１\",\"kana\":\"ショウヒンメイイチ\",\"note\":\"商品名１の備考など\",\"tokens\":[\"0000000001\",\"商品名１\",\"ショウヒンメイイチ\"]}]"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}
	
	/**
	 * 認証済みで部門の Typeahed の Rest レスポンスをテストする。
	 * 
	 * @throws Exception 認証済みで部門の Typeahed の Rest レスポンスが取得できない。
	 */
	@Test
	public void testRestDptTypeahead() throws Exception {

		ResultActions actions = mvc.perform(get("/sample/controlles/typeahead/dpt/{queryStr}","1100"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{\"cddpt\":\"1100\",\"cdupperdpt\":\"1000\",\"nmdpt\":\"人事課\",\"nmshortdpt\":\"人事\",\"showodr\":3,\"dtavlst\":\"19000101\",\"dtavled\":\"99991231\",\"tokens\":[\"1100\",\"1000\",\"人事課\",\"人事\"]}]"));
	
		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}
	
	
	
}
