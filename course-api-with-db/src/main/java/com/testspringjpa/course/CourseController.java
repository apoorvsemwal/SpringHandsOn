package com.testspringjpa.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testspringjpa.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/topics/{topic_id}/courses")
	public List<Course> getAllCourses(@PathVariable("topic_id") String id) {
		return courseService.getAllCourses(id);
	}

	@RequestMapping("/topics/{topic_id}/courses/{course_id}")
	public Course getCourse(@PathVariable("course_id") String id) {
		return courseService.getCourse(id);
	}

	@RequestMapping(method=RequestMethod.POST, value="/topics/{topic_id}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable("topic_id") String topic_id) {
		course.setTopic(new Topic(topic_id, "", ""));
		courseService.addCourse(course);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topic_id}/courses/{id}")
	public void updateCourse(@RequestBody Course course, @PathVariable("topic_id") String topic_id, @PathVariable String id) {
		course.setTopic(new Topic(topic_id, "", ""));
		courseService.updateCourse(course);
	}	

	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topic_id}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}	
}
