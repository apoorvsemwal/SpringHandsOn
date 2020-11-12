package com.conference.repository;

import java.util.List;

import com.conference.model.Speaker;

public interface SpeakerRepository {

	List<Speaker> findAll();

}