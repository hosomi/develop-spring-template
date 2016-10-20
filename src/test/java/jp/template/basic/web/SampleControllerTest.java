package jp.template.basic.web;

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
	 * 認証済みで Modal サンプル画面にアクセスできるかテストする。
	 * 
	 * @throws Exception 認証済みで  Modal サンプル画面にアクセスできない。
	 */
	@Test
	public void testModalIndex() throws Exception {

		// 認証済みでアクセスし対象ページの内容を取得する。
		mvc.perform(get("/sample/controlles/modal"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated()) // 認証情報ありか確認
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8")).andExpect(view().name("sample/controlles/modal"))
			.andExpect(xpath("/html/head/title").string("コントロール:Modal"));
	}
	
	
}
