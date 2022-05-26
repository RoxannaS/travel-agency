package com.adobe.travelagency.repository;

import com.adobe.travelagency.entity.Logging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends JpaRepository<Logging, Long> {
}
