package com.biolab.doce_laar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biolab.doce_laar.entity.Doce;

@Repository
public interface DoceRepository extends JpaRepository<Doce, Long> {
}
