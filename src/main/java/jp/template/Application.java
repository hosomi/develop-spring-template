package jp.template;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import jp.template.domain.User;
import jp.template.domain.UserBCrypt;
import jp.template.mapper.UserMapper;

/**
 * SpringBootApplication の実装。
 * 
 * @author hosomi.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

//	/** Logger. */
//	private static Logger logger = LogManager.getLogger();

//	@Autowired
//	private TodoMapper todoMapper;

	@Autowired
	private UserMapper userMapper;

	
	/**
	 * ローカル実行用。
	 * 
	 * @param args コマンドライン引数。
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * デプロイ時に実行されるメイン。
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	/**
	 * Spring Boot起動時にCommandLineRunner#runメソッドが呼び出される
	 */
	@Transactional
	@Override
	public void run(String... args) throws Exception {

//		Todo newTodo = new Todo();
//		newTodo.setTitle("TODO1");
//		newTodo.setDetails("TODO1詳細");
//
//		todoMapper.insert(newTodo); // 新しいTodoをインサートする
//		Todo loadedTodo = todoMapper.select(newTodo.getId()); // インサートしたTodoを取得して標準出力する
//		logger.debug("ID       : " + loadedTodo.getId());
//		logger.debug("TITLE    : " + loadedTodo.getTitle());
//		logger.debug("DETAILS  : " + loadedTodo.getDetails());
//		logger.debug("FINISHED : " + loadedTodo.isFinished());
//		
		
		User user = new UserBCrypt("test", "testpass");

		userMapper.insert(user);
		
		user = new UserBCrypt("test2","test2pass");
		userMapper.insert(user);
		
		
//		user = new UserBCrypt();
//		user.setLoginUserId("testdata1");
//		user.setPassword("testdata1pass");
//		userMapper.insert(user);
//		
//		user = new UserBCrypt();
//		user.setLoginUserId("testdata2");
//		user.setPassword("testdata2pass");
//		userMapper.insert(user);
		
	}

	/**
	 * H2 用のコンソールサーブレット実装。
	 * 
	 * @return {@link ServletRegistrationBean}
	 */
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

}
