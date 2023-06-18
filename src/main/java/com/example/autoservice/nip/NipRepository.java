package com.example.autoservice.nip;

import com.example.autoservice.nip.domain.Nip;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NipRepository extends CrudRepository<Nip, Long> {
    @Query("SELECT n FROM Nip n")
    List<Nip> findAllNip();
    @Modifying
    @Transactional
    @Query(value = "UPDATE Nip n SET n.nip=:nip where n.id = :id")
    int editNip(String nip, Long id);

}
