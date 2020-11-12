import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conference.service.SpeakerService;

public class Application {

	private static ApplicationContext appContext;

	public static void main(String[] args) {
		
		appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//Hard Coded Reference - Could have used Factory Design Pattern but we'll let Spring do that.
		//SpeakerService service = new SpeakerServiceImpl();
		
		SpeakerService service = appContext.getBean("speakerService", SpeakerService.class);

		System.out.println(service.findAll().get(0).getFirstName());
		
	}

}
