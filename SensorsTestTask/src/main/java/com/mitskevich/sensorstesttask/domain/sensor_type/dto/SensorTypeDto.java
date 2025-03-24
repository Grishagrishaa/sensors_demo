package com.mitskevich.sensorstesttask.domain.sensor_type.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public record SensorTypeDto(@Schema(description = "Id типа сенсора")
                            Long id,

                            @Schema(description = "Название типа сенсора")
                            String name) {
}
