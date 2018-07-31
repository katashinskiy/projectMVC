package com.projectMVC.repository;

import com.projectMVC.entity.Massege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaseggeRepository extends JpaRepository<Massege,Integer> {

    List<Massege> findByTag(String teg);
    Massege findById(Long id);

}
