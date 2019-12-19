package com.shcompany.job.parser.dao;

import java.util.List;

import com.shcompany.job.parser.definition.model.Definition;

public interface DefinitionDAO {
	public List<Definition> findAll();
	public void save(List<Definition> list);
}
