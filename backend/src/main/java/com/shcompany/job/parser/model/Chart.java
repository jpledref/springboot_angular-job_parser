package com.shcompany.job.parser.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Chart {
	
	private List<Object> values=new LinkedList<Object>();
	private Set<String> labels=new LinkedHashSet<String>();
	
	public Chart(){};	
	
	public Chart(List<Object> values, Set<String> labels) {
		super();
		this.values = values;
		this.labels = labels;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chart [values=");
		builder.append(values);
		builder.append(", labels=");
		builder.append(labels);
		builder.append("]");
		return builder.toString();
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public Set<String> getLabels() {
		return labels;
	}
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}
	
}
