package nextstep.ladder.view;

import nextstep.ladder.domain.ladder.Ladder;
import nextstep.ladder.domain.ladder.Row;
import nextstep.ladder.domain.player.Count;
import nextstep.ladder.domain.player.Players;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nextstep.ladder.view.MyPrinter.*;

public class OutputView {

    private OutputView() {}

    public static void printLadder(Players players, Ladder ladder) {
        printLine(Announcements.RESULT);

        lineChange();

        final int columnWidth = columnWidth(players);
        printPlayers(players, columnWidth);
        printLadder(ladder, columnWidth);
    }

    private static int columnWidth(Players players) {
        final Count maxLength = players.maxNameLength();
        return (maxLength.value() / 2 + 1) * 2 + 1;
    }

    private static void printPlayers(Players players, int columnWidth) {
        String joinedName = players.playerNames().stream()
                .map(name -> centerName(name, columnWidth))
                .collect(Collectors.joining());

        printLine(joinedName);
    }

    private static void printLadder(Ladder ladder, int columnWidth) {
        ladder.forEachRows(row -> printLadderRow(row, columnWidth));
    }

    private static void printLadderRow(Row ladderRow, int columnWidth) {
        final String leftPad = spaceBuilder(columnWidth / 2);
        final String emptyRungString = spaceBuilder(columnWidth - 1);
        final String rungString = rungBuilder(columnWidth - 1);

        final String row = ladderRow.rungs().stream()
                .map(rung -> {
                    StringBuilder sb = new StringBuilder(Announcements.COLUMN);
                    if (rung.exist()) {
                        return sb.append(emptyRungString).toString();
                    }
                    return sb.append(rungString).toString();
                })
                .collect(Collectors.joining());

        StringBuilder builder = new StringBuilder(leftPad)
                .append(row)
                .append(Announcements.COLUMN);
        printLine(builder.toString());
    }

    private static String centerName(String name, int width) {
        int rightPad = (width - name.length()) / 2;
        int leftPad = width - name.length() - rightPad;

        StringBuilder builder = new StringBuilder();
        builder.append(spaceBuilder(leftPad));
        builder.append(name);
        builder.append(spaceBuilder(rightPad));

        return builder.toString();
    }

    private static String spaceBuilder(int length) {
        return stringRepeater(length, Announcements.SPACE);
    }

    private static String rungBuilder(int length) {
        return stringRepeater(length, Announcements.RUNG);
    }

    private static String stringRepeater(int length, String target) {
        return IntStream.range(0, length)
                .mapToObj(i -> target)
                .collect(Collectors.joining());
    }

    private static abstract class Announcements {
        static final String RESULT = "실행결과";
        static final String SPACE = " ";
        static final String COLUMN = "|";
        static final String RUNG = "-";
    }
}