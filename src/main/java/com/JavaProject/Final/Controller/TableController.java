package com.JavaProject.Final.Controller;

import com.JavaProject.Final.Exception.TableNotFoundException;
import com.JavaProject.Final.Pojo.*;
import com.JavaProject.Final.Pojo.DTO.TablesDTO;
import com.JavaProject.Final.Repository.OrderRepository;
import com.JavaProject.Final.Repository.TableRepository;
import com.JavaProject.Final.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    @Autowired
    TableService tableService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TableRepository tableRepository;

    @PostMapping("/create")
    public Tables saveTables(@RequestBody TablesDTO tablesDTO) {
        return tableService.saveTableFromDTO(tablesDTO);
    }


    @GetMapping("/{id}")
    public Tables getTalbeById(@PathVariable Integer id) throws TableNotFoundException {
        return tableService.getTableById(id);
    }

    @GetMapping("/all")
    public List<Tables> getAllTables() {
        return tableService.getAllTables();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable Integer id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.ok().body("Waiter deleted successfully!");
        } catch (TableNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tableID}/orders")
    public ResponseEntity<List<Order>> getOrdersByTableId(@PathVariable("tableID") Integer tableID) {
        List<Order> orders = orderRepository.findAllByTable_TableID(tableID);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/checkout/{tableId}")
    public ResponseEntity checkOut(@PathVariable("tableId") Integer tableId) throws TableNotFoundException {
        tableService.checkOut(tableId);
        return ResponseEntity.ok().build();
    }

}
