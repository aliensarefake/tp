package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.classroom.TuitionClass;
import seedu.address.model.person.Student;

/**
 * Lists all classes with their enrolled students in the address book to the user.
 */
public class ListClassCommand extends Command {

    public static final String COMMAND_WORD = "listclass";
    public static final String MESSAGE_SUCCESS = "Listed all classes with their students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<TuitionClass> classList = model.getFilteredClassList();

        if (classList.isEmpty()) {
            return new CommandResult(MESSAGE_SUCCESS + "\n[No classes]");
        }

        String output = classList.stream()
                .map(this::formatClassWithStudents)
                .collect(Collectors.joining("\n\n"));

        return new CommandResult(MESSAGE_SUCCESS + "\n" + output);
    }

    /**
     * Formats a tuition class with its enrolled students.
     */
    private String formatClassWithStudents(TuitionClass tuitionClass) {
        String className = tuitionClass.getClassName();
        List<Student> students = tuitionClass.getStudents();

        if (students.isEmpty()) {
            return className + ": [No students]";
        }

        String studentNames = students.stream()
                .map(student -> student.getName().fullName)
                .collect(Collectors.joining(", "));

        return className + ": " + studentNames;
    }
}
