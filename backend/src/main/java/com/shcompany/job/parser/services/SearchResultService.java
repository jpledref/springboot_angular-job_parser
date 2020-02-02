package com.shcompany.job.parser.services;

import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.shcompany.job.parser.definition.model.Criterea;
import com.shcompany.job.parser.definition.model.Definition;
import com.shcompany.job.parser.model.Chart;
import com.shcompany.job.parser.model.ChartWrapper;
import com.shcompany.job.parser.model.Job;
import com.shcompany.job.parser.model.SearchResult;

@Service
public class SearchResultService {
	Logger LOG = LoggerFactory.getLogger(SearchResultService.class);	
	
	@Autowired 
	private CacheManager cacheManager;   
	
	@Scheduled(cron = "${cache.clear.cron:2 */15 * * * ?}")              
    public void clearCacheSchedule(){
		LOG.info("Clearing cache...");
		cacheManager.getCache("searches").clear(); 
        /*for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            
        }*/
    }

	@Cacheable("searches")
	public SearchResult parseByDefinition(Definition d,String what,String where){
		SearchResult ret=new SearchResult();
		
		long startTime = System.currentTimeMillis();
		
		StringBuilder url=new StringBuilder(d.getSearch());			
		Map<String, Criterea> mapCrit=d.getMapCritereas();				
		
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
			Document doc = Jsoup
					.connect(url.toString())
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.get();
			res=doc.select(d.getCssListSelector());
		} catch (IOException e) {
			e.printStackTrace();
		}
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    LOG.debug("Execution time:"+elapsedTime+" for "+ url.toString());
		
		ret.setUrl(url.toString());
		ret.setResultHtml(res!=null?res.html().toString():"");			
		ret.setResultText(res!=null?res.text():"");			
		ret.setJobs(processHTML(d,ret));	
		
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
				
				if(res.getTitle()!=null&&!res.getTitle().equals(""))ret.add(res);				
			});
		}				
		return ret;
	}	
	
	public ChartWrapper buildChartWrapper(List<Job> jobs){
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
