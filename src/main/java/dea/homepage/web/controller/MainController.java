package dea.homepage.web.controller;

import org.springframework.stereotype.Controller;
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
}
