package com.auto.tester.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.auto.tester.helpers.Status;
import com.auto.tester.helpers.Suitemanager;
import com.auto.tester.helpers.Testcase;
import com.auto.tester.helpers.TestcaseRow;

public class TestListener implements ITestListener  {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		String tcname =  context.getName();
		String tcno= context.getName();
		Testcase tc = new Testcase(tcno, tcname, "testcase desc");
		tc.setStatus(Status.RUNNING);
		Suitemanager.getInstance().addTestcase(tc);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Testcase tc = Suitemanager.getInstance().getTestcase(context.getName());
		tc.updateTcStatus();
		TestcaseRow tcr = new TestcaseRow(tc.getTcno(), tc.getTcname(), tc.getTcdesc(), tc.getStatus().name());
		String path = Suitemanager.getInstance().generatetcreport(tcr);
		tcr.setPath(path);
		Suitemanager.getInstance().addTestcaseRow(tcr);
		
		System.out.println(tc.toString());
		
		
	}

}
