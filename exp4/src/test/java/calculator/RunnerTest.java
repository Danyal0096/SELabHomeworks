package calculator;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "calculator",
        plugin = {"pretty"}
)
public class RunnerTest {
    // The JUnit runner discovers and executes the feature scenarios.
}
