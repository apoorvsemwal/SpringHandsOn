import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.conference.repository.HibernateSpeakerRepositoryImpl;
import com.conference.repository.SpeakerRepository;
import com.conference.service.SpeakerService;
import com.conference.service.SpeakerServiceImpl;

@Configuration
public class AppConfig {

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
	
	@Bean(name = "speakerRepository")
	public SpeakerRepository getSpeakerRepository() {
		return new HibernateSpeakerRepositoryImpl();
	}
}
