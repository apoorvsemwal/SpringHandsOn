package com.conference.service;

import java.util.List;

import com.conference.model.Speaker;
import com.conference.repository.SpeakerRepository;

public class SpeakerServiceImpl implements SpeakerService {
	
	//Hard Coded Reference - Could have used Factory Design Pattern but we'll let Spring do that.
	//private SpeakerRepository speakerRepo = new HibernateSpeakerRepositoryImpl();
	
	private SpeakerRepository speakerRepo;
	
	//For constructor based injection
	public SpeakerServiceImpl(SpeakerRepository speakerRepo) {
		super();
		this.speakerRepo = speakerRepo;
	}
	
	//For setter based injection
	public void setSpeakerRepo(SpeakerRepository speakerRepo) {
		this.speakerRepo = speakerRepo;
	}

	@Override
	public List<Speaker> findAll() {
		return speakerRepo.findAll();
	} 	
}
