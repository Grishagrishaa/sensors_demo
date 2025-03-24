package com.mitskevich.syberrytesttask.repository.sensor_type;

import com.mitskevich.syberrytesttask.domain.sensor_type.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {
}
