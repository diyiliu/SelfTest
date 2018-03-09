import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Description: TestMail
 * Author: DIYILIU
 * Update: 2017-11-13 17:18
 */
public class TestMail {


    @Test
    public void test() throws Exception {
        // 创建一个程序与邮件服务器会话对象
        Properties props = new Properties();
        props.put("mail.transport.protocol", "SMTP");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
        props.put("mail.user", "572772828@qq.com");
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.user"), "fadfsnmciruobcbe");
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress("diyiliu@vip.qq.com");
        message.setRecipient(Message.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject("测试邮件");
        // 设置邮件的内容体
        message.setContent("这是一封测试邮件", "text/html;charset=UTF-8");
        // 最后当然就是发送邮件啦
        Transport.send(message);
    }
}
