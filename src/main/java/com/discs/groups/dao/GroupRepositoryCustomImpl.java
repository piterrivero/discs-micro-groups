package com.discs.groups.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

	@Autowired
    MongoTemplate mongoTemplate;

}
