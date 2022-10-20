package com.auto.tester.autotester;

public interface IBase {
	
   
   
   void fail(String sexp, String sact);
   
   void warn(String sexp, String sact);
   
   void info(String sexp, String sact);

   void pass(String sexp, String sact);
   

}
