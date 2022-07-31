package com.example.universityfx;

import javafx.beans.NamedArg;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class NumberTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    private final int startNumber;

    public NumberTableCellFactory(@NamedArg("startNumber") int startNumber) {
        this.startNumber = startNumber;
    }

    public NumberTableCellFactory() {
        this(1);
    }

    public static class NumberTableCell<S, T> extends TableCell<S, T> {

        private final int startNumber;

        public NumberTableCell(int startNumber) {
            this.startNumber = startNumber;
        }



        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            setText(empty ? "" : Integer.toString(startNumber + getIndex()));
        }

    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new NumberTableCell<>(startNumber);
    }

    public static <T> TableColumn<T, Void> createNumberColumn(String text, int startNumber) {
        TableColumn<T, Void> column = new TableColumn<>(text);
        column.setSortable(false);
        column.setEditable(false);
        column.setCellFactory(new NumberTableCellFactory<>(startNumber));
        return column;
    }

}