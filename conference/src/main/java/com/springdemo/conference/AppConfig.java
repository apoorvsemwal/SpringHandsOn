//package com.springdemo.conference;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@ComponentScan({"com.springdemo.conference"})
//@PropertySource("application.properties")
//public class AppConfig {
//
//	/*
//	Entire Bean Definition is not required now since we ask spring to do a component scan and autowire
//	dependencies. We have now marked
//	
//	@Bean(name = "speakerService") //Proper Naming is essential if we want to enable Autowire by Name Later.
//	@Scope(value = BeanDefinition.SCOPE_SINGLETON)
//	public SpeakerService getSpeakerService() { //Function name can be anything.
//		//SpeakerServiceImpl speakerService = new SpeakerServiceImpl();
//
//		//Getting the singleton bean SpeakerRepository.
//		//Even if we call getSpeakerRepository() multiple times.
//		//speakerService.setSpeakerRepo(getSpeakerRepository()); 
//		
//		//Constructor Injection
//		//getSpeakerRepository() is not required since its autowired and automatically injected.
//		SpeakerServiceImpl speakerService = new SpeakerServiceImpl();
//		return speakerService;
//	}
//	*/
//	
//	/*
//	Entire Bean Definition is not required now since we ask spring to do a component scan and autowire
//	dependencies 
//	@Bean(name = "speakerRepository")
//	public SpeakerRepository getSpeakerRepository() {
//		return new HibernateSpeakerRepositoryImpl();
//	}
//	*/
//}
