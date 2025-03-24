package com.mitskevich.sensorstesttask.mapping.sensor;

import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorUpdateDto;
import com.mitskevich.sensorstesttask.domain.sensor.dto.embedded.RangeDto;
import com.mitskevich.sensorstesttask.domain.sensor.entity.Sensor;
import com.mitskevich.sensorstesttask.domain.sensor.entity.embedded.Range;
import com.mitskevich.sensorstesttask.domain.sensor_type.entity.SensorType;
import com.mitskevich.sensorstesttask.domain.sensor_unit.entity.SensorUnit;
import com.mitskevich.sensorstesttask.repository.sensor_type.SensorTypeRepository;
import com.mitskevich.sensorstesttask.repository.sensor_unit.SensorUnitRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper
public abstract class SensorMapper {

    @Autowired
    protected SensorUnitRepository sensorUnitRepository;

    @Autowired
    protected SensorTypeRepository sensorTypeRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unit", source = "unitId", qualifiedByName = "mapSensorUnit")
    @Mapping(target = "type", source = "typeId", qualifiedByName = "mapSensorType")
    public abstract Sensor toSensor(SensorCreateDto createDto);

    @Mapping(target = "unit", source = "sensor.unit.name")
    @Mapping(target = "type", source = "sensor.type.name")
    public abstract SensorDto toSensorDto(Sensor sensor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unit", source = "unitId", qualifiedByName = "mapSensorUnit")
    @Mapping(target = "type", source = "typeId", qualifiedByName = "mapSensorType")
    public abstract void updateSensor(@MappingTarget Sensor sensor, SensorUpdateDto updateDto);

    @Named("mapSensorUnit")
    protected SensorUnit mapSensorUnit(Long unitId) {
        return Optional.ofNullable(unitId)
            .flatMap(sensorUnitRepository::findById)
            .orElseThrow(() -> new IllegalArgumentException("Invalid sensor unit id"));
    }

    @Named("mapSensorType")
    protected SensorType mapSensorType(Long typeId) {
        return Optional.ofNullable(typeId)
            .flatMap(sensorTypeRepository::findById)
            .orElseThrow(() -> new IllegalArgumentException("Invalid sensor type id"));
    }

    public abstract Range map(RangeDto value);

}
