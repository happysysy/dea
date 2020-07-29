package dea.homepage.web.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/manager" )
public class ManagerLoginController {

    @GetMapping( "" )
    public String index() {

        return "/manager/login";
    }

    @GetMapping( "login" )
    public String login() {

        return "/manager/login";
    }
}