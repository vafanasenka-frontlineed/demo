package com.allot.domain.asm.frontend.page.table;

import com.codeborne.selenide.*;
import com.google.common.collect.Iterables;
import java.util.*;
import java.util.stream.Collectors;

import static com.allot.domain.asm.frontend.page.table.Table.TablePageElement.*;


public class Table {

    private SelenideElement tableContainer;
    private List<Row> rowList;
    private Header header;

    private static final String ACTIONS_COLUMN = "ACTIONS";

    public Table(SelenideElement tableContainer) {
        this.tableContainer = tableContainer;
        this.header = new Header();
        this.rowList = tableContainer.$$(ROWS.getLocator())
                .stream()
                .map(Row::new)
                .collect(Collectors.toList());
    }

    public List<String> getHeaders() {
        return header.getHeaders();
    }

    public List<Row> getRows() {
        return rowList;
    }

    /**
     * Returns a table row by specified column name and value
     * @param columnName String column (header) name
     * @param value      String value for the specified column (header) name
     */
    public Row getRow(String columnName, String value) {
        int columnIndex = getColumnIndex(columnName);
        return getRows().stream()
                .filter(row -> row.getCellText(columnIndex).equals(value))
                .findFirst()
                .orElseThrow(() -> new TableElementNotFoundException(TableElement.ROW, columnName, value));
    }

    /**
     * Returns text of the cell specified by columnName for exact row
     * @param row        Row instance
     * @param columnName String column (header) name
     */
    public String getCellText(Row row, String columnName) {
        int columnIndex = getColumnIndex(columnName);
        return row.getCellText(columnIndex);
    }

    /**
     * Returns column index by specified column name
     * @param columnName String header name
     */
    public int getColumnIndex(String columnName) {
        int index = Iterables.indexOf(header.getHeaders(), h -> {
            assert h != null;
            return h.equalsIgnoreCase(columnName);
        });
        if (index < 0) {
            throw new TableElementNotFoundException(TableElement.COLUMN, "name", columnName);
        }
        return index;
    }

    private class Header {

        public ElementsCollection getHeaderElements() {
            return tableContainer.$(HEADER.getLocator()).$$("th[colspan]");
        }

        public List<String> getHeaders() {
            return getHeaderElements().stream().map(SelenideElement::innerText).collect(Collectors.toList());
        }

    }

    public class Row {

        private List<Cell> cellList;

        public Row(SelenideElement rowElement) {
            this.cellList = rowElement.$$("td[colspan]").stream().map(Cell::new).collect(Collectors.toList());
        }

        public String getCellText(int index) {
            var cellOptional = getCell(index)
                    .orElseThrow(() -> new TableElementNotFoundException(TableElement.CELL, "index",
                            String.valueOf(index)));
            return cellOptional.getCellText();
        }

        public Optional<Cell> getCell(int index) {
            return cellList.size() >= index + 1 ? Optional.of(cellList.get(index)) : Optional.empty();
        }

        public Cell getActionsCell() {
            int indexActionsColumn = getColumnIndex(ACTIONS_COLUMN);
            return getCell(indexActionsColumn)
                    .orElseThrow(() -> new TableElementNotFoundException(TableElement.COLUMN, "name", ACTIONS_COLUMN));
        }

    }

    public class Cell {

        private SelenideElement cellElement;

        public Cell(SelenideElement selenideElement) {
            this.cellElement = selenideElement;
        }

        public SelenideElement getEditButton() {
            return cellElement.$("button.btn-warning");
        }

        public SelenideElement getDeleteButton() {
            return cellElement.$("button .ui-1_simple-remove");
        }

        String getCellText() {
            return cellElement.getText();
        }

    }

    public enum TablePageElement {

        HEADER(" .el-table__header-wrapper "),
        BODY(" .el-table__body-wrapper "),
        ROWS(BODY.getLocator() + " .el-table__row ");

        private String locator;

        TablePageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}