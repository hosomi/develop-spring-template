package jp.template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;

import jp.template.component.CustomHandlerInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		configurer.enable();
	}

	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
				.excludePathPatterns("/static/**","/webjars/**"); // 除外するパス(パターン)を指定する
	}

	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("login");
		registry.addViewController("/login").setViewName("login");
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.resourceChain(false) // 自動でWebJarsResourceResolverが有効化される
				.addResolver(new GzipResourceResolver());
	}

}
