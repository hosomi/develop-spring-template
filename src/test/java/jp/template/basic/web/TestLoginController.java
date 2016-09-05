package jp.template.basic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.template.Application;
import jp.template.basic.data.TestDataResource;
import jp.template.basic.data.TestLoginUserData;

/**
 * SpringSecurity のテスト。
 * 
 * @author hosomi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestLoginController {
	@Autowired
	private WebApplicationContext context;

	@Rule
	@Autowired
	public TestDataResource testDataResource;

	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).addFilter(springSecurityFilterChain).build();
	}

	/**
	 * ログイン画面にアクセスできるかテストする。
	 * 
	 * @throws Exception ログイン画面にアクセスできない。
	 */
	@Test
	public void testLoginIndex() throws Exception {
		this.mvc.perform(get("/login")).andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("login"))
				.andExpect(xpath("/html/head/title").string("ログイン"));
	}

	/**
	 * 認証処理（許可、非許可）ができるかテストする。
	 * 
	 * @throws Exception 認証処理が正常ではない。
	 */
	@Test
	public void testLoginProcess() throws Exception {
		// 存在しないユーザでログインを試みる
		this.mvc.perform(
			formLogin()
				.loginProcessingUrl("/login/auth")
				.user("username", "testdata3")
				.password("password", "testdata3pass")
			)
			.andExpect(status().isFound()).andExpect(redirectedUrl("/login?error=authentication"))
			.andExpect(unauthenticated()).andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION",isA(BadCredentialsException.class)))
		;

		// 使用できないユーザ ( enabled = 0 ) でログインを試みる。
		// この運用は現在提供していない為、実装時は下記コメントを解除しチェックする。
//		this.mvc.perform(
//			formLogin()
//				.loginProcessingUrl("/login/auth")
//				.user("username", "*****")
//				.password("password", "******")
//			)
//			.andExpect(status().isFound()).andExpect(redirectedUrl("/login?error=authentication")).andExpect(unauthenticated())
//			.andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION", isA(DisabledException.class)))
//		;

		// ログイン可能なユーザでログインする
		this.mvc.perform(
			formLogin()
				.loginProcessingUrl("/login/auth")
				.user("username", "testdata1")
				.password("password", "testdata1pass")
			)
			.andExpect(status().isFound()).andExpect(redirectedUrl("/menu"))
			.andExpect(authenticated().withUsername("testdata1"))
		;
	}

	/**
	 * BCrypt によるハッシュ処理のテストを行う。
	 * 
	 * @throws Exception BCrypt によるハッシュ処理が異常。
	 */
	@Test
	public void testEncode() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();

		// 認証済みとする。
		SecurityContext securityContext;
		Authentication authentication = new UsernamePasswordAuthenticationToken(TestLoginUserData.getLoginUserData(),
		TestLoginUserData.getLoginUserData().getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);

		// 認証先のページからハッシュ化した値を取得する。
		MvcResult result = mvc.perform(get("/hash/encode?password=testdata1pass")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();
		String crypt = result.getResponse().getContentAsString();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// 内部で生成したハッシュ化された値とコントローラから生成したハッシュ化された値と照合し同じか判定する。
		assertThat(passwordEncoder.matches("testdata1pass", crypt), is(true));
	}

}
