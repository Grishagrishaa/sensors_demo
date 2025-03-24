package com.mitskevich.sensorstesttask.repository.sensor_type;

import com.mitskevich.sensorstesttask.domain.sensor_type.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {
}
