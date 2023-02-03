package com.JavaProject.Final.Repository;

import com.JavaProject.Final.Pojo.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter,Integer> {

    boolean existsWaiterByName(String name);
}
