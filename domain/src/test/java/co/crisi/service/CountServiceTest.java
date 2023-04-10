package co.crisi.service;

import co.crisi.service.objectmother.TextInfoObjectMother;
import java.util.List;
import java.util.Map;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountServiceTest {

    private final CountService countService = new CountService();


    @Nested
    class CountWords {

        @Test
        void givenNormalText_shouldReturnCorrectNumberOfWords() {
            val textInfo = TextInfoObjectMother.withText();
            val expectedWords = 32L;

            long words = countService.countWords(textInfo);

            assertThat(words)
                    .isEqualTo(expectedWords);
        }

    }


    @Nested
    class CountGroupBy {

        @Test
        void whenNormalText_shouldMapCorrectCounting() {
            val textInfo = TextInfoObjectMother.withTextToGroupBy();

            Map<String, Long> wordCount = countService.countGroupByWord(textInfo);

            assertThat(wordCount)
                    .isNotEmpty()
                    .containsEntry("THE", 2L)
                    .containsEntry("SAME", 1L)
                    .containsEntry("THAT", 2L)
                    .containsEntry("IS", 2L)
                    .containsEntry("AS", 1L)
                    .containsEntry("TIMES", 1L)
                    .containsEntry("TWO", 1L)
                    .containsEntry("ABOUT", 1L)
                    .containsEntry("THING", 1L);
        }

    }

    @Nested
    class GetMostRepeatedWords {

        @Test
        void whenNormalText_shouldRetrieve10MostRepeatedWords() {
            val textInfo = TextInfoObjectMother.withTextToGroupBy();
            val expectedOrder = List.of("THAT", "THE", "IS", "SAME", "AS", "TIMES", "THIS", "TWO", "ABOUT", "THING");

            val mostRepeatedWords = countService.getMostRepeatedWords(textInfo);

            assertThat(mostRepeatedWords)
                    .isNotEmpty()
                    .doesNotHaveDuplicates()
                    .isEqualTo(expectedOrder);
        }

    }

}