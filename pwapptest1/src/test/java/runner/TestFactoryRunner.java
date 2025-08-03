package runner;

import com.pwcore.factory.FeatureTestFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

public class TestFactoryRunner {

    @TestFactory
    Stream<DynamicTest> runScenariosFromJson() {
        return FeatureTestFactory.runAllFeaturesWithData(CucumberTestRunner.class);
    }
}
