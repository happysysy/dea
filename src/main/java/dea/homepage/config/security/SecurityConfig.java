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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity  // Spring security 설정 활성화
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
                .csrf().disable()
                /**
                 * authorizeRequests - URL별 권한관리를 설정하는 옵션의 시작점
                 * antMatchers      - 권한관리대상을 지정하는 옵션
                 * anyRequest       - 나머지 URL
                 * authenticated    - 인증된 사용자 (로그인한 사용자만 사용할수 있도록)
                 */
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user").permitAll()   //사용자페이지는 누구나 접속
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/admin")    //로그인 안되어있을 시 url
                .loginProcessingUrl( "/admin/perform_login" )     //로그인로직 실행 url
                .defaultSuccessUrl("/admin/main", true )
                .usernameParameter( "id" )
                .passwordParameter( "password" )
                .failureUrl( "/admin" )
                .permitAll()     //양식 로그인 동작 구성


        ;

        //  스프링시큐리티가 항상 쎄션을 생성
        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.ALWAYS);

        http.logout()
                //.logoutUrl( "/admin/perform_logout" )
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/perform_logout"))
                .logoutSuccessUrl("/admin")
                .invalidateHttpSession( true )
                .deleteCookies( "JSESSIONID");

        //web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    public void configure(WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/static/**" );
    }

    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( memberService ).passwordEncoder( passwordEncoder() );
    }
    */
}
