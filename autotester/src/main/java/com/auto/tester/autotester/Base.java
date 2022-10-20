package com.auto.tester.autotester;

import java.util.LinkedList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.internal.annotations.ITest;

import com.auto.tester.helpers.Status;
import com.auto.tester.helpers.Suitemanager;
import com.auto.tester.helpers.Testcase;
import com.auto.tester.helpers.Teststep;

public class Base implements IBase{
	
	List<Teststep> slist = new LinkedList<>();

	@Override
	public void pass(String sexp,String sact) {
		// TODO Auto-generated method stub
		slist.add(new Teststep(sexp, sact, Status.PASS)); 
	}

	@Override
	public void fail(String sexp,String sact) {
		// TODO Auto-generated method stub
		slist.add(new Teststep(sexp, sact, Status.FAIL)); 
	}

	@Override
	public void warn(String sexp,String sact) {
		// TODO Auto-generated method stub
		slist.add(new Teststep(sexp, sact, Status.WARN)); 
	}

	@Override
	public void info(String sexp,String sact) {
		// TODO Auto-generated method stub
		slist.add(new Teststep(sexp, sact, Status.INFO)); 
	}
	@AfterClass
	public void save(ITestContext test) {
		
		Testcase tc = Suitemanager.getInstance().getTestcase(test.getName());
		tc.addsteps(slist);
		
	}
	
	
	

}
