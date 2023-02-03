package com.JavaProject.Final.Pojo.DTO;

import com.JavaProject.Final.Pojo.TableStatus;

public class TablesDTO {

    private TableStatus tableStatus;

    public TablesDTO(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }


    public TablesDTO() {
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
}

