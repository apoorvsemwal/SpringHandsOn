package com.springdemo.conference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
@Configuration
//@ComponentScan({"com.springdemo.conference"})
//@PropertySource("application.properties")
public class AppConfig implements WebMvcConfigurer{
	
	
	
	@Override
	//This is in case we want to host some static files say some PDFs.
	//in our base url if we all /files/Toad.pdf it would redirect us to this pdf.
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**")
		.addResourceLocations("/WEB-INF/pdf/");
	}

	@Bean
	//This view resolver is automatically created for us when we use @SpringBootApplication in our
	//main class. These are the same properties we have defined in application.properties file.
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		bean.setOrder(0); //If we have multiple view resolvers then what position should this resolver
		//execute in.
		return bean;
	}
	
	/*
	Entire Bean Definition is not required now since we ask spring to do a component scan and autowire
	dependencies. We have now marked
	
	@Bean(name = "speakerService") //Proper Naming is essential if we want to enable Autowire by Name Later.
	@Scope(value = BeanDefinition.SCOPE_SINGLETON)
	public SpeakerService getSpeakerService() { //Function name can be anything.
		//SpeakerServiceImpl speakerService = new SpeakerServiceImpl();

		//Getting the singleton bean SpeakerRepository.
		//Even if we call getSpeakerRepository() multiple times.
		//speakerService.setSpeakerRepo(getSpeakerRepository()); 
		
		//Constructor Injection
		//getSpeakerRepository() is not required since its autowired and automatically injected.
		SpeakerServiceImpl speakerService = new SpeakerServiceImpl();
		return speakerService;
	}
	Entire Bean Definition is not required now since we ask spring to do a component scan and autowire
	dependencies 
	@Bean(name = "speakerRepository")
	public SpeakerRepository getSpeakerRepository() {
		return new HibernateSpeakerRepositoryImpl();
	}
	*/
}
