package com.mitskevich.sensorsstatisticservice.api;

import com.mitskevich.sensorsstatisticservice.dto.SensorStatisticDto;
import com.mitskevich.sensorsstatisticservice.dto.filter.StatisticFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Tag(name = "StatisticApi", description = "API для получения статистики датчиков")
public interface SensorStatisticApi {

    @Operation(summary = "Получение статистики датчиков")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    List<SensorStatisticDto> getSensorStatistic(@PageableDefault @ParameterObject Pageable pageable, @ParameterObject StatisticFilter statisticFilter);
}
