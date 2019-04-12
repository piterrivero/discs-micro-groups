package com.discs.groups.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discs.groups.model.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, Long>, GroupRepositoryCustom {
	
	List<Group> findAll(Sort sortByNameAtAsc);
	
	Group findById(long id);

		
}
