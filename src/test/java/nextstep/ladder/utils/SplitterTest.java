package nextstep.ladder.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SplitterTest {

    @DisplayName("byDelimiter는")
    @Nested
    class Describe_byDelimiter {

        @DisplayName("입력받은 문자열이 null 이거나 비어있다면 IllegalArgumentExcpetion을 던진다.")
        @ParameterizedTest
        @NullAndEmptySource
        public void throwExceptionWhenEmptyString(String input) {
            assertThatIllegalArgumentException().isThrownBy(() -> Splitter.byDelimiter(input, Splitter.COMMA_DELIMITER));
        }

        @DisplayName("입력받은 문자열을 쉼표 문자로 split 할 수 있다")
        @Test
        void byComma() {
            final String input = "1,2,3,4,5,6";
            final List<String> result = Splitter.byDelimiter(input, Splitter.COMMA_DELIMITER);

            assertThat(result).containsExactly("1", "2", "3", "4", "5", "6");
        }

    }
}
