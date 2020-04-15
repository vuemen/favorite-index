package com.shun.favoriteindex.mail;

import java.util.Set;

/**
 * 邮件服务
 */
public interface IMailService {

    /**
     * 发送邮件 支持html格式
     * @param subject   邮件主题
     * @param content   邮件内容
     * @param recvs     收件人
     */
    void sendMail(String subject, String content, String... recvs);

    /**
     * 发送带附件的邮件
     * @param subject   邮件主题
     * @param content   邮件内容
     * @param filePaths 附件文件路径
     * @param recvs     收件人
     */
    void sendAttachmentsMail(String subject, String content, Set<String> filePaths, String... recvs);
}
