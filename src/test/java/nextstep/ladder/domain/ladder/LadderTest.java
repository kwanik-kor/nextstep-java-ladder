package nextstep.ladder.domain.ladder;

import nextstep.ladder.domain.player.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LadderTest {

    @DisplayName("사다리는 높이 만큼의 행을 가진다.")
    @Test
    void constructor() {
        assertThat(new Ladder(new Height(5), new Count(3), () -> true).height())
                .isEqualTo(new Height(5));
    }

    @DisplayName("사다리는 반드시 Row를 한 개 이상 가져야 한다.")
    @Test
    void empty_constructor() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Ladder(Collections.emptyList()));
    }
}