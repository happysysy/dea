
package dea.homepage.service.mail;

import dea.homepage.vo.contact.ContactInfo;
import org.apache.commons.mail.HtmlEmail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.StringUtils;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {

    private static String mailAddr = "deasart27@gmail.com";
    private static String mailPwd = "dbstmdwn12#$";
    private static String mailSendName = "DEA";

    /**
     * @return
     */
    public static final boolean send(ContactInfo info) {

        /*
        String user = "deasart27@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "dbstmdwn12#$";   // 패스워드
        */

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailAddr, mailPwd);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailAddr));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("chlong941@gmail.com"));

            // Subject
//            message.setSubject( info.getTitle()); //메일 제목을 입력
            message.setSubject( "[DEA 홈페이지 문의] " + info.getTitle() );


            //  메일내용( 문의자 정보, 문의사항 )
            String contents = "* 문의자정보\r\n\r\n";
            contents += "  성함         : " + info.getName() + "\r\n";
            if( StringUtils.hasText( info.getPhone() ) ) {
                contents += "  연락처     : " + info.getPhone() + "\r\n";
            }
            if( StringUtils.hasText( info.getEmail() ) ) {
                contents += "  회신메일     : " + info.getEmail() + "\r\n";
            }
            contents += "\r\n\r\n\r\n* 문의사항\r\n\r\n  " + info.getContents();

            message.setText( contents );    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송

            return true;

            //  예외발생 시 return false
        } catch (AddressException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static final boolean sendMailTemplate( ContactInfo info ) throws Exception {

        Velocity.setProperty( "resource.loader", "class" );
        Velocity.init();

        HtmlEmail email = new HtmlEmail();

        String title = "[dae 홈페이지]" + info.getTitle();
        email.setSubject( info.getTitle() );

        String templatePath = "mailTemplates/contact_email.vm";

        String templateName = MessageFormat.format( templatePath, Locale.getDefault() );

        VelocityContext context = new VelocityContext();
        context.put( "innerText", "innter!!!!" );

        StringWriter writer = new StringWriter();
        Template tplt = Velocity.getTemplate( templateName, "UTF-8");
        tplt.merge( context, writer );
        email.setHtmlMsg( writer.toString() );

        sendMail( email );

        return true ;
    }

    public static final boolean sendMail( HtmlEmail email ) {

        /**
         *   prop.put("mail.smtp.host", "smtp.gmail.com");
         *         prop.put("mail.smtp.port", 465);
         *         prop.put("mail.smtp.auth", "true");
         *         prop.put("mail.smtp.ssl.enable", "true");
         *         prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
         */
        email.setHostName( "smtp.gmail.com" );

        email.setSmtpPort( 465 );
        email.setAuthentication( "deasart27@gmail.com", "dbstmdwn12#$");
        email.setSSLOnConnect( true );
        /*
        if ( SystemConfig.IS_LOCAL || IS_DEBUG )	{
            email.setDebug(true);
        }
        */
        email.setCharset( "UTF-8" );
        email.setStartTLSEnabled(true);

        try {

            //	메일계정설정 되어있지 않은경우만 설정
            if ( StringUtils.isEmpty(email.getFromAddress() ) ) {
                email.setFrom( mailAddr, mailSendName );
            }
            email.send();
            return true;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }


}