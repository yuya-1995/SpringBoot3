package com.example.sample1.app;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

@Component
public class SampleComponent {
	
	@Value("${samplespp.samplecomponent.message}")
	private String message;
	
	public SampleComponent() {
		super();
	}
	
	public String message() {
		return message;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}

}
