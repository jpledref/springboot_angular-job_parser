package com.shcompany.job.parser.model;

public class Job {
	private String title="";
	private String salary="";
	private String location="";
	private String enterprise="";
	private String description="";
	private String siteSource="";
	private String link="";
		
	public Job() {
		super();
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [title=");
		builder.append(title);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", location=");
		builder.append(location);
		builder.append(", enterprise=");
		builder.append(enterprise);
		builder.append(", description=");
		builder.append(description);
		builder.append(", siteSource=");
		builder.append(siteSource);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSiteSource() {
		return siteSource;
	}
	public void setSiteSource(String siteSource) {
		this.siteSource = siteSource;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
