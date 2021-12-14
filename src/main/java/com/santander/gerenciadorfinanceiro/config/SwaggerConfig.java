package com.santander.gerenciadorfinanceiro.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.santander.gerenciadorfinanceiro"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Lists.newArrayList(apiKey()))
				.securityContexts(Lists.newArrayList(securityContext()))
				.apiInfo(metaInfo());
	}

	@Bean
	SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
	    return new ApiKey("JWT", "Authorization", "header");
	}

	private ApiInfo metaInfo() {

		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo("Agenda API REST", "API REST de gerenciador financeiro.", "1.0", "Terms of Service",
				new Contact("Sonoda", null, "tsonoda2003@hotmail.com"), "Apache License Version 2.0",
				"https://www.apache.org/licesen.html", new ArrayList<VendorExtension>());

		return apiInfo;
	}
}
