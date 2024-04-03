import org.example.PatternFileHandler;
import org.javatuples.Quartet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatternFileHandlerTest {

    private static final String DEFAULT_FILEPATH=System.getProperty("user.dir")+"/OutputFiles/";
    private static final String TESTFILE_NAME="testOutput.txt";

    @Test
    void testLoadPatternsFromFile_provideCorrectFileName_getListOfPatterns(){
        //arrange
        //act
        List<Quartet<Integer, String, String, Boolean>> listOfPatterns= PatternFileHandler.loadPatternsFromFile(DEFAULT_FILEPATH+TESTFILE_NAME);

        //assert
        assertEquals(2,listOfPatterns.size());
    }

    @Test
    void testStorePatternsFromFile_provideCorrectFileName_getListOfPatterns(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern2", "src/patterns/test2.pat", true);
        List<Quartet<Integer, String, String, Boolean> > ExpectedListOfPatterns= new ArrayList<>(Arrays.asList(ExpectedPattern,ExpectedPattern2));
        //act
         PatternFileHandler.storePatternsIntoFile("testOutput_stored.txt",ExpectedListOfPatterns);
        List<Quartet<Integer, String, String, Boolean>> actualListOfPatterns= PatternFileHandler.loadPatternsFromFile(DEFAULT_FILEPATH+"testOutput_stored.txt");

        //assert
        assertAll(
                ()-> assertFalse(actualListOfPatterns.isEmpty()),
                ()-> assertEquals(ExpectedListOfPatterns.size(),actualListOfPatterns.size())
        );
    }

}
