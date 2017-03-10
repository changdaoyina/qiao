package com.qiao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.qiao.domain.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, User> registTemplate;

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("蝙蝠侠", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("蝙蝠侠"));
		

//		// 保存对象
		User user = new User(0l,"aa", 20);
		registTemplate.opsForValue().set(user.getName(), user);
//
//		user = new User("蝙蝠侠", 30);
//		redisTemplate.opsForValue().set(user.getName(), user);
//
//		user = new User("蜘蛛侠", 40);
//		redisTemplate.opsForValue().set(user.getName(), user);
//
//		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
	}
}
