package common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.ITestNGListener;


public interface IMethodInterceptorClass extends ITestNGListener {
	  List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context);
	}

/*

public class IMethodInterceptorClass implements IMethodInterceptor {
	
	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

	    List<IMethodInstance> methodlist = new ArrayList<IMethodInstance>();

	    // Read these flags from somewhere, system var / test context / file or
	    // where ever
	    Boolean shouldRunTest1 = Boolean.valueOf(context.getAttribute("SHOULD_RUN_TEST1")
	            .toString());
	    Boolean shouldRunTest2 = Boolean.valueOf(context.getAttribute("SHOULD_RUN_TEST2")
	            .toString());

	    for (IMethodInstance iMethodInstance : methods) {

	        // decide based on method name / group / priority / description or
	        // what ever
	        String methodName = iMethodInstance.getMethod().getMethodName();

	        if (iMethodInstance.getMethod().isTest()) {
	            if (shouldRunTest1 && methodName.equals("testCase1")) {
	                methodlist.add(iMethodInstance);

	            } else if (shouldRunTest2 && methodName.equals("testCase2")) {
	                methodlist.add(iMethodInstance);

	            }
	        }
	    }

	    // Here we return the test cases to be run
	    return methodlist;
	}
	
}
*/	
	
	
