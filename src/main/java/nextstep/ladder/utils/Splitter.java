package nextstep.ladder.utils;

public class Splitter {
    private static final String COMMA_DELIMITER = ",";

    private Splitter() {}

    public static String[] byComma(String input) {
        assertNotBlank(input);
        return input.split(COMMA_DELIMITER);
    }

    private static void assertNotBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("문자열을 입력해주세요.");
        }
    }

}
