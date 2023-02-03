package com.JavaProject.Final.Controller;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Exception.WaiterNotFoundException;
import com.JavaProject.Final.Pojo.DTO.WaiterDTO;
import com.JavaProject.Final.Pojo.Waiter;
import com.JavaProject.Final.Service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiter")
public class WaiterController {

    @Autowired
    WaiterService waiterService;


    @PostMapping("/create")
    public Waiter saveWaiter(@RequestBody WaiterDTO waiterDTO) throws ConflictException {
        return waiterService.saveWaiterFromDTO(waiterDTO);
    }


    @GetMapping("/{id}")
    public Waiter getWaiterById(@PathVariable Integer id) throws WaiterNotFoundException {
        return waiterService.getWaiterById(id);
    }

    @GetMapping("/all")
    public List<Waiter> getAllWaiters() {
        return waiterService.getAllWaiters();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWaiter(@PathVariable Integer id) {
        try {
            waiterService.deleteWaiter(id);
            return ResponseEntity.ok().body("Waiter deleted successfully!");
        } catch (WaiterNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
