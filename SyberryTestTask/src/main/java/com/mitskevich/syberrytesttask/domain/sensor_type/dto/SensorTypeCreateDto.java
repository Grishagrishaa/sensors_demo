package com.mitskevich.syberrytesttask.domain.sensor_type.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SensorTypeCreateDto(@Schema(description = "Название типа сенсора") String name) {
}
