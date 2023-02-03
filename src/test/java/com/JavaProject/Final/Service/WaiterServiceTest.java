package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Pojo.DTO.WaiterDTO;
import com.JavaProject.Final.Pojo.Waiter;
import com.JavaProject.Final.Repository.WaiterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WaiterServiceTest {

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private WaiterRepository waiterRepository;

    @Test
    public void testSaveWaiterFromDTO() throws ConflictException{
        WaiterDTO waiterDTO = new WaiterDTO("Waiter1");
        Waiter savedWaiter = waiterService.saveWaiterFromDTO(waiterDTO);

        assertNotNull(savedWaiter);
        assertEquals(waiterDTO.getName(), savedWaiter.getName());
        assertTrue(waiterRepository.existsWaiterByName(savedWaiter.getName()));


    }

    @Test
    public void testWaiterExists() throws ConflictException {
        WaiterDTO waiterDTO = new WaiterDTO("Waiter2");
        Waiter savedWaiter = waiterService.saveWaiterFromDTO(waiterDTO);

        waiterRepository.save(savedWaiter);

        boolean result = waiterService.waiterExists("Waiter2");
        assertThat(result).isTrue();
    }



}