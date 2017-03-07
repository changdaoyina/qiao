package com.qiao.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@Value("${application.message:1234556677}")  
    private String message = "hi,hello world......";  
  
    @RequestMapping("/")  
    public String web(Map<String,Object> model){  
        model.put("time",new Date());  
        model.put("message",this.message);  
        return "web";//返回的内容就是templetes下面文件的名称  
    }  
}
