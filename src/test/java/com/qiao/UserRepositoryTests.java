package com.qiao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.qiao.domain.User;
import com.qiao.repository.readonly.ReadonlyUserRepository;
import com.qiao.repository.tx.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReadonlyUserRepository readonlyUserRepository;

	
	@Test
	public void test2() throws Exception{
		readonlyUserRepository.save(new User("AAA", 10));
	}
	
	 @Test
	 public void test3() throws Exception {
		 User u1 = readonlyUserRepository.findByName("AAA");
		 System.out.println("第一次查询：" + u1.getAge());

		 User u2 = readonlyUserRepository.findByName("AAA");
		 System.out.println("第二次查询：" + u2.getAge());
	}

	@Test
//	@Transactional(value="transactionManagerPrimary")
	@Transactional(value="transactionManagerSecondary")
	@Rollback
	public void test() throws Exception {

		// 创建10条记录
		userRepository.save(new User("AAA", 10));
		userRepository.save(new User("BBB", 20));
		userRepository.save(new User("CCC", 30));
		userRepository.save(new User("DDD", 40));
		userRepository.save(new User("EEE", 50));
		userRepository.save(new User("FFF", 60));
		userRepository.save(new User("GGG", 70));
		userRepository.save(new User("HHH", 80));
		userRepository.save(new User("III", 90));
		userRepository.save(new User("JJJ", 100));

		// 测试findAll, 查询所有记录
		Assert.assertEquals(10, userRepository.findAll().size());

		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

		// 测试findUser, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

		// 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
		Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

		// 测试删除姓名为AAA的User
		userRepository.delete(userRepository.findByName("AAA"));

		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
		Assert.assertEquals(9, userRepository.findAll().size());

	}


}
