package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.security;


import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.properties.RestProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatelessApiKeyAuthenticationFilter extends GenericFilterBean {

	private RestProperties restProperties;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (restProperties == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			restProperties = webApplicationContext.getBean(RestProperties.class);
		}

		HttpServletRequest servReq = (HttpServletRequest) request;

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		SimpleGrantedAuthority simpleGrantedAuthorityResponsable = new SimpleGrantedAuthority("ROLE_APLICACIO");
		grantedAuthorities.add(simpleGrantedAuthorityResponsable);

		String apiKey = servReq.getHeader(restProperties.getApiTag());

		if(StringUtils.isBlank(apiKey)){
			((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken("REST_CALL", apiKey, grantedAuthorities);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);
	}
}
