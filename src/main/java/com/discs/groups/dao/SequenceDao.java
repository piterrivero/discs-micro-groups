package com.discs.groups.dao;

import org.springframework.stereotype.Repository;

import com.discs.groups.exceptions.SequenceException;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
	
}
