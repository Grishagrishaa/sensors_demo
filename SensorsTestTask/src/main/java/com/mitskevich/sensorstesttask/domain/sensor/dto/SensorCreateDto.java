package com.mitskevich.sensorstesttask.domain.sensor.dto;

import com.mitskevich.sensorstesttask.domain.sensor.dto.embedded.RangeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SensorCreateDto(@NotBlank
                              @Size(min = 3, max = 30)
                              @Schema(description = "Название датчика")
                              String name,

                              @NotBlank
                              @Size(max = 15)
                              @Schema(description = "Модель")
                              String model,

                              @Size(max = 40)
                              @Schema(description = "Местоположение")
                              String location,

                              @Size(max = 200)
                              @Schema(description = "Описание")
                              String description,

                              @Schema(description = "Радиус работы")
                              RangeDto range,

                              @NotNull
                              @Schema(description = "Id Типа")
                              Long typeId,

                              @Schema(description = "Id Единицы измерения")
                              Long unitId) {
}
