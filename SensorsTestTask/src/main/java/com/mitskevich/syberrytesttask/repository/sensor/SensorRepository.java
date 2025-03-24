package com.mitskevich.syberrytesttask.repository.sensor;

import com.mitskevich.syberrytesttask.domain.sensor.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID>, JpaSpecificationExecutor<Sensor> {
}
