package com.mitskevich.sensorsstatisticservice.repository;

import com.mitskevich.sensorsstatisticservice.entity.SensorsStatisticReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SensorStatisticRepository extends JpaRepository<SensorsStatisticReport, UUID>,
    JpaSpecificationExecutor<SensorsStatisticReport> {
}
