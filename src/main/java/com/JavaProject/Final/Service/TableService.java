package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.TableNotFoundException;
import com.JavaProject.Final.Pojo.DTO.TablesDTO;
import com.JavaProject.Final.Pojo.Order;
import com.JavaProject.Final.Pojo.TableStatus;
import com.JavaProject.Final.Pojo.Tables;
import com.JavaProject.Final.Repository.OrderRepository;
import com.JavaProject.Final.Repository.TableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;
    @Autowired
    OrderRepository orderRepository;


    public Tables saveTableFromDTO(TablesDTO tablesDTO) {
        Tables tableEntity = new Tables();
        tableEntity.setTableStatus(TableStatus.valueOf(tablesDTO.getTableStatus().toString().toUpperCase()));
        tableRepository.save(tableEntity);
        return tableEntity;
    }


    public List<Tables> getAllTables() {
        List<Tables> tables = tableRepository.findAll();
        return tables;
    }

    public Tables getTableById(Integer id) throws TableNotFoundException {
        if(tableExists(id)) {
            return tableRepository.findById(id).get();
        }
        else throw new TableNotFoundException("A table with this id: " + id + " doesn't exist");
    }

    public List<Order> getOrdersByTableId(Integer tableID) {
        return orderRepository.findAllByTable_TableID(tableID);
    }

    @Transactional
    public Map<String, Double> checkOut(Integer tableID) throws TableNotFoundException {
        Map<String, Double> response = new HashMap<>();
        if(tableExists(tableID)) {
            double tablePrice = 0;
            List<Order> orders = orderRepository.findAllByTable_TableID(tableID);
            for(Order order : orders) {
                order.setTable(null);
                orderRepository.save(order);

                tablePrice += order.getOrderPrice();


            }
            response.put("tablePrice", tablePrice);
            tableRepository.updateTableStatus(tableID, TableStatus.AVAIABLE);



        }
        else throw new TableNotFoundException("A table with this id: " + tableID + "doesn't exist");

        return response;
    }




    public void deleteTable(Integer id) throws TableNotFoundException {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
        } else throw new TableNotFoundException("A table with this id: " + id + " doesn't exist");
    }

    public boolean tableExists(Integer id) {
        if(tableRepository.existsById(id)) {
            return true;
        }
        else return false;
    }


}
