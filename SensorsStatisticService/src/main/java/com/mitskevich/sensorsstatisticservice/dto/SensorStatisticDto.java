package com.mitskevich.sensorsstatisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(description = "DTO для ответа статистики по сенсорам")
public record SensorStatisticDto(@Schema(description = "Общее количество")
                                 Long total,

                                 @Schema(description = "Количество по каждому типу")
                                 Map<String, Long> typeCountMap) {
}
