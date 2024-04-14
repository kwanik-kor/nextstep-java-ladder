package nextstep.ladder.domain.ladder;

import nextstep.ladder.domain.player.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RowTest {

    @DisplayName("moveFrom은 특정 열에서 움직인 결과인 열 번호를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,1", "1,0", "2,2"})
    void moveFrom(int startIndex, int endIndex) {
        final Row row = new Row(new Count(3), () -> true);
        assertThat(row.moveFrom(new ColumnIndex(startIndex)))
                .isEqualTo(new ColumnIndex(endIndex));
    }
}
