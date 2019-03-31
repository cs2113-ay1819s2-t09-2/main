package seedu.address.ui;

import java.time.YearMonth;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import seedu.address.model.task.Task;
import seedu.address.ui.calendar.Calendar;


/**
 * Default page contains calendar, reminder and timeline.
 */
public class DefaultPage extends UiPart<Region> {

    private static final String FXML = "DefaultPage.fxml";

    @FXML
    private SplitPane overallPane;

    @FXML
    private AnchorPane calendarAnchorPane;

    @FXML
    private AnchorPane reminderAnchorPane;

    @FXML
    private AnchorPane timelineAnchorPane;

    @FXML
    private AnchorPane upperPartAnchorPane;

    public DefaultPage(ObservableList<Task> taskList) {
        super(FXML);
        init();

        //Show the calendar
        calendarAnchorPane.getChildren().add(new Calendar(YearMonth.now()).getView());
    }

    /**
     * Set each window to proper fixed size.
     */
    private void init() {
        upperPartAnchorPane.maxHeightProperty().bind(overallPane.heightProperty().multiply(0.6));
        timelineAnchorPane.maxHeightProperty().bind(overallPane.heightProperty().multiply(0.4));
        calendarAnchorPane.maxWidthProperty().bind(upperPartAnchorPane.widthProperty().multiply(0.5));
        reminderAnchorPane.maxWidthProperty().bind(upperPartAnchorPane.widthProperty().multiply(0.5));
    }

    /**
     * Set the desired month.
     */
    public void setMonth(String month) {
        Calendar c = new Calendar(YearMonth.now());
        //System.out.println(month);
        if (month.equals("Viewing next month's calendar!")) {
            c.nextMonth();
            //System.out.println(month);
        }
        if (month.equals("Viewing previous month's calendar!")) {
            c.previousMonth();
            //System.out.println(month);
        }
        calendarAnchorPane.getChildren().clear();
        calendarAnchorPane.getChildren().add(c.getView());
    }
}