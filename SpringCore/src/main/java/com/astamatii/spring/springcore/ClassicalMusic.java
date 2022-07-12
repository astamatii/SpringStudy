package com.astamatii.spring.springcore;


public class ClassicalMusic implements Music{
		
	public void doMyInit() {
		System.out.println("Doing init-method for a ClassicalMusic Bean: " + this.hashCode() + "\n");
	}	
	
	public void doMyDestroy() {
		System.out.println("\nDoing destroy-method for a ClassicalMusic Bean " + this.hashCode());
	}
	
	@Override
	public String getSong() {
		return "Hungarian Rhapsody";
	}
}
