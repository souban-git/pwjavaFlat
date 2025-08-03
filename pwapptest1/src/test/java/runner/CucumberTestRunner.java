package runner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // folder under `src/test/resources/features`
@ConfigurationParameter(key = "cucumber.glue", value = "steps,com.pwcore.hooks")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-html-report")
public class CucumberTestRunner {
}