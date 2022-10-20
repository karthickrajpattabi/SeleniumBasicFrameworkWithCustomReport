package com.auto.tester.helpers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Testcase {
	
	
	private String tcno;
	private String tcname;
	private String tcdesc;
	private Status status;
	HashMap<String,Teststep> tsmap = new LinkedHashMap<>();
	
	public Testcase(String tcno,String tcname,String tcdesc) {
		this.tcno = tcno;
		this.tcname= tcname;
		this.tcdesc= tcdesc;
	}
	
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getTcname() {
		return tcname;
	}
	public void setTcname(String tcname) {
		this.tcname = tcname;
	}
	public String getTcdesc() {
		return tcdesc;
	}
	public void setTcdesc(String tcdesc) {
		this.tcdesc = tcdesc;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void addsteps(List<Teststep> ts) {
		int i =0;
		for (Teststep teststep : ts) {
			i++;
			tsmap.put(String.valueOf(i), teststep);
			
		}
	}
	public HashMap<String,Teststep> getsteps() {
		return tsmap;
	}
	public void updateTcStatus() {
		
		for (Map.Entry<String, Teststep> entry : tsmap.entrySet()) {
			int curtcstatus = getStatus().ordinal();
			int curspstatus = entry.getValue().getStatus().ordinal();
			if(curtcstatus > curspstatus) {
				setStatus(entry.getValue().getStatus());
			}
			
		}
		
	}

}
