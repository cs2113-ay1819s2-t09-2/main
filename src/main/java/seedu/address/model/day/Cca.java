package seedu.address.model.day;

import java.util.Objects;

/**
 * Represents a Day's cca in the task book.
 */
public class Cca extends Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Cca time should only contain numbers & '.', ie: 3.0";
    public static final String VALIDATION_REGEX = "\\d" + "." + "\\d";
    public String time;

    /**
     * Constructs a {@code Academic}.
     *
     * @param time A valid time.
     */
    public Cca(String time) {
        this.time = time;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String addTime(double result) {
        double t = Double.parseDouble(time);
        t += result;
        time = String.valueOf(t);
        return time;
    }

    public String removeTime(double result) {
        double t = Double.parseDouble(time);
        t -= result;
        time = String.valueOf(t);
        return time;
    }

    public String getTime() {
        return time;
    }

    public double getTimeDouble() {
        double t;
        double scale = Math.pow(10, 2);
        t = Double.parseDouble(time);

        return Math.round(t * scale) / scale;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cca // instanceof handles nulls
                && time == ((Cca) other).time); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

}
