package com.auto.tester.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Suitemanager {
	
	private static List<Testcase> tcs = new LinkedList<>();
	
	private static List<TestcaseRow> tcr = new LinkedList<>();
		  
	private static Suitemanager Suitemanager = null;
	
	private static String testhtmldata ="";
	
	private static String[] headers= {"tcno","tcname","tcdesc","status","testcaselink"};
	private static String[] stepheaders= {"Expected","Actual","Status"};

	private Suitemanager() {
		testhtmldata+=HtmlHelpers.createTable("testcases");
		testhtmldata+=HtmlHelpers.endtable();
		testhtmldata+=HtmlHelpers.createrow();
		for (String str : headers) {
			testhtmldata+=HtmlHelpers.createcolHeader(str);
		}
		testhtmldata+=HtmlHelpers.endrow();
		
	}
	
	public static Suitemanager getInstance() {
		
		if(Suitemanager == null) {
			synchronized (Suitemanager.class) {
				if(Suitemanager == null) {
					Suitemanager = new Suitemanager();
					return Suitemanager;
				}
			}
		}
		
		return Suitemanager;
		
	}
	
	public void addTestcase(Testcase tc) {
		tcs.add(tc);
	}
	public void addTestcaseRow(TestcaseRow tr) {
		tcr.add(tr);
		updatehtmldata(tr);
	}
	public static Testcase getTestcase(String name) {
		
		for (Testcase testcase : tcs) {
			
			if(testcase.getTcname().equalsIgnoreCase(name)) {
				return testcase;
			}
			
		}
		return null;
	}
	public static String updatehtmldata(TestcaseRow tr) {
		
		Map<String, String> data = tr.getData();
		if(data.size() > 0) {
			testhtmldata+=HtmlHelpers.createrow();
			for (String key : data.keySet()) {
				if(key.equalsIgnoreCase("testcaselink")) {
					testhtmldata+=HtmlHelpers.createtablelinkdata(key,data.get(key));
				}else {
					testhtmldata+=HtmlHelpers.createtabledata(key,data.get(key));
				}
				
				
			}
			testhtmldata+=HtmlHelpers.endrow();
		}
		
		return "";
	}
	
	public static String generatesteps(TestcaseRow tr) {
		String stepshtml="";
		Testcase tc = getTestcase(tr.getTcname());
		stepshtml+=HtmlHelpers.createspan("Testcase : "+tc.getTcname());
		stepshtml+=HtmlHelpers.createspan("Testdesc : "+tc.getTcdesc());
		stepshtml+=HtmlHelpers.createspan("Status : "+tc.getStatus().name());
		Map<String, Teststep> ts = tc.getsteps();
		if(ts.size() > 0) {
			stepshtml += HtmlHelpers.createTable("teststeps");
			stepshtml += HtmlHelpers.endtable();
			stepshtml += HtmlHelpers.createrow();
			for (String str : stepheaders) {
				stepshtml+=HtmlHelpers.createcolHeader(str);
			}
			stepshtml += HtmlHelpers.endrow();
			for (Map.Entry<String, Teststep> entry : ts.entrySet()) {
				stepshtml += HtmlHelpers.createrow();
				HashMap<String, String> steps = entry.getValue().getData();
				for (Map.Entry<String, String> ent : steps.entrySet()) {
					String key = ent.getKey();
					String val = ent.getValue();
					stepshtml += HtmlHelpers.createtabledata(key, val);
				}
				stepshtml += HtmlHelpers.endrow();
			}
			
		}
		
		return stepshtml;
		
	}
	public static String generatetcreport(TestcaseRow tr) {
		 String htmlcontent = readContent("D:\\Automation\\testcase-report.html");
		 String modified = htmlcontent.replace("${teststeps}", generatesteps(tr));
		 String tcpath = "./testcases/"+tr.getTcname();
		 createFolder(tcpath);
		 savereport(tcpath+"/testcase-report.html", modified);
		 return Paths.get("").toAbsolutePath().toString()+File.separator+"testcases\\"+tr.getTcname()+"\\testcase-report.html";
		 
		 
		 
	 }
	public static void createFolder(String path) {
		if(!new File(path).exists()) {
			new File(path).mkdirs();
		}
	}
	
	 public static void generatereport() {
		 String htmlcontent = readContent("D:\\Automation\\automation-report.html");
		 String modified = htmlcontent.replace("${testcases}", testhtmldata);
		 savereport("./automation-report.html", modified);
		 
		 
		 
	 }
	 
	 public static String readContent(String path) {
		
			        
			        StringBuilder html = new StringBuilder();
			 
			        // Reading html file on local directory
			        FileReader fr = null;
					try {
						fr = new FileReader(new File(path));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
			        // Try block to check exceptions
			        try {
			 
			            // Initialization of the buffered Reader to get
			            // the String append to the String Builder
			            BufferedReader br = new BufferedReader(fr);
			 
			            String val;
			 
			            // Reading the String till we get the null
			            // string and appending to the string
			            while ((val = br.readLine()) != null) {
			                html.append(val);
			            }
			 
			            // AtLast converting into the string
			            String result = html.toString();
			            return result;
			        }
			 
			        // Catch block to handle exceptions
			        catch (Exception ex) {
			 
			            /* Exception of not finding the location and
			            string reading termination the function
			            br.close(); */
			            System.out.println(ex.getMessage());
			        }
					return "";
			    }
			public static void savereport(String path,String data) {
				FileWriter fw;
				if(new File(path).exists()) {
					new File(path).delete();
					try {
						new File(path).createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						new File(path).createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					fw = new FileWriter(new File(path));
					fw.write(data);
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

}
