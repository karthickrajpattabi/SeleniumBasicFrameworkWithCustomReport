package com.auto.tester.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.auto.tester.helpers.Suitemanager;

public class TestSuiteListener implements ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		Suitemanager.getInstance().generatereport();
	}

}
