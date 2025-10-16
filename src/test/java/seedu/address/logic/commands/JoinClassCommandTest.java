package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.classroom.ClassName;
import seedu.address.model.classroom.TuitionClass;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Tutor;

public class JoinClassCommandTest {

    private Model model;
    private Student validStudent;
    private Tutor validTutor;
    private TuitionClass validClass;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        validStudent = new Student(
                new Name("Alice Tan"),
                new Phone("98765432"),
                new Email("alice@example.com"),
                new Address("10 Kent Ridge Road"),
                new java.util.HashSet<>()
        );

        validTutor = new Tutor(
                new Name("Mr Smith"),
                new Phone("91234567"),
                new Email("smith@example.com"),
                new Address("1 Tutor Lane"),
                new java.util.HashSet<>()
        );

        validClass = new TuitionClass(new ClassName("CS2103T"));

        model.addPerson(validStudent);
        model.addPerson(validTutor);
        model.addClass(validClass);
    }

    @Test
    public void constructor_nullPersonName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JoinClassCommand(null, "CS2103T"));
    }

    @Test
    public void constructor_nullClassName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JoinClassCommand("Alice Tan", null));
    }

    @Test
    public void execute_studentJoinClass_success() throws Exception {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        CommandResult result = command.execute(model);

        assertEquals(String.format(JoinClassCommand.MESSAGE_SUCCESS, "Student", "CS2103T", "Alice Tan"),
                result.getFeedbackToUser());
        assertTrue(validClass.hasStudent(validStudent));
    }

    @Test
    public void execute_tutorJoinClass_success() throws Exception {
        JoinClassCommand command = new JoinClassCommand("Mr Smith", "CS2103T");
        CommandResult result = command.execute(model);

        assertEquals(String.format(JoinClassCommand.MESSAGE_SUCCESS, "Tutor", "CS2103T", "Mr Smith"),
                result.getFeedbackToUser());
        assertTrue(validClass.hasTutor(validTutor));
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        JoinClassCommand command = new JoinClassCommand("NonExistent Person", "CS2103T");
        assertThrows(CommandException.class, JoinClassCommand.MESSAGE_PERSON_NOT_EXIST, (
                ) -> command.execute(model));
    }

    @Test
    public void execute_classNotFound_throwsCommandException() {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "NonExistent Class");
        assertThrows(CommandException.class, JoinClassCommand.MESSAGE_CLASS_NOT_EXIST, (
                ) -> command.execute(model));
    }

    @Test
    public void execute_studentAlreadyInClass_throwsCommandException() {
        model.addStudentToClass(validStudent, validClass);
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        assertThrows(CommandException.class, JoinClassCommand.MESSAGE_STUDENT_ALREADY_IN_CLASS, (
                ) -> command.execute(model));
    }

    @Test
    public void execute_tutorAlreadyAssigned_throwsCommandException() {
        model.assignTutorToClass(validTutor, validClass);
        JoinClassCommand command = new JoinClassCommand("Mr Smith", "CS2103T");
        assertThrows(CommandException.class, JoinClassCommand.MESSAGE_TUTOR_ALREADY_ASSIGNED, (
                ) -> command.execute(model));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        JoinClassCommand command1 = new JoinClassCommand("Alice Tan", "CS2103T");
        JoinClassCommand command2 = new JoinClassCommand("Alice Tan", "CS2103T");
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentPersonName_returnsFalse() {
        JoinClassCommand command1 = new JoinClassCommand("Alice Tan", "CS2103T");
        JoinClassCommand command2 = new JoinClassCommand("Bob Lim", "CS2103T");
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_differentClassName_returnsFalse() {
        JoinClassCommand command1 = new JoinClassCommand("Alice Tan", "CS2103T");
        JoinClassCommand command2 = new JoinClassCommand("Alice Tan", "CS2101");
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_null_returnsFalse() {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        assertFalse(command.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        assertFalse(command.equals(1));
    }

    @Test
    public void toString_validCommand_containsCorrectInfo() {
        JoinClassCommand command = new JoinClassCommand("Alice Tan", "CS2103T");
        String result = command.toString();
        assertTrue(result.contains("Alice Tan"));
        assertTrue(result.contains("CS2103T"));
    }
}
