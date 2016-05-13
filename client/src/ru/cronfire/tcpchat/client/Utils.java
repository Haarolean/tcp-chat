package ru.cronfire.tcpchat.client;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.client
 * Date: 13.05.2016
 * Time: 18:47
 * DO NOT DISTRIBUTE.
 */

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
    /**
     * Show modal window with exception message and stacktrace.
     *
     * @param ex the specified exception
     */
    public static void showExceptionDialog(final Throwable ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Main.getStage());
        //alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Диалог исключения");
        alert.setHeaderText("Возникло исключение!");
        alert.setContentText("Сообщение исключения: " + ex.getClass().getSimpleName() + ": " +  ex.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));

        TextArea textArea = new TextArea(sw.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(new Label("Stacktrace исключения:"), 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    private static void showAlertWindow(final Alert.AlertType alertType, final String title, final String message) {
        Alert alert = new Alert(alertType, message, new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.initOwner(Main.getStage());
        //alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static void showAlertWindow(final String title, final String message) {
        showAlertWindow(Alert.AlertType.NONE, title, message);
    }
}
