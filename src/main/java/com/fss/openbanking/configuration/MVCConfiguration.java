/**
 * 
 */
package com.fss.openbanking.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.fss.openbanking.interceptor.AuthorizationInterceptor;

/**
 * @author Selvakumar
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.fss")
public class MVCConfiguration  implements WebMvcConfigurer {

	@Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(
          new String[] { "/WEB-INF/tiles/common/commons.xml" });
        tilesConfigurer.setCheckRefresh(true);
         
        return tilesConfigurer;
    }
    
	 @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        TilesViewResolver viewResolver = new TilesViewResolver();
	        registry.viewResolver(viewResolver);
	    }
	     
		  @Override
		    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		        registry.addResourceHandler("/css/**")
		                .addResourceLocations("/WEB-INF/css/");

		        registry.addResourceHandler("/fonts/**")
		                .addResourceLocations("/WEB-INF/fonts/");

		        registry.addResourceHandler("/images/**")
		                .addResourceLocations("/WEB-INF/images/");
		        
		        registry.addResourceHandler("/js/**")
	            	.addResourceLocations("/WEB-INF/js/");
		    }
		  
		  @Override
		    public void addInterceptors(InterceptorRegistry registry) {
		        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**","/images/**","/fonts/**");
		    }
		  
		  @Bean("messageSource")
		  public MessageSource messagoeSource() {
		      ReloadableResourceBundleMessageSource messageSource = 
		                                                 new ReloadableResourceBundleMessageSource();
		      messageSource.setBasenames("classpath:/QueryConstants","classpath:MessageResources");
		      messageSource.setCacheSeconds(10); //reload messages every 10 seconds
		      return messageSource;
		  }
		  
		  

}

