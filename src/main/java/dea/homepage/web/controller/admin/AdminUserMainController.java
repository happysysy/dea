package dea.homepage.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/admin/user/main/" )
public class AdminUserMainController {

    @GetMapping( value = "thumbnailMgnt" )
    public String index() {

        return "/admin/user/main/thumbnailMgnt";
    }
}
