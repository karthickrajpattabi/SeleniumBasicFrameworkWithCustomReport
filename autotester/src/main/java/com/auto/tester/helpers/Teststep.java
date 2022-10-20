package com.auto.tester.helpers;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Teststep {
	private String sexp;
	private String sact;
	private Status sstatus;
	
	public Teststep(String sexp,String sact,Status sstatus) {
		this.sexp = sexp;
		this.sact = sact;
		this.sstatus = sstatus;
	}
	
	public String getSexp() {
		return sexp;
	}
	public void setSexp(String sexp) {
		this.sexp = sexp;
	}
	public String getSact() {
		return sact;
	}
	public void setSact(String sact) {
		this.sact = sact;
	}
	public Status getStatus() {
		return sstatus;
	}
	public void setStatus(Status sstatus) {
		this.sstatus = sstatus;
	}
	public HashMap<String, String> getData(){
		HashMap<String, String>  data = new LinkedHashMap<String, String>();
		data.put("Expected", this.sexp);
		data.put("Actual", this.sact);
		data.put("Status", this.sstatus.name());
		return data;
	}
}
