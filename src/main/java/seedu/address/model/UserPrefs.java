package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path taskBookFilePath = Paths.get("data" , "taskbook.json");
    private Path daysKeeperFilePath = Paths.get("data" , "dayskeeper.json");
    private Path exportCsvFilePath = Paths.get("data", "tasketch.json");
    private Path accountListFilePath = Paths.get("data" , "AccountList.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setTaskBookFilePath(newUserPrefs.getTaskBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }


    public Path getAccountListFilePath() {
        return accountListFilePath;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTaskBookFilePath() {
        return taskBookFilePath;
    }

    public void setTaskBookFilePath(Path taskBookFilePath) {
        requireNonNull(taskBookFilePath);
        this.taskBookFilePath = taskBookFilePath;
    }


    public Path getExportCsvFilePath() {
        return exportCsvFilePath;
    }

    public void setDaysKeeperFilePath(Path daysKeeperFilePath) {
        requireNonNull(daysKeeperFilePath);
        this.daysKeeperFilePath = daysKeeperFilePath;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && taskBookFilePath.equals(o.taskBookFilePath)
                && daysKeeperFilePath.equals(o.daysKeeperFilePath)
                && exportCsvFilePath.equals(o.exportCsvFilePath)
                && taskBookFilePath.equals(o.taskBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, taskBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal taskBook data file location : " + taskBookFilePath);
        return sb.toString();
    }

}
