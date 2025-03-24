package com.mitskevich.syberrytesttask.domain.sensor_unit.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SensorUnitCreateDto(@Schema(description = "Название типа сенсора")
                                  String name) {
}
