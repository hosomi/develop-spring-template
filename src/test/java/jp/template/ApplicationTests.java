package jp.template;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.template.domain.User;
import jp.template.mapper.UserMapper;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private UserMapper usermapper;
	
	@Test
	public void contextLoads() {

		// ログインできるユーザ情報が存在しているかテスト。
		List<User> list = usermapper.selectAll();
		Assert.assertTrue("user list empty!", list.size() > 0);
	}

}
