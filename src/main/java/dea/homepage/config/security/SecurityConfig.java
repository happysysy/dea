package dea.homepage.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    private MemberService memberService;
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser( "admin" ).password( passwordEncoder().encode( "1111" )).roles( "ADMIN" );
    }
    @Override
    public void configure( HttpSecurity http ) throws Exception {

        http
                .authorizeRequests()    //사용자가 인증할 수 있도록 /login에 익명 엑세스를 허용
                .antMatchers("/user").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/admin/login.html")
                .loginProcessingUrl( "/perform_login" )
                .defaultSuccessUrl("/admin/main")
                .permitAll()     //양식 로그인 동작 구성

                .and()
                .logout()
                .logoutUrl( "/perform_logout" )
                .deleteCookies( "JSESSIONID") ;


        //web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( memberService ).passwordEncoder( passwordEncoder() );
    }
    */
}
