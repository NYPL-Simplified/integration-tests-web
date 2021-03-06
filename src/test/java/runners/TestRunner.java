package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {
                "hooks",
                "stepdefinitions"
        },
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "aquality.tracking.integrations.cucumber5jvm.AqualityTrackingCucumber5Jvm"
        },
        strict = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
