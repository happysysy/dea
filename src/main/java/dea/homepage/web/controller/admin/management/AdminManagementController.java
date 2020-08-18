package dea.homepage.web.controller.admin.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/admin/management/" )
public class AdminManagementController {

    @GetMapping( value = "thumbnail" )
    public String thumbnail() {

        return "/admin/management/thumbnail";
    }

    @GetMapping( value = "representative_work" )
    public String representativeWork() {

        return "/admin/management/representative_work";
    }

    @GetMapping( value = "intro" )
    public String intro() {

        return "/admin/management/intro";
    }


}
