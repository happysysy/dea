package dea.homepage.web.controller.user;

import dea.homepage.service.mail.MailUtil;
import dea.homepage.vo.contact.ContactInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping( value = "/" )
    public String index() {

        return "redirect:/user";
    }

    @RequestMapping( value = "/user" )
    public String index2() {

        return "/user/index";
    }

    @GetMapping( value = "/main" )
    public String main() {

        return "/user/index";
    }

    @PostMapping( value = "/main/sendMail" )
    public @ResponseBody boolean sendMail( @ModelAttribute ContactInfo info ) {

        return MailUtil.send( info );
    }
}
