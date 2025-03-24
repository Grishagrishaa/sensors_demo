package com.mitskevich.sensorsstatisticservice.dto.filter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO для фильтра статистики")
public record StatisticFilter(@Schema(description = "Время от") LocalDateTime from,
                              @Schema(description = "Время до") LocalDateTime to) {
}
