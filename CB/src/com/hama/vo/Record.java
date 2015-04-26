package com.hama.vo;

import java.io.Serializable;

public class Record implements Serializable{
	private String birthYear;
	private int ensureYear;
	private String label;
	private String agent;
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public int getEnsureYear() {
		return ensureYear;
	}
	public void setEnsureYear(int ensureYear) {
		this.ensureYear = ensureYear;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
}
