package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Exception.WaiterNotFoundException;
import com.JavaProject.Final.Pojo.DTO.WaiterDTO;
import com.JavaProject.Final.Pojo.Waiter;
import com.JavaProject.Final.Repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterService {

    @Autowired
    WaiterRepository waiterRepository;


    public boolean waiterExists(Integer id) {
        if(waiterRepository.existsById(id)) {
            return true;
        }
        else return false;
    }

    public boolean waiterExists(String name) {
        return waiterRepository.existsWaiterByName(name);
    }


    public List<Waiter> getAllWaiters() {
        List<Waiter> waiters = waiterRepository.findAll();
        return waiters;
    }

    public Waiter getWaiterById(Integer id) throws WaiterNotFoundException {
        if(waiterExists(id)) {
            return waiterRepository.findById(id).get();
        }
        else throw new WaiterNotFoundException("A waiter with this id: " + id + "doesn't exist");
    }


    public Waiter saveWaiterFromDTO(WaiterDTO waiterDTO) throws ConflictException {
        Waiter waiterEntity = new Waiter();
        waiterEntity.setName(waiterDTO.getName());
        saveWaiter(waiterEntity);
        return waiterEntity;
    }



    public void saveWaiter(Waiter waiter) throws ConflictException {
        if (waiterExists(waiter.getName())) {
            throw new ConflictException("A Waiter with this name: " + waiter.getName() + " already exists! ");
        } else {
            waiterRepository.save(waiter);
        }
    }

    public void deleteWaiter(Integer id) throws WaiterNotFoundException {
        if(waiterRepository.existsById(id)) {
            waiterRepository.deleteById(id);
        }
        else throw new WaiterNotFoundException("A waiter with this id: " + id + "doesn't exist");
    }
}

