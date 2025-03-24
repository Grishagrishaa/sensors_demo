package com.mitskevich.syberrytesttask.mapping.sensor_type;

import com.mitskevich.syberrytesttask.domain.sensor_type.dto.SensorTypeCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor_type.dto.SensorTypeDto;
import com.mitskevich.syberrytesttask.domain.sensor_type.entity.SensorType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SensorTypeMapper {

    @Mapping(target = "id", ignore = true)
    SensorType toSensorType(SensorTypeCreateDto createDto);

    SensorTypeDto toSensorTypeDto(SensorType sensorType);

}
