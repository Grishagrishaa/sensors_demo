package com.mitskevich.sensorstesttask.mapping.sensor_unit;

import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitDto;
import com.mitskevich.sensorstesttask.domain.sensor_unit.entity.SensorUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SensorUnitMapper {

    @Mapping(target = "id", ignore = true)
    SensorUnit toSensorUnit(SensorUnitCreateDto createDto);

    SensorUnitDto toSensorUnitDto(SensorUnit sensorUnit);

}
