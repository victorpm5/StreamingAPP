package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.config;

import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.security.StatelessApiKeyAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationSecurity {

	/*
	 * Validació amb ws rest
	 */
	@Configuration
    @Order(1)
    public static class HeaderUserNameConfig extends WebSecurityConfigurerAdapter {

		private static final String APLICACIO = "APLICACIO";
		private static final String WS_REST = "/api/**";

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http
				.antMatcher(WS_REST)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				.antMatcher(WS_REST)
					.exceptionHandling()

				.and()
					.antMatcher(WS_REST)
					.anonymous()

				.and()
					.antMatcher(WS_REST)
					.servletApi()

				.and()
					.antMatcher(WS_REST)
					.headers()
					.cacheControl()

				.and()
				.and()
					.antMatcher(WS_REST)
						.csrf().disable()
						.authorizeRequests()
						.antMatchers(WS_REST).hasRole(APLICACIO);

			http
				.antMatcher(WS_REST)
				.addFilterBefore(statelessAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		}

		private StatelessApiKeyAuthenticationFilter statelessAuthenticationFilter() {
			return new StatelessApiKeyAuthenticationFilter();
		}

	}

}
