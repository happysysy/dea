package dea.test.mail;


import dea.homepage.service.mail.MailUtil;
import dea.homepage.vo.contact.ContactInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

public class MailTest {
    @Test
    public void 메일전송_테스트() {

        System.out.println( " 테스트실행.. ");

        ContactInfo info = ContactInfo.builder()
                .name( "황선영" )
                .email( "happy-sysy@hanmail.net" )
                .title( "취업가능한가요" )
                .contents( "안녕하세요. 4년차 디자이너입니다. 회사에서 하는일엔 늘 한계가 있어 프리랜서로 일해볼까 합니다." )
                .build();
        MailUtil.send( info );

    }

    @Test
    public void 템플릿메일전송_테스트() throws Exception {


        ContactInfo info = ContactInfo.builder()
                .name( "황선영" )
                .email( "happy-sysy@hanmail.net" )
                .title( "취업가능한가요" )
                .contents( "안녕하세요. 4년차 디자이너입니다." )
                .build();

        MailUtil.sendMailTemplate( info );

    }
}
