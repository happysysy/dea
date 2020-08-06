package dea.homepage.web.controller.admin.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/admin/user/main/" )
public class AdminUserMainManagementController {

    @GetMapping( value = "thumbnailManagement" )
    public String index() {

        return "/admin/user/main/thumbnailManagement";
    }
}
