package dea.homepage.web.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/manager/main" )
public class ManagerMainController {

    @GetMapping( value = "" )
    public String index() {

        return "/manager/main";
    }
}
