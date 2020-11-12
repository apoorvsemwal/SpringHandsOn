import com.conference.service.SpeakerService;
import com.conference.service.SpeakerServiceImpl;

public class Application {

	public static void main(String[] args) {
		
		//Hard Coded Reference - Could have used Factory Design Pattern but we'll let Spring do that.
		SpeakerService service = new SpeakerServiceImpl();
		System.out.println(service.findAll().get(0).getFirstName());
	}

}
