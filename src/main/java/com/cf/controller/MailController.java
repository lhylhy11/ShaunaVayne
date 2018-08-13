package com.cf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.service.MailService;

@Controller
public class MailController {
    @Autowired  
    private MailService mailService; 
    
    private String to = "100@qq.com";  

	// http://127.0.0.1:8080/mail
	@RequestMapping("/mail")
	public String file() {
		return "/mail";
	}

	/**
	 * 发送邮件 邮件标题
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/send")
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		System.out.println(title);
		mailService.sendSimpleMail(to, "主题：" + title, "测试邮件内容");  
		return "发送成功";
	}
	
//    @Autowired  
//    VelocityEngine velocityEngine;  
//      
//    public void sendHtmlMail() {  
//        Map<String, Object> model = new HashMap<String, Object>();  
//        model.put("time", XDateUtils.nowToString());  
//        model.put("message", "这是测试的内容。。。");  
//        model.put("toUserName", "张三");  
//        model.put("fromUserName", "老许");  
//        String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", "UTF-8", model);  
//          
//        mailService.sendHtmlMail(to, "主题：html邮件", content);  
//    }  
//  
//    public void sendAttachmentsMail() {  
//        mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\xcx.png");  
//    }  
//      
//    public void sendInlineResourceMail() {  
//        String rscId = "rscId001";  
//        mailService.sendInlineResourceMail(to,  
//                "主题：嵌入静态资源的邮件",  
//                "<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",  
//                "C:\\xcx.png",  
//                rscId);  
//    }  
}
