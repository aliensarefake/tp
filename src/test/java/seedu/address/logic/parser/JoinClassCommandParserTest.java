package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.JoinClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class JoinClassCommandParserTest {

    private final JoinClassCommandParser parser = new JoinClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("  "));
    }

    @Test
    public void parse_validArgs_returnsJoinClassCommand() throws Exception {
        JoinClassCommand expected = new JoinClassCommand("Alice Tan", "CS2103T");
        JoinClassCommand result = parser.parse("n/Alice Tan c/CS2103T");
        assertEquals(expected, result);
    }

    @Test
    public void parse_validArgsWithExtraWhitespace_returnsJoinClassCommand() throws Exception {
        JoinClassCommand expected = new JoinClassCommand("Alice Tan", "CS2103T");
        JoinClassCommand result = parser.parse("  n/Alice Tan   c/CS2103T  ");
        assertEquals(expected, result);
    }

    @Test
    public void parse_missingNamePrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("c/CS2103T"));
    }

    @Test
    public void parse_missingClassPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("n/Alice Tan"));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("invalid n/Alice Tan c/CS2103T"));
    }
}
