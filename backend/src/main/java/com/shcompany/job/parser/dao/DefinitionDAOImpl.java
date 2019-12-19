package com.shcompany.job.parser.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


import com.shcompany.job.parser.definition.model.Definition;
import com.shcompany.job.parser.threads.DefinitionsLoader;

@Repository
public class DefinitionDAOImpl implements DefinitionDAO {

	public List<Definition>definitions=new ArrayList<>();	
	
	private DefinitionDAOImpl(){		
		DefinitionsLoader dl=new DefinitionsLoader();
		dl.setDefinitionDAO(this);
		Thread thread = new Thread(dl);	
		thread.start();		
    }	
	
	@Override
	public List<Definition> findAll() {
		return definitions;
	}

	@Override
	public void save(List<Definition> list) {
		definitions=list;
	}

}
