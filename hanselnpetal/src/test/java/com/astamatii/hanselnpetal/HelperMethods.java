package com.astamatii.hanselnpetal;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HelperMethods {
	
	public static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	}
}
