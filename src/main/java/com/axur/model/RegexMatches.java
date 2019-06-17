package com.axur.model;

import java.io.Serializable;
import java.util.List;

public class RegexMatches implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean match;
	private List<String> regexlist;
	private Integer correlationId;
	
	public boolean isMatch() {
		return match;
	}
	public void setMatch(boolean match) {
		this.match = match;
	}
	public List<String> getRegexlist() {
		return regexlist;
	}
	public void setRegexlist(List<String> regexlist) {
		this.regexlist = regexlist;
	}
	public Integer getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(Integer correlationId) {
		this.correlationId = correlationId;
	}
	
	
}
