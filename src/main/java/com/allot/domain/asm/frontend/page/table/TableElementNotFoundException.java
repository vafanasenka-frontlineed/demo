package com.allot.domain.asm.frontend.page.table;

public class TableElementNotFoundException extends RuntimeException {

    public TableElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public TableElementNotFoundException(TableElement tableElement, String name, String value) {
        super(String.format("%s with the %s = '%s' was not found in the table", tableElement.toString(), name, value));
    }

}
