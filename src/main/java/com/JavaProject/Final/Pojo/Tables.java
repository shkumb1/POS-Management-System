package com.JavaProject.Final.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableID;


    @Enumerated(EnumType.STRING)
    @Column(name = "table_status")
    private TableStatus tableStatus;





    public Tables(Integer tableID, TableStatus tableStatus) {
        this.tableID = tableID;
        this.tableStatus = tableStatus;
    }

    public Tables() {
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }

}

