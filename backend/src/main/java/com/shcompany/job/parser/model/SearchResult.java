package com.shcompany.job.parser.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
	
	private String url="";
	private String resultHtml="";	
	private String resultText="";
	private List<Job> jobs=new ArrayList<Job>();	
	
	public SearchResult() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchResult [url=");
		builder.append(url);
		builder.append(", resultHtml=");
		builder.append(resultHtml);
		builder.append(", resultText=");
		builder.append(resultText);
		builder.append(", jobs=");
		builder.append(jobs);
		builder.append("]");
		return builder.toString();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getResultHtml() {
		return resultHtml;
	}

	public void setResultHtml(String resultHtml) {
		this.resultHtml = resultHtml;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	
	
}
