package dea.homepage.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .authorizeRequests()    //사용자가 인증할 수 있
                // 도록 /login에 익명 엑세스를 허용
                .antMatchers("/").permitAll()
                .antMatchers("/user").permitAll()   //사용자페이지는 누구나 접속
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/admin")    //로그인 안되어있을 시 url
                .loginProcessingUrl( "/admin/perform_login" )     //로그인로직 실행 url
                .failureUrl( "/admin" )
                .defaultSuccessUrl("/admin/main")
                .permitAll()     //양식 로그인 동작 구성

                ;

        //  스프링시큐리티가 항상 쎄션을 생성
        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.ALWAYS);

        http.logout()
                .logoutUrl( "/admin/perform_logout" )
                .logoutSuccessUrl("/admin")
                .invalidateHttpSession( true )
                .deleteCookies( "JSESSIONID");

        //web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    public void configure(WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/**" );
    }
    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( memberService ).passwordEncoder( passwordEncoder() );
    }
    */
}
