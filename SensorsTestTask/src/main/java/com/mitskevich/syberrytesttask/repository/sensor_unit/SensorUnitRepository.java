package com.mitskevich.syberrytesttask.repository.sensor_unit;

import com.mitskevich.syberrytesttask.domain.sensor_unit.entity.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorUnitRepository extends JpaRepository<SensorUnit, Long> {
}
