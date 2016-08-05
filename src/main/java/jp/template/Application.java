package jp.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.Transactional;

import jp.template.domain.Todo;
import jp.template.mapper.TodoMapper;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
    @Autowired
    TodoMapper todoMapper; // Mapperをインジェクションする

    // Spring Boot起動時にCommandLineRunner#runメソッドが呼び出される
    @Transactional
    @Override
    public void run(String... args) throws Exception { 
    	
        Todo newTodo = new Todo();
        newTodo.setTitle("TODO1");
        newTodo.setDetails("TODO1詳細");

        todoMapper.insert(newTodo); // 新しいTodoをインサートする
//        for (int i = 0; i < 10000; i++) {
//        	todoMapper.insert(newTodo);
//    }
        Todo loadedTodo = todoMapper.select(newTodo.getId()); // インサートしたTodoを取得して標準出力する
        System.out.println("ID       : " + loadedTodo.getId());
        System.out.println("TITLE    : " + loadedTodo.getTitle());
        System.out.println("DETAILS  : " + loadedTodo.getDetails());
        System.out.println("FINISHED : " + loadedTodo.isFinished());

//        List<Todo> list = todoMapper.selectAll();
//        
//        for (Todo entity : list) {
//        	System.out.println("ID       : " + entity.getId());
//        }
    }
}
