package com.auto.tester.helpers;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestcaseRow {
	
	private String tcno;
	private String tcname;
	private String tcdesc;
	private String status;
	private String path;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestcaseRow(String tcno,String tcname,String tcdesc,String status) {
		this.tcno = tcno;
		this.tcname= tcname;
		this.tcdesc= tcdesc;
		this.status = status;
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
	public Map<String, String> getData(){
		HashMap<String, String> map = new LinkedHashMap<>();
		map.put("tcno", this.tcno);
		map.put("tcname", this.tcname);
		map.put("tcdesc", this.tcdesc);
		map.put("status", this.status);
		map.put("testcaselink", this.path);
		return map;
	}
	
}
