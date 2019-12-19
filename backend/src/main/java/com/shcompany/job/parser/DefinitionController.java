package com.shcompany.job.parser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shcompany.job.parser.dao.DefinitionDAO;
import com.shcompany.job.parser.definition.model.Definition;

@RestController
//@RequestMapping(value="/info")
public class DefinitionController {
	
	@Autowired
    private DefinitionDAO definitionDAO;	
		

	@RequestMapping(
	  value = "/definitions", 
	  produces = { "application/json" }, 
	  method = RequestMethod.GET)
	private List<Definition> getDefinitions(){		
		List<Definition> definitions=definitionDAO.findAll();		
		return definitions;
	}
	
}
