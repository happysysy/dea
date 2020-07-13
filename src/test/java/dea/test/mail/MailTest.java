package dea.test.mail;


import dea.homepage.service.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

public class MailTest {
    @Test
    public void 메일전송_테스트() {

        System.out.println( " 테스트실행.. ");

        MailUtil.send();

    }
}
