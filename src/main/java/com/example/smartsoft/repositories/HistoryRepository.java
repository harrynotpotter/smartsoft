package com.example.smartsoft.repositories;

import com.example.smartsoft.models.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface HistoryRepository extends JpaRepository<History, Integer>, JpaSpecificationExecutor<History> {
}
