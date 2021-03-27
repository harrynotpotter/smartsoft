package com.example.smartsoft.repositories;

import com.example.smartsoft.models.entity.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface ValuteRepository extends JpaRepository<Valute, String>, JpaSpecificationExecutor<Valute> {
}
