package com.mitskevich.sensorstesttask.repository.sensor_unit;

import com.mitskevich.sensorstesttask.domain.sensor_unit.entity.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorUnitRepository extends JpaRepository<SensorUnit, Long> {
}
