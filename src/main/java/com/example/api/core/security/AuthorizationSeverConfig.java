//package com.example.api.core.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//
//import java.security.KeyPair;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationSeverConfig extends AuthorizationServerConfigurerAdapter{
//
//	@Value("${resouce.id:spring-boot-application}")
//	private String resourceId;
//
//	@Value("${access_token.validity_period:3600}")
//	int accessTokenValiditySeconds = 3600;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
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
//		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("server.jks"), "passtwo".toCharArray())
//				.getKeyPair("auth", "passone".toCharArray());
//		converter.setKeyPair(keyPair);
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
//    }
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter());
//	}
//
//	@Bean
//	@Primary
//	public JdbcClientDetailsService JdbcClientDetailsService(DataSource dataSource) {
//		return new JdbcClientDetailsService(dataSource);
//	}
//
//	@Autowired
//	private ClientDetailsService clientDetailsService;
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		//oauth_client_details 테이블에 등록된 사용자로 조회한다.
//		clients.withClientDetails(clientDetailsService);
//	}