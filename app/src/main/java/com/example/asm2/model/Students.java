package com.example.asm2.model;

import java.util.Date;

public class Students {
	private String id;
	private String name;
	private Date dob;
	private int classId;

	public Students() {
	}

	public Students(String id, String name, Date dob, int classId) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.classId = classId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}
}
