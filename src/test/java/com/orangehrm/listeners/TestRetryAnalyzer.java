package com.orangehrm.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;
    @Override
    public boolean retry(ITestResult result){
        if (retryCount < MAX_RETRY_COUNT){
            retryCount++;
            System.out.println("Test"+result.getName()+"filed.Retrying....(Attempt"+retryCount + ")");
            return true;
        }
        return false;
    }

}
