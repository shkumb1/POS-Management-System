package com.JavaProject.Final.Repository;

import com.JavaProject.Final.Pojo.TableStatus;
import com.JavaProject.Final.Pojo.Tables;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TableRepository extends JpaRepository<Tables,Integer> {

    @Transactional
    @Modifying
    @Query("update Tables set tableStatus = :status where tableID = :id")
    void updateTableStatus(@Param("id") Integer id, @Param("status") TableStatus status);

}
