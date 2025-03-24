package com.mitskevich.sensorsstatisticservice.external.dto;

import java.util.UUID;

public record SensorDto(UUID id,
                        String name,
                        String model,
                        String location,
                        String description,
                        RangeDto range,
                        String type,
                        String unit) {

    public record RangeDto(Integer from,
                           Integer to) {}

}
