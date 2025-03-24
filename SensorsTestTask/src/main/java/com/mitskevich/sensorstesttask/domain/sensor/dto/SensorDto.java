package com.mitskevich.sensorstesttask.domain.sensor.dto;

import com.mitskevich.sensorstesttask.domain.sensor.dto.embedded.RangeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@FieldNameConstants
public record SensorDto(@Schema(description = "Id сенсора")
                        UUID id,

                        @Schema(description = "Название датчика")
                        String name,

                        @Schema(description = "Модель")
                        String model,

                        @Schema(description = "Местоположение")
                        String location,

                        @Schema(description = "Описание")
                        String description,

                        @Schema(description = "Радиус работы")
                        RangeDto range,

                        @Schema(description = "Тип")
                        String type,

                        @Schema(description = "Единица измерения")
                        String unit) {
}
