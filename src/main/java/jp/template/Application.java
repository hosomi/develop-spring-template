package jp.template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.Transactional;

import jp.template.domain.Todo;
import jp.template.mapper.TodoMapper;

/**
 * SpringBootApplication の実装。
 * 
 * @author hosomi.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private TodoMapper todoMapper; // Mapper をインジェクションする
	
	/**
	 * ローカル実行用。
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// デプロイ時に実行されるメイン。
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	// Spring Boot起動時にCommandLineRunner#runメソッドが呼び出される
	@Transactional
	@Override
	public void run(String... args) throws Exception {

		Todo newTodo = new Todo();
		newTodo.setTitle("TODO1");
		newTodo.setDetails("TODO1詳細");

		todoMapper.insert(newTodo); // 新しいTodoをインサートする
		Todo loadedTodo = todoMapper.select(newTodo.getId()); // インサートしたTodoを取得して標準出力する
		logger.debug("ID       : " + loadedTodo.getId());
		logger.debug("TITLE    : " + loadedTodo.getTitle());
		logger.debug("DETAILS  : " + loadedTodo.getDetails());
		logger.debug("FINISHED : " + loadedTodo.isFinished());
/*		for (int i = 0; i < 10000; i++) {
			todoMapper.insert(newTodo);
		}

		List<Todo> list = todoMapper.selectAll();

		for (Todo entity : list) {
			System.out.println("ID       : " + entity.getId());
		}
*/
	}

}
