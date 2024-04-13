package nextstep.ladder;

import nextstep.ladder.domain.ladder.Height;
import nextstep.ladder.domain.ladder.Ladder;
import nextstep.ladder.domain.ladder.RandomRungGenerator;
import nextstep.ladder.domain.player.Count;
import nextstep.ladder.domain.player.Players;
import nextstep.ladder.domain.result.GameResults;
import nextstep.ladder.domain.result.Results;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.OutputView;

public class LadderGamePlayer {

    private void play() {
        final Players players = Players.from(InputView.playerNames());

        final Results results = Results.fromResultNames(InputView.gameResults(), players.count());

        final Height height = new Height(InputView.ladderHeight());

        final Ladder ladder = ladderByRandomRungGenerator(players.count(), height);

        OutputView.printLadder(players, ladder, results);

        final GameResults gameResults = ladder.gameResults(players, results);

    }

    private Ladder ladderByRandomRungGenerator(Count playersCount, Height height) {
        return new Ladder(height, playersCount, new RandomRungGenerator());
    }

    public static void main(String[] args) {
        final LadderGamePlayer player = new LadderGamePlayer();
        player.play();
    }
}
