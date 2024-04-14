package nextstep.ladder.domain.ladder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @DisplayName("next는 현재 열 번호에서 이동할 경우 다음 열 번호를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"LEFT,2,1", "RIGHT,2,3", "NONE,2,2"})
    void next(Direction direction, int origin, int expected) {
        assertThat(direction.nextColumnIndex(new ColumnIndex(origin)))
                .isEqualTo(new ColumnIndex(expected));
    }

    @DisplayName("사다리 발판 생성 규칙은 이전 발판에 영향을 받는다.")
    @ParameterizedTest
    @CsvSource(value = {"NONE,RIGHT", "RIGHT,LEFT", "LEFT,RIGHT"})
    void generate(Direction originDirection, Direction expectedDirection) {
        assertThat(originDirection.generate(() -> true))
                .isEqualTo(expectedDirection);
    }

    @DisplayName("마지막 발판 생성은 RIGHT를 만들어 내지 않는다.")
    @ParameterizedTest
    @CsvSource(value = {"NONE,NONE", "RIGHT,LEFT"})
    void generateLast(Direction origin, Direction expectedLast) {
        assertThat(origin.generateLast())
                .isEqualTo(expectedLast);
    }

    @DisplayName("isRight는 발판이 우측으로 연결되었는지 여부를 반환한다.")
    @Test
    void isRight() {
        assertThat(Direction.RIGHT.isRight())
                .isTrue();
    }

    @DisplayName("isLeft는 발판이 우측으로 연결되었는지 여부를 반환한다.")
    @Test
    void isLeft() {
        assertThat(Direction.LEFT.isLeft())
                .isTrue();
    }

    @DisplayName("notConnected는 발판이 우측으로 연결되었는지 여부를 반환한다.")
    @Test
    void notConnected() {
        assertThat(Direction.NONE.notConnected())
                .isTrue();
    }

}
