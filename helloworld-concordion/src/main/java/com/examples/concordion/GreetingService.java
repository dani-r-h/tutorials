package com.examples.concordion;

public class GreetingService {

	public String greetings(final String username){
		final StringBuilder sb = new StringBuilder();
		return sb.append("Hola ").append(username).append("!").toString();
	}
	
}
