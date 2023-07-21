package com.astamatii.londonhotel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.astamatii.londonhotel.controller.QuoteRequestController;
import com.astamatii.londonhotel.controller.QuoteRequestManagementController;

@ControllerAdvice(assignableTypes = {
		QuoteRequestController.class,
		QuoteRequestManagementController.class
})
public class QuoteRequestManagementAdvice {

	@InitBinder
	public void addCommonInitBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	public ResponseEntity<String> handle(IOException ex) {
		// implement some rules here
		
		return null;
	}
}
