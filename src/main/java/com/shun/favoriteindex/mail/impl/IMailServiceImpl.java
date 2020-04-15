package com.shun.favoriteindex.mail.impl;

import com.shun.favoriteindex.mail.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.MessageFormat;
import java.util.Set;

@Service
public class IMailServiceImpl implements IMailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SEPARATOR = "/";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String send;

    @Override
    public void sendMail(String subject, String content, String... recvs) {
        if (recvs == null || recvs.length <= 0) {
            String errorMsg = "调用邮件发送接口[IMailService.sendMail]时参数错误，收件人为空。";
            logger.info(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(send);
            //邮件接收人
            messageHelper.setTo(recvs);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，支持html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            logger.info(MessageFormat.format
                    ("\n调用邮件发送接口[IMailService.sendMail]成功，邮件已经发送。收件人{0}，邮件主题{1}，邮件内容{2}",
                            recvs, subject, content));
        } catch (MessagingException e) {
            logger.error("调用邮件发送接口[IMailService.sendMail]时发生异常！", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendAttachmentsMail(String subject, String content, Set<String> filePaths, String... recvs) {
        if (recvs == null || recvs.length <= 0) {
            String errorMsg = "调用邮件发送接口[IMailService.sendAttachmentsMail]时参数错误，收件人为空。";
            logger.info(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //邮件信息设置
            helper.setFrom(send);
            helper.setTo(recvs);
            helper.setSubject(subject);
            helper.setText(content, true);

            if (filePaths != null && filePaths.size() > 0) {
                //添加附件信息
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(SEPARATOR));
                    helper.addAttachment(fileName, file);
                }
            }
            //发送
            mailSender.send(message);
            //日志信息
            logger.info(MessageFormat.format
                    ("\n调用邮件发送接口[IMailService.sendAttachmentsMail]成功，邮件已经发送。收件人{0}，邮件主题{1}，附件路径{2}，邮件内容{3}",
                            recvs, subject, filePaths.toString(), content));
        } catch (MessagingException e) {
            logger.error("调用邮件发送接口[IMailService.sendAttachmentsMail]时发生异常！", e);
        }
    }
}
