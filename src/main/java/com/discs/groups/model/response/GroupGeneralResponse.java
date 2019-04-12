package com.discs.groups.model.response;

import java.util.List;

import com.discs.groups.model.Group;

public class GroupGeneralResponse {

	private Group group;
	private List<Group> groupList;
	private int errorCode;
	private String errorMsg;
	private String responsePort;
	
	public GroupGeneralResponse() {
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponsePort() {
		return responsePort;
	}
	public void setResponsePort(String responsePort) {
		this.responsePort = responsePort;
	}
	
}
