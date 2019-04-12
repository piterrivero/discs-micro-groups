package com.discs.groups.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discs.groups.ErrorCodes;
import com.discs.groups.dao.GroupRepository;
import com.discs.groups.dao.SequenceDao;
import com.discs.groups.model.Group;
import com.discs.groups.model.response.GroupGeneralResponse;

@CrossOrigin
@RestController
public class GroupsController extends ErrorCodes {

	private static final String GROUP_SEQ_KEY = "group";
	
	@Autowired
	Environment environment;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	SequenceDao sequenceDao;
	
	@GetMapping("/groups/test")
	public String test(){
	    String resp = "The Groups Service is works on the port: " + environment.getProperty("local.server.port");
	    return resp;
	}
	
	@RequestMapping(value = "/groups/list")
	public GroupGeneralResponse list(@RequestParam String order){
		List<Group> groupList = null;
		
		if (order != null && !order.equals("") && order.toUpperCase().equals("ASC")) {
			groupList = groupRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		}else if (order != null && !order.equals("") && order.toUpperCase().equals("DESC")) {
			groupList = groupRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
		}
		
		GroupGeneralResponse groupGeneralResponse = new GroupGeneralResponse();
		groupGeneralResponse.setErrorCode(0);
		groupGeneralResponse.setErrorMsg("OK");
		groupGeneralResponse.setGroupList(groupList);
		groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return groupGeneralResponse;
	}
	
	@RequestMapping(value = "/groups/find")
	public GroupGeneralResponse findById(@RequestParam String id){
		
		GroupGeneralResponse groupGeneralResponse = new GroupGeneralResponse();
		
		Group group = groupRepository.findById(Long.parseLong(id));
		
		if (group == null) {
			groupGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			groupGeneralResponse.setErrorMsg("Error: Group not found");
			groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		} else {
			groupGeneralResponse.setErrorCode(0);
			groupGeneralResponse.setErrorMsg("OK");
			groupGeneralResponse.setGroup(group);
			groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
	    return groupGeneralResponse;
	}
	
	@RequestMapping(value = "/groups/add", method = RequestMethod.POST)
	public GroupGeneralResponse add(@RequestBody(required=true) Group group){
		group.setIdGroup(sequenceDao.getNextSequenceId(GROUP_SEQ_KEY));
		groupRepository.save(group);
		
		GroupGeneralResponse groupGeneralResponse = new GroupGeneralResponse();
		groupGeneralResponse.setErrorCode(0);
		groupGeneralResponse.setErrorMsg("OK");
		groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return groupGeneralResponse;
	}
	
	@RequestMapping(value = "/groups/edit", method = RequestMethod.PATCH)
	public GroupGeneralResponse edit(@RequestBody(required=true) Group group){
		
		GroupGeneralResponse groupGeneralResponse = new GroupGeneralResponse();
		
		if (group.getIdGroup() == 0) {
			groupGeneralResponse.setErrorCode(REQUIRED_FIELD);
			groupGeneralResponse.setErrorMsg("Error: the field groupId is required");
			groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			GroupGeneralResponse ggrTemp = findById(String.valueOf(group.getIdGroup()));
			if (ggrTemp.getErrorCode() == RECORD_NOT_FOUND){
				groupGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
				groupGeneralResponse.setErrorMsg("Error: Group not found");
				groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			}else {
				groupGeneralResponse.setErrorCode(0);
				groupGeneralResponse.setErrorMsg("OK");
				groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
				groupRepository.save(group);
			}
		}
		
		return groupGeneralResponse;
	}
	
	@RequestMapping(value = "/groups/delete")
	public GroupGeneralResponse delete(@RequestParam String id){
		
		GroupGeneralResponse groupGeneralResponse = new GroupGeneralResponse();
		
		Group group = groupRepository.findById(Long.parseLong(id));
		
		if (group == null){
			groupGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			groupGeneralResponse.setErrorMsg("Error: Group not found");
			groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			groupRepository.delete(group);
			groupGeneralResponse.setErrorCode(0);
			groupGeneralResponse.setErrorMsg("OK");
			groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
		return groupGeneralResponse;
	}
}
