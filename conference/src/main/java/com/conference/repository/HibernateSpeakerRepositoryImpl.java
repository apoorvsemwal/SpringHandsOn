package com.conference.repository;

import java.util.ArrayList;
import java.util.List;

import com.conference.model.Speaker;

public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {
	
	@Override
	public List<Speaker> findAll() {
		
		//DB implementation might change.
		List<Speaker> speakers = new ArrayList<Speaker>();
		
		Speaker speaker = new Speaker();
		speaker.setFirstName("Apoorv");
		speaker.setLastName("Semwal");
		
		speakers.add(speaker);
		return speakers;
	} 
}
