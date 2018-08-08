package com.jack.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 测试发送邮件
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMailSenderTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine thymeleaf;

    @Test
    public void testSendSampleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("747596887@qq.com");
        message.setTo("1494805480@qq.com");
        message.setSubject("邮件Subject");
        message.setText("邮件内容");
        javaMailSender.send(message);
    }

    @Test
    public void testSendMimeMessage() throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setFrom("747596887@qq.com");
        helper.setTo("1494805480@qq.com");
        helper.setSubject("主题邮件");
        helper.setText("有附件内容的邮件");

        FileSystemResource resource = new FileSystemResource(ResourceUtils.getFile("classpath:1.jpg"));
        helper.addAttachment("附件-1.jpg",resource);
        helper.addAttachment("附件-2.jpg",resource);
        javaMailSender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom("747596887@qq.com");
        helper.setTo("1494805480@qq.com");
        helper.setSubject("静态资源邮件");
        // true 表示启动HTML格式的邮件
        helper.setText("<html><body><img src=\"cid:img1\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile("classpath:1.jpg"));
        helper.addInline("img1",file);

        javaMailSender.send(message);

    }

    @Test
    public void testSendHtmlMail() throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setFrom("747596887@qq.com");
        helper.setTo("1494805480@qq.com");
        helper.setSubject("模板邮件");

        IContext context = new Context();
        ((Context) context).setVariable("name","Jackwu");
        String htmlText = thymeleaf.process("/email/email.html",context);
        helper.setText(htmlText,true);

        javaMailSender.send(mimeMessage);
    }

}
