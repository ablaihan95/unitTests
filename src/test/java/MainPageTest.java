import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class MainPageTest {

    @ParameterizedTest
    @ValueSource(strings = {"0123456789012345678901234", "Hello world", "W", " Hello this is a test foo"})
    public void shouldNotCutArticle(final String input) {
        String reformatted = MainPage.reformatArticleByLength(input);
        assertEquals(input, reformatted);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForCuttingAndExpectedResult")
    public void shouldCutArticle(final String input, final String expected) {
        String reformatted = MainPage.reformatArticleByLength(input);
        assertEquals(reformatted, expected);
    }

    @Test
    public void shouldGetError() {
        StringIndexOutOfBoundsException ex = assertThrows(StringIndexOutOfBoundsException.class,
                () -> MainPage.reformatArticleByLength("01234567890123456789012345"));
        assertEquals("The article cannot be shorted with full world. Because first world is longet then 25",
                ex.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldGetErrorIfArticleIsBlankOrNull(final String input) {
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> MainPage.reformatArticleByLength(input));
        assertEquals("Article should not be blank",
                ex.getMessage());
    }

    private static Stream<Arguments> provideStringsForCuttingAndExpectedResult() {
        return Stream.of(
                Arguments.of("0123456789012345678901234 cut here", "0123456789012345678901234..."),
                Arguments.of("Volvo released a new car with the following spec: V6 236HP. " +
                        "It will cost $22647 and going to be sold in New York only",
                       "Volvo released a new car..."),
                Arguments.of("Hello this is a test foo: and some describe", "Hello this is a test foo:..."),
                Arguments.of("Hello this is a test foo. Should Work fine", "Hello this is a test foo....")
        );
    }

}
