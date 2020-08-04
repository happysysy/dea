package dea.homepage.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/admin/main" )
public class AdminMainController {

    @GetMapping( value = "" )
    public String index() {

        return "/admin/index";
    }
}
