package org.iiitb.sis.dto;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	private static final long serialVersionUID = -6382397973111293282L;
	private int sid;
	private String name;
	private String rollno;
	private String email;
	private String phno;
	private Date dob;
	private String lastdegree;
	private int currentsem;
	private byte[] image;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public int getCurrentsem() {
		return currentsem;
	}
	public void setCurrentsem(int currentsem) {
		this.currentsem = currentsem;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
