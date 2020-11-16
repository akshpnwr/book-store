package book.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username,password,active from user where username=?")
				.authoritiesByUsernameQuery("select username,roles from user where username=?")
				;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		        .authorizeRequests()
		        .antMatchers("/books").hasAnyRole("USER","ADMIN")
		        .antMatchers("/add").hasRole("ADMIN")
		        .antMatchers("/sales").hasRole("ADMIN")
		        .antMatchers("/users").hasRole("ADMIN")
		        .antMatchers("/upload").hasRole("ADMIN")
		        .antMatchers("/cart").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/books")
				.and()
				.logout()
				.logoutUrl("/**/logout")
				.logoutSuccessUrl("/login")
				.permitAll()
				;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
