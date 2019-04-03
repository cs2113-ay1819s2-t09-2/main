package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.FileUtil.isFileExists;

import java.io.IOException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.task.Task;

/**
 * Import the tasks to the task book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String COMMAND_PARAMETERS = "Parameters: FILENAME (must end with .json)\n";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " import.json";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import the tasks in the file specified to "
            + "Tasketch.\n" + COMMAND_PARAMETERS + COMMAND_EXAMPLE;

    public static final String MESSAGE_IMPORT_SUCCESS = "Imported %1$s task(s).";

    private static final String MESSAGE_FAILURE = "Import failed! Error: %1$s";
    private static final String MESSAGE_INVALID_LIST_SIZE = "Invalid list size.";
    private static final String MESSAGE_FILE_NOT_FOUND = "File not found!";

    private final Path filePath;

    public ImportCommand(Path filePath) {
        requireNonNull(filePath);

        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ReadOnlyTaskBook readOnlyTasksBook = model.getTaskBook();
        ObservableList<Task> taskList = readOnlyTasksBook.getTaskList();
        int initialNumberOfPersons = taskList.size();

        if (!isFileExists(filePath)) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        }

        try {
            model.importTasksFromTaskBook(filePath);
        } catch (IOException ioe) {
            throw new CommandException(String.format(MESSAGE_FAILURE, ioe));
        } catch (DataConversionException dce) {
            throw new CommandException(String.format(MESSAGE_FAILURE, dce));
        }

        int finalNumberOfTasks = taskList.size();
        int tasksImported = calculateImportedEntries(initialNumberOfPersons, finalNumberOfTasks);

        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, tasksImported));
    }

    /**
     * Returns the number of entries imported to the address book.
     * @throws CommandException If an error occurs during command execution.
     */
    private static int calculateImportedEntries(int initialListSize, int finalListSize) throws CommandException {
        requireNonNull(initialListSize);
        requireNonNull(finalListSize);

        int importedEntries = finalListSize - initialListSize;
        if (!isValidSize(importedEntries)) {
            throw new CommandException(String.format(MESSAGE_FAILURE, MESSAGE_INVALID_LIST_SIZE));
        }
        return importedEntries;
    }

    private static boolean isValidSize(int size) {
        return size >= 0;
    }

    @Override
    public boolean equals(Object other) {
        // same object
        if (other == this) {
            return true;
        }

        // handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        // checks state
        ImportCommand e = (ImportCommand) other;
        return filePath.equals(e.filePath);
    }
}
