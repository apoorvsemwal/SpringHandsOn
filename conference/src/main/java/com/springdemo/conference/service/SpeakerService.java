package com.springdemo.conference.service;

import java.util.List;

import com.springdemo.conference.model.Speaker;

public interface SpeakerService {

	List<Speaker> findAll();

}