package com.shcompany.job.parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/info")
public class InfoController {

	@RequestMapping(
	  value = "/time", 
	  produces = { "text/html" }, 
	  method = RequestMethod.GET)
	private String getTime(){		
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
	}
	
}
