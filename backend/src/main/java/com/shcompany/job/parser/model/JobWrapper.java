package com.shcompany.job.parser.model;

import java.util.ArrayList;
import java.util.List;

public class JobWrapper {

	private List<Job> jobs=new ArrayList<Job>();
	private ChartWrapper charts;
	
	public JobWrapper(){};
	
	public JobWrapper(List<Job> jobs, ChartWrapper charts) {
		super();
		this.jobs = jobs;
		this.charts = charts;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public ChartWrapper getCharts() {
		return charts;
	}
	public void setCharts(ChartWrapper charts) {
		this.charts = charts;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobWrapper [jobs=");
		builder.append(jobs);
		builder.append(", charts=");
		builder.append(charts);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
