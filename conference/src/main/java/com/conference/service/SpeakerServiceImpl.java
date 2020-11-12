package com.conference.service;

import java.util.List;

import com.conference.model.Speaker;
import com.conference.repository.HibernateSpeakerRepositoryImpl;
import com.conference.repository.SpeakerRepository;

public class SpeakerServiceImpl implements SpeakerService {
	
	private SpeakerRepository speakerRepo = new HibernateSpeakerRepositoryImpl();
	
	@Override
	public List<Speaker> findAll() {
		return speakerRepo.findAll();
	} 	
}
