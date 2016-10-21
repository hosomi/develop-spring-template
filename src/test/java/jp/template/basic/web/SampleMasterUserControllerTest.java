package jp.template.basic.web;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
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
import jp.template.domain.User;

/**
 * ユーザマスタサンプルテスト。
 * 
 * @author hosomi.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SampleMasterUserControllerTest {

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
	 * 認証済みで画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで画面にアクセスできない。
	 */
	@Test
	public void testIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		ResultActions actions = mvc.perform(get("/sample/master/user"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/master/user"))
			.andExpect(xpath("/html/head/title").string("ユーザマスタ"));

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}

	/**
	 * 照会検索ボタンのテスト。
	 * 
	 * @throws Exception 照会検索ボタンが動作しない。
	 */
	@Test
	public void testInquiry() throws Exception {

		// サブミット（全件取得）中身まで精査しない。
		ResultActions actions = mvc.perform(post("/sample/master/user").with(csrf()));
		actions.andExpect(status().isOk()); 
		actions.andExpect(model().attribute("list", notNullValue())); // 検索結果の要素の確認。

		// サブミット（ユーザ指定）中身の精査。
		actions = mvc.perform(post("/sample/master/user").param("loginUserId", "test2").with(csrf()));
		actions.andExpect(status().isOk()); 
		actions.andExpect(model().attribute("list", hasItem(Matchers.<User>hasProperty("loginUserId", equalTo("test2"))))); // 結果に指定したユーザIDが入っているか確認する。

		// JUnit tests should include assert() or fail() 
		assertNotNull(actions);
	}
}
