package com.discs.groups.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group")
public class Group {

	@Id
	private long idGroup;
	
	@Indexed(unique = true)
	private String name;
	
	private List<String> genres;
	
	public Group() {
	}
	
	public long getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(long idGroup) {
		this.idGroup = idGroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
}
