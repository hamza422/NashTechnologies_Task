import org.example.PatternHandler;
import org.javatuples.Quartet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PatternHandlerTest {
    private PatternHandler patternHandler;
    private static final String DEFAULT_FILEPATH=System.getProperty("user.dir")+"/OutputFiles/";

    @BeforeEach
    void setup(){
        patternHandler= new PatternHandler(DEFAULT_FILEPATH+"testOutput_Patterns.txt");
    }

    @Test
    void testAddPattern_provideValidInput_compairListSize(){
        //arrange
        Quartet<Integer, String, String, Boolean> pattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(pattern);
        //assert
        assertEquals(1,patternHandler.getListOfPatterns().size());
    }

    @Test
    void testAddPattern_provideInValidInput_compairListSize(){
        //arrange
        Quartet<Integer, String, String, Boolean> pattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(null);
        //assert
        assertEquals(0,patternHandler.getListOfPatterns().size());
    }

    @Test
    void testAddPattern_provideExistedIdentifierValidInput_compairListSize(){
        //arrange
        Quartet<Integer, String, String, Boolean> pattern1 = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> pattern2 = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(pattern1);
        patternHandler.addPattern(pattern2);
        //assert
        assertEquals(1,patternHandler.getListOfPatterns().size());
    }

    @Test
    void testGetPatternByIdentifier_provideValidInput_returnCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        Optional<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByIdentifier(1);

        //assert
        assertAll(
                ()-> assertTrue(actualPattern.isPresent()),
                ()-> assertEquals(ExpectedPattern, actualPattern.orElse(null))
        );
    }

    @Test
    void testGetPatternByIdentifier_provideValidInput_returnInCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        patternHandler.addPattern(ExpectedPattern2);
        Optional<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByIdentifier(2);

        //assert
        assertAll(
                ()-> assertTrue(actualPattern.isPresent()),
                ()-> assertNotEquals(ExpectedPattern, actualPattern.orElse(null))
        );
    }

    @Test
    void testGetPatternByIdentifier_provideInValidInput_returnEmpty(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(null);
        Optional<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByIdentifier(1);

        //assert
        assertAll(
                ()-> assertTrue(actualPattern.isEmpty()),
                ()-> assertNotEquals(ExpectedPattern, actualPattern.orElse(null))
        );
    }

    @Test
    void testGetPatternByName_provideValidInput_returnCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        final List<Quartet<Integer, String, String, Boolean>>  actualPattern=patternHandler.getPatternByName("testPattern");

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertEquals(ExpectedPattern.getValue1(), actualPattern.getFirst().getValue1())
        );
    }

    @Test
    void testGetPatternByName_provideValidInput_returnInCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern2", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        patternHandler.addPattern(ExpectedPattern2);
        final List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByName("testPattern2");

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertNotEquals(ExpectedPattern.getValue1(), actualPattern.getFirst().getValue1())
        );
    }

    @Test
    void testGetPatternByName_provideInValidInput_returnEmpty(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(null);
        final List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByName("testPattern");

        //assert
        assertAll(
                ()-> assertTrue(actualPattern.isEmpty()),
                ()-> assertNotEquals(ExpectedPattern, null)
        );
    }

    @Test
    void testGetPatternByCalledFlag_provideValidInput_returnCorrectListOfPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern2", "src/patterns/test.pat", true);

        //act
        patternHandler.addPattern(ExpectedPattern);
        patternHandler.addPattern(ExpectedPattern2);
        List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByCalledFlag(false);

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertEquals(ExpectedPattern.getValue3(), actualPattern.getFirst().getValue3() )
        );
    }

    @Test
    void testGetPatternByCalledFlag_provideValidInput_returnInCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern2", "src/patterns/test2.pat", true);

        //act
        patternHandler.addPattern(ExpectedPattern);
        patternHandler.addPattern(ExpectedPattern2);
        List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByCalledFlag(true);

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertNotEquals(ExpectedPattern.getValue3(), actualPattern.getFirst().getValue3())
        );
    }

    @Test
    void testGetPatternByPath_provideValidInput_returnCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByPath("src/patterns/test.pat");

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertEquals(ExpectedPattern.getValue2(), actualPattern.getFirst().getValue2())
        );
    }

    @Test
    void testGetPatternByPath_provideValidInput_returnInCorrectPattern(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);
        Quartet<Integer, String, String, Boolean> ExpectedPattern2 = Quartet.with(2, "testPattern2", "src/patterns/test2.pat", false);

        //act
        patternHandler.addPattern(ExpectedPattern);
        patternHandler.addPattern(ExpectedPattern2);
        List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByPath("src/patterns/test2.pat");

        //assert
        assertAll(
                ()-> assertEquals(1,actualPattern.size()),
                ()-> assertNotEquals(ExpectedPattern.getValue2(), actualPattern.getFirst().getValue2())
        );
    }

    @Test
    void testGetPatternByPath_provideInValidInput_returnEmpty(){
        //arrange
        Quartet<Integer, String, String, Boolean> ExpectedPattern = Quartet.with(1, "testPattern", "src/patterns/test.pat", false);

        //act
        patternHandler.addPattern(null);
        List<Quartet<Integer, String, String, Boolean>> actualPattern=patternHandler.getPatternByPath("src/patterns/test.pat");

        //assert
        assertAll(
                ()-> assertTrue(actualPattern.isEmpty()),
                ()-> assertNotEquals(ExpectedPattern, null)
        );
    }

}
