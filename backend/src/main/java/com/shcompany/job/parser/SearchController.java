package com.shcompany.job.parser;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shcompany.job.parser.dao.DefinitionDAO;
import com.shcompany.job.parser.definition.model.Definition;
import com.shcompany.job.parser.model.Job;
import com.shcompany.job.parser.model.JobWrapper;
import com.shcompany.job.parser.model.SearchResult;
import com.shcompany.job.parser.services.SearchResultService;

@RestController
public class SearchController {
	Logger LOG = LoggerFactory.getLogger(SearchController.class);
	
	
	@Autowired
    private DefinitionDAO definitionDAO;	
	
	@Autowired
	private SearchResultService searchResultService;
	
	@RequestMapping(value = "/jobs",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	private JobWrapper getSearchJob(@RequestParam(required = true,defaultValue = "informatique") String what,@RequestParam(required = true,defaultValue ="lyon") String where){
		JobWrapper ret=new JobWrapper();
		
		List<SearchResult> listSR=doSearch(what,where);		
		
		//Jobs
		List<Job> listJob=new ArrayList<Job>();			
		listSR.stream().forEach(s->{			
			listJob.addAll(s.getJobs());			
		});		
		ret.setJobs(listJob);
		
		//Extracting charts
		ret.setCharts(searchResultService.buildChartWrapper(listJob));		
		
		return ret;
	}
	
	@RequestMapping(value = "/search",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	private List<SearchResult> doSearch(@RequestParam(required = true,defaultValue = "informatique") String what,@RequestParam(required = true,defaultValue ="lyon") String where){	
		List<SearchResult> ret=new ArrayList<SearchResult>();		
		List<Definition> definitions=definitionDAO.findAll();

		definitions.stream().forEach(s->{
			SearchResult a=searchResultService.parseByDefinition(s,what,where);					
			ret.add(a);
		});
		
		return ret;
	}
	
	@RequestMapping(value = "/search/{name}",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	private SearchResult doSearchByName(@PathVariable(required = true) String name,@RequestParam(required = true,defaultValue = "informatique") String what,@RequestParam(required = true,defaultValue ="lyon") String where){	
		SearchResult ret=new SearchResult();		
		Definition definition=definitionDAO.findByName(name);

		if(definition!=null){
			ret=searchResultService.parseByDefinition(definition,what,where);			
		}
		
		return ret;
	}
	
	
}
