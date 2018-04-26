package com.wut.user;

public class StateInformation {
	private String State;
	private String information;

	public StateInformation(String State, String information) {
		this.State = State;
		this.information = information;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
