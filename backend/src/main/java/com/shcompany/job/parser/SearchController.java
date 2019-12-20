package com.shcompany.job.parser;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shcompany.job.parser.dao.DefinitionDAO;
import com.shcompany.job.parser.definition.model.Criterea;
import com.shcompany.job.parser.definition.model.Definition;
import com.shcompany.job.parser.model.Chart;
import com.shcompany.job.parser.model.ChartWrapper;
import com.shcompany.job.parser.model.Job;
import com.shcompany.job.parser.model.JobWrapper;
import com.shcompany.job.parser.model.SearchResult;

@RestController
public class SearchController {
	
	@Autowired
    private DefinitionDAO definitionDAO;		
	
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
		ret.setCharts(buildChartWrapper(listJob));		
		
		return ret;
	}
	
	@RequestMapping(value = "/search",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	private List<SearchResult> doSearch(@RequestParam(required = true,defaultValue = "informatique") String what,@RequestParam(required = true,defaultValue ="lyon") String where){	
		List<SearchResult> ret=new ArrayList<SearchResult>();		
		List<Definition> definitions=definitionDAO.findAll();

		definitions.stream().forEach(s->{
			SearchResult a=new SearchResult();
			
			StringBuilder url=new StringBuilder(s.getSearch());			
			Map<String, Criterea> mapCrit=s.getMapCritereas();				
			
			if(what!=null&what!=""&mapCrit.containsKey("what")){
				Criterea c=mapCrit.get("what");
				
				url.append("&").append(c.getValue())
				 .append("=").append(URLEncoder.encode(what));				
			}
			
			if(where!=null&where!=""&mapCrit.containsKey("where")){
				Criterea c=mapCrit.get("where");
				
				//Dirty fix for APEC
				if(!c.getValue().contains("=")){
					url.append("&").append(c.getValue())
					 .append("=").append(URLEncoder.encode(where));
				}
				else{
					url.append("&").append(URLEncoder.encode(c.getValue()));
				}						
			}			
			
			Elements res=null;
			try {
				Document doc = Jsoup.connect(url.toString()).get();
				res=doc.select(s.getCssListSelector());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			a.setUrl(url.toString());
			a.setResultHtml(res.html().toString());			
			a.setResultText(res.text());			
			a.setJobs(processHTML(s,a));			
			
			ret.add(a);
		});
		
		return ret;
	}
	
	private List<Job> processHTML(Definition d,SearchResult s){
		List<Job> ret=new ArrayList<Job>();		
		Document doc = Jsoup.parse(s.getResultHtml());
		
		Elements jobList=null;		
		if(d.getMapSelectors().containsKey("itemBox")){
			jobList=doc.select(d.getMapSelectors().get("itemBox").getValue());			
			jobList.stream().forEach(j->{
				Job res=new Job();				
				res.setTitle(getValOrEmpty(j,d,"jobTitle"));
				res.setSalary(getValOrEmpty(j,d,"jobSalary"));
				res.setEnterprise(getValOrEmpty(j,d,"jobEnterprise"));
				res.setDescription(getValOrEmpty(j,d,"jobDescription"));	
				res.setSiteSource(d.getName());		
				res.setLocation(getValOrEmpty(j,d,"jobLocation"));
				
				/*Sanitize url*/
				String url=getAttrOrEmpty(j,d,"jobLink","href");
				if(url!=null && !url.equals("")){
					if(!url.startsWith("https://")&&!url.startsWith("http://")){
						url=d.getHome()+url;						
					}
				}else{
					url=s.getUrl();
				}
				res.setLink(url);
				
				ret.add(res);				
			});
		}				
		return ret;
	}	
	
	private ChartWrapper buildChartWrapper(List<Job> jobs){
		ChartWrapper ret=new ChartWrapper();
		
		//Pie
		Chart pie=new Chart();
		List<Object> values=new LinkedList<Object>();
		Set<String> labels=new LinkedHashSet<String>();
		Map<String, Long> counted = jobs.stream()
	            .collect(Collectors.groupingBy(Job::getSiteSource, Collectors.counting()));
		counted.entrySet().stream().forEach(kv->{		
			labels.add(kv.getKey());			
			values.add(kv.getValue());		
		});		
		pie.setLabels(labels);
		pie.setValues(values);
		ret.setPie(pie);
		
		
		return ret;
	}
	
	
	
	private String getValOrEmpty(Element e,Definition d, String selector) {			
		return d.getMapSelectors().containsKey(selector)&&!d.getMapSelectors().get(selector).getValue().equals("")
				?e.select(d.getMapSelectors().get(selector).getValue()).text()
				:"";		
	}
	
	private String getAttrOrEmpty(Element e,Definition d, String selector, String attr) {			
		return d.getMapSelectors().containsKey(selector)&&!d.getMapSelectors().get(selector).getValue().equals("")
				?e.select(d.getMapSelectors().get(selector).getValue()).attr(attr)
				:"";		
	}
}
