package com.mitskevich.sensorstesttask.api.sensor_type;

import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Tag(name = "SensorTypeApi", description = "API управления спровочником типов сенсоров")
public interface SensorTypeApi {

    @Operation(summary = "Создание записи справочника типа сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Создано успещно"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    ResponseEntity<SensorTypeDto> create(SensorTypeCreateDto createDto);

    @Operation(summary = "Получение всех записей справочника типа сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<List<SensorTypeDto>> getAll(@PageableDefault Pageable pageable);


    @Operation(summary = "Удаление записи справочника типа сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Успешно удален"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "404", description = "Ресурс не был найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<Void> delete(Long id);
}
