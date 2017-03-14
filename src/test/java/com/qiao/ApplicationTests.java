package com.qiao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiao.properties.QiaoProperties;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {
	
	private static final Log log = LogFactory.getLog(ApplicationTests.class);
	
	@Autowired
	private QiaoProperties qiaoProperties;
	
	@Test
	public void test1() throws Exception {
		Assert.assertEquals("qiao", qiaoProperties.getName());
		Assert.assertEquals("spring boot", qiaoProperties.getTitle());
		log.info(qiaoProperties.getDesc());
		log.info("随机数测试输出：");
		log.info("随机字符串 : " + qiaoProperties.getValue());
		log.info("随机int : " + qiaoProperties.getNumber());
		log.info("随机long : " + qiaoProperties.getBignumber());
		log.info("随机10以下 : " + qiaoProperties.getTest1());
		log.info("随机10-20 : " + qiaoProperties.getTest2());

	}


}
