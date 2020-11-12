import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.conference.repository.HibernateSpeakerRepositoryImpl;
import com.conference.repository.SpeakerRepository;
import com.conference.service.SpeakerService;
import com.conference.service.SpeakerServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name = "speakerService") //Proper Naming is essential if we want to enable Autowire by Name Later.
	public SpeakerService getSpeakerService() { //Function name can be anything.
		//SpeakerServiceImpl speakerService = new SpeakerServiceImpl();
		//speakerService.setSpeakerRepo(getSpeakerRepository()); 
		//Getting the singleton bean SpeakerRepository. 
		//Even if we call getSpeakerRepository() multiple times.
		SpeakerServiceImpl speakerService = new SpeakerServiceImpl(getSpeakerRepository());
		return speakerService;
	}
	
	@Bean(name = "speakerRepository")
	public SpeakerRepository getSpeakerRepository() {
		return new HibernateSpeakerRepositoryImpl();
	}
}
