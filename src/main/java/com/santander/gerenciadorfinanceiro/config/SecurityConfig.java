package com.santander.gerenciadorfinanceiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.santander.gerenciadorfinanceiro.service.imp.TokenService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AutenticacaoService userDetailService;
	@Autowired
	private TokenService tokenService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin").password("1234").roles("USER");
		auth.userDetailsService(userDetailService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().sameOrigin().and().authorizeRequests()
		//	.antMatchers("/h2-console/**").permitAll().antMatchers(HttpMethod.POST, "/auth/**").permitAll()
		//	.anyRequest().authenticated().and().sessionManagement()
			.anyRequest().permitAll().and().sessionManagement()	
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(
					new JwtTokenFilter(tokenService, userDetailService), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Qualifier(value = "autenticate")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}

	@Bean
	@Qualifier(value = "encoder")
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
