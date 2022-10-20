package com.auto.tester.helpers;

public class HtmlHelpers {
	
	
	public static String createTable(String tname) {
		return "<table id='"+tname+"' style= 'width : 98%'";
	}
	public static String endtable() {
		return "</table>";
	}
	public static String createrow() {
		return "<tr>";
	}
	public static String endrow() {
		return "</tr>";
	}
	public static String createrow(String rowname) {
		return "<tr  id='"+rowname+"'>";
	}
	public static String createcolHeader(String col) {
		return "<th>"+col+"</th>";
	}
	public static String createspan(String val) {
		return "<span>"+val+"</span>";
	}
	public static String createlink(String linkpath) {
		return "<a href='"+linkpath+"' target='_blank' onclick='window.open('"+linkpath+"','popup');return false;'>View</a>";
	}
	public static String createtabledata(String col,String val) {
		return "<td class='default' column='"+col+"'>"+val+"</td>";
	}
	public static String createtablelinkdata(String col,String linkpath) {
		return "<td class='default' column='"+col+"'>"+createlink(linkpath)+"</td>";
	}
}
