
package com.shcompany.job.parser.definition.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "search",
    "method",
    "critereas"
})
public class Definition {

    @JsonProperty("name")
    private String name;
    @JsonProperty("search")
    private String search;
    @JsonProperty("home")
    private String home;
    @JsonProperty("method")
    private String method;
    @JsonProperty("cssListSelector")
    private String cssListSelector;
    @JsonProperty("critereas")
    private List<Criterea> critereas = null;
    @JsonProperty("cssItemSelector")
    private List<ItemSelector> selectors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonIgnore
    private Map<String, Criterea> mapCritereas = new HashMap<String, Criterea>();
    @JsonIgnore
    private Map<String, ItemSelector> mapSelectors = new HashMap<String, ItemSelector>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Definition() {
    }

    public Definition(String name, String home, String search, String method, String cssListSelector, List<Criterea> critereas) {
		super();
		this.name = name;
		this.search = search;
		this.home = home;
		this.method = method;
		this.cssListSelector = cssListSelector;
		this.critereas = critereas;
	}

	@JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("search")
    public String getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(String search) {
        this.search = search;
    }
    
    @JsonProperty("home")
    public String getHome() {
		return home;
	}

    @JsonProperty("home")
	public void setHome(String home) {
		this.home = home;
	}

	@JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }    

    @JsonProperty("cssListSelector")
    public String getCssListSelector() {
        return cssListSelector;
    }

    @JsonProperty("cssListSelector")
    public void setCssListSelector(String cssListSelector) {
        this.cssListSelector = cssListSelector;
    }

    @JsonProperty("critereas")
    public List<Criterea> getCritereas() {
        return critereas;
    }

    @JsonProperty("critereas")
    public void setCritereas(List<Criterea> critereas) {
        this.critereas = critereas;        
        mapCritereas=critereas.stream().collect(
                Collectors.toMap(Criterea::getName, a->{return a;}));       
    }

    @JsonProperty("cssItemSelector")
    public List<ItemSelector> getSelectors() {
		return selectors;
	}

    @JsonProperty("cssItemSelector")
	public void setSelectors(List<ItemSelector> selectors) {
		this.selectors = selectors;	      
        mapSelectors=selectors.stream().collect(
                Collectors.toMap(ItemSelector::getName, a->{return a;}));  
	}

	public Map<String, Criterea> getMapCritereas() {
		return mapCritereas;
	}
	
	public Map<String, ItemSelector> getMapSelectors() {
		return mapSelectors;
	}

	@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("home", home).append("search", search).append("method", method).append("cssListSelector", cssListSelector).append("critereas", critereas).append("selectors", selectors).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(home).append(critereas).append(selectors).append(search).append(additionalProperties).append(method).append(cssListSelector).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Definition) == false) {
            return false;
        }
        Definition rhs = ((Definition) other);
        return new EqualsBuilder().append(name, rhs.name).append(home, rhs.home).append(critereas, rhs.critereas).append(selectors, rhs.selectors).append(search, rhs.search).append(additionalProperties, rhs.additionalProperties).append(method, rhs.method).append(cssListSelector, rhs.cssListSelector).isEquals();
    }

}
