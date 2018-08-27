package com.dbjinjin.flyshare.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>标题： SwaggerConfiguration</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月25日 下午3:32:56</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.config.SwaggerConfiguration</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
	@Bean
	public Docket accessToken()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")// 定义组
				.select() // 选择那些路径和 api 会生成 document
				.apis(RequestHandlerSelectors.basePackage("com.dbjinjin.flyshare.ui.file.web")) // 拦截的包路径
				.paths(PathSelectors.regex("/*/.*"))// 拦截的接口路径
				.build() // 创建
				.apiInfo(apiInfo()); // 配置说明
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()//
				.title("Flyshare 之API 接口")// 标题
				.description("飞享项目API接口，提供第三方系统调用")// 描述
				.termsOfServiceUrl("http://www.dbjinjin.com")//
				.contact(new Contact("wuxiaohai", "http://www.dbjinjin.com", "154954803@qq.com"))// 联系
				.version("1.0")// 版本
				.build();
	}
}
