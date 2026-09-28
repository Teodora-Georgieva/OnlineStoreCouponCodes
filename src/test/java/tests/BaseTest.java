package tests;

import context.TestContext;
import framework.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Workflow;

public abstract class BaseTest {
//    protected TestContext context;
    protected Workflow workflow;

    @BeforeMethod
    public void setUp() {
        DriverManager.initializeDriver();
//        context = new TestContext();
        workflow = new Workflow();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}