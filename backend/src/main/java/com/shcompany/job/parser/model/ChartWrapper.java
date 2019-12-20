package com.shcompany.job.parser.model;


public class ChartWrapper {

	private Chart pie=new Chart();

	
	public ChartWrapper() {};	
	
	
	public ChartWrapper(Chart pie) {
		super();
		this.pie = pie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChartWrapper [pie=");
		builder.append(pie);
		builder.append("]");
		return builder.toString();
	}

	public Chart getPie() {
		return pie;
	}

	public void setPie(Chart pie) {
		this.pie = pie;
	}
	
	
	
}
