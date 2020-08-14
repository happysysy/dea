package dea.homepage.web.controller.admin;

import dea.homepage.domain.user.User;
import dea.homepage.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/admin" )
public class AdminLoginController {

    private final PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @GetMapping( "" )
    public String index(HttpServletRequest request, Authentication authentication ) {

        final HttpSession httpSession = request.getSession();

        try {
            Optional.ofNullable( authentication.getPrincipal() ).orElseThrow(()->new Exception( "로그인되지 않았습니다." ));
            return "redirect:/admin/main";
        }catch ( Exception e ) {

            return "/admin/login";
        }
    }

    @GetMapping( "/login" )
    public String login() {

        return "/admin/login";
    }

    @PostMapping("/join")
    public String adminJoin(User user) {
        /* PasswordEncoder로 비밀번호 암호화 */
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save( user );
        return "redirect:/admin";
    }
}
