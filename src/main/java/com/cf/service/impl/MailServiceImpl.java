package com.cf.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.cf.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  //可以放在类上面或者方法上面,如果类上面和方法上面都有则方法上面重写类上面的.
public class MailServiceImpl implements MailService{
      
    @Autowired  
    private JavaMailSender sender;  
    // 邮件发送者
    @Value("${spring.mail.username}")  
    private String from;  
      
    /** 
     * 发送纯文本的简单邮件 
     * @param to 发给谁
     * @param subject 主题
     * @param content 内容
     */  
    public void sendSimpleMail(String to, String subject, String content){  
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setFrom(from);  
        message.setTo(to);  
        message.setSubject(subject);  
        message.setText(content);
        try {  
            sender.send(message);  
            System.out.println("简单邮件已经发送。");  
        } catch (Exception e) {  
        	System.out.println("发送简单邮件时发生异常！" + e.getMessage());  
        }  
    }  
      
    /** 
     * 发送html格式的邮件 
     * @param to 
     * @param subject 
     * @param content 
     */  
    public void sendHtmlMail(String to, String subject, String content){  
        MimeMessage message = sender.createMimeMessage();  
  
        try {  
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  
            helper.setFrom(from);  
            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setText(content, true);
            sender.send(message);  
            System.out.println("html邮件已经发送。");  
        } catch (MessagingException e) {  
        	System.out.println("发送html邮件时发生异常！" + e.getMessage());  
        }  
    }  
      
    /** 
     * 发送带附件的邮件 
     * @param to 
     * @param subject 
     * @param content 
     * @param filePath 
     */  
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){  
        MimeMessage message = sender.createMimeMessage();  
  
        try {  
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  
            helper.setFrom(from);  
            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setText(content, true);  
  
            FileSystemResource file = new FileSystemResource(new File(filePath));  
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));  
            helper.addAttachment(fileName, file);
            sender.send(message);  
            System.out.println("带附件的邮件已经发送。");  
        } catch (MessagingException e) {  
        	System.out.println("发送带附件的邮件时发生异常！" + e.getMessage());  
        }  
    }  
      
    /** 
     * 发送嵌入静态资源（一般是图片）的邮件 
     * @param to 
     * @param subject 
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" > 
     * @param rscPath 静态资源路径和文件名 
     * @param rscId 静态资源id 
     */  
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){  
        MimeMessage message = sender.createMimeMessage();
        try {  
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  
            helper.setFrom(from);  
            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setText(content, true);  
  
            FileSystemResource res = new FileSystemResource(new File(rscPath));  
            helper.addInline(rscId, res);  
              
            sender.send(message);  
            System.out.println("嵌入静态资源的邮件已经发送。");  
        } catch (MessagingException e) {  
        	System.out.println("发送嵌入静态资源的邮件时发生异常！" + e.getMessage());  
        }  
    }  
}  
