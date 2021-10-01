package app.commons.config;

import app.commons.filter.JsonApiAuthenticationFilter;
import app.component.service.UserAuthDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/api/**");
        //デフォルトでアクセス許可されている
        http.authorizeRequests()
            .antMatchers("/api/login").permitAll()
            .antMatchers( "/login").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/*.css").permitAll()
            .anyRequest().authenticated();
        http.addFilterAt(jsonApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //ログイン成功時の動作
        http.formLogin()
            .loginPage("/login").defaultSuccessUrl("/")
            .permitAll();
        //ログアウト時の動作
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .deleteCookies("SESSION")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/login")
            .permitAll();
        //CORS設定
        http.cors().configurationSource(corsConfigurationSource());
    }

    /**
     * SPA用の認証Filter
     * @return
     */
    @Bean
    public JsonApiAuthenticationFilter jsonApiAuthenticationFilter() throws Exception{
        JsonApiAuthenticationFilter jsonApiAuthenticationFilter = new JsonApiAuthenticationFilter(authenticationManager());
        jsonApiAuthenticationFilter.setAuthenticationSuccessHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_OK));
        jsonApiAuthenticationFilter.setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
        jsonApiAuthenticationFilter.setAllowSessionCreation(true);
        return jsonApiAuthenticationFilter;
    }

    /**
     * SPA用のCORS関連設定
     * @return
     */
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("https://localhost:5000", "http://localhost:5000"));
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/api/**", corsConfiguration);
        return corsConfigurationSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userAuthDetailService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserAuthDetailService userAuthDetailService(){
        return new UserAuthDetailService();
    }

}
