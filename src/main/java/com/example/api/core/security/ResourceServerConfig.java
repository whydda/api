//package com.example.api.core.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//	@Value("${resource.id:spring-boot-application}")
//	private String resourceId;
//
//	@Value("${security.oauth2.resource.jwt.key-value}")
//	private String publicKey;
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		//converter.setSigningKey("secret");
//		converter.setVerifierKey(publicKey);
//		return converter;
//	}
//
//	@Bean
//	@Primary
//	public DefaultTokenServices tokenService() {
//		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//		defaultTokenServices.setTokenStore(tokenStore());
//		defaultTokenServices.setSupportRefreshToken(true);
//		return defaultTokenServices;
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .antMatchers("/api/**").hasRole("USER");
//	}
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources.resourceId(resourceId);
//	}