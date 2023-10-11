package it.euris.teslabatteryBm.config.security;

import it.euris.teslabatteryBm.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConf {

  private final WhiteListConfiguration whiteList;

  private final CorsFilter corsFilter;

  public SecurityConf(WhiteListConfiguration whiteList, CorsFilter corsFilter) {
    this.whiteList = whiteList;
    this.corsFilter = corsFilter;
  }

  @Bean
  public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable)
        .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(whiteList.getUrls()).permitAll()
            .requestMatchers("/catena-di-montaggio/**").hasRole(UserRole.ADMIN.toString())
            .requestMatchers("/componente/**").hasRole(UserRole.ADMIN.toString())
            .requestMatchers("/formula/**").hasRole(UserRole.ADMIN.toString())
            .anyRequest().authenticated()
        )
        .httpBasic(withDefaults())
        .formLogin(withDefaults());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService (){

    UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("user"))
        .roles(UserRole.USER.toString())
        .build();

    UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("admin"))
        .roles(UserRole.ADMIN.toString())
        .build();

    return new InMemoryUserDetailsManager(user,admin);
  }

}
