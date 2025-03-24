package com.mitskevich.syberrytesttask.api.sensor;

import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorUpdateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.filter.SearchFilterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "SensorApi", description = "API управлние сенсорами")
public interface SensorApi {

    @Operation(summary = "Создание датчика")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Создано успещно"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    ResponseEntity<SensorDto> create(SensorCreateDto createDto);

    @Operation(summary = "Получение датчика")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "404", description = "Ресурс не был найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<SensorDto> get(UUID id);

    @Operation(summary = "Получение всех датчиков")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<List<SensorDto>> getAll(SearchFilterDto filterDto, @PageableDefault Pageable pageable);

    @Operation(summary = "Обновление датчика")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<Void> update(UUID id, SensorUpdateDto updateDto);

    @Operation(summary = "Удаление датчика")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Успешно удален"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "404", description = "Ресурс не был найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<Void> delete(UUID id);
}
