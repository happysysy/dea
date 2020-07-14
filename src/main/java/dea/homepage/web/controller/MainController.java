package dea.homepage.web.controller;

import dea.homepage.service.mail.MailUtil;
import dea.homepage.vo.contact.ContactInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping( value = "/" )
    public String index() {

        return "/index";
    }

    @RequestMapping( value = "/main" )
    public String main() {

        return "/index";
    }

    @PostMapping( value = "/main/sendMail" )
    public boolean sendMail( @ModelAttribute ContactInfo info ) {

        return MailUtil.send( info );
    }
}
