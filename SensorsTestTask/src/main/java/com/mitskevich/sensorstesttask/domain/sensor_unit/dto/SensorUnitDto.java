package com.mitskevich.sensorstesttask.domain.sensor_unit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public record SensorUnitDto(@Schema(description = "Id типа сенсора")
                            Long id,

                            @Schema(description = "Название единицы измерения сенсора")
                            String name) {
}
