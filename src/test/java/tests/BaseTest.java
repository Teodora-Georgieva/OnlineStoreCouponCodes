package tests;

import framework.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import workflow.Workflow;

public abstract class BaseTest {
    protected Workflow workflow;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        workflow = new Workflow();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}