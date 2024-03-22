package partTwo.lessonLaunch16;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//Путь от джавы в тесте
@SelectPackages("junit5.example")
@IncludeTags("search")
public class SuiteTest {
}
