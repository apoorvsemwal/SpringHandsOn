package test.simplespringboot.topic;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	private List<Topic> topics = Arrays.asList(
			new Topic("Spring", "Spring Framework", "Dependency Injection"),
			new Topic("Java", "Language", "Frameworks")
			);

	public List<Topic> getAllTopics(){
		return topics;
	}
}
