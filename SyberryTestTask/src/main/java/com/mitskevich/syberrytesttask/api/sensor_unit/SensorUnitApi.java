package com.mitskevich.syberrytesttask.api.sensor_unit;

import com.mitskevich.syberrytesttask.domain.sensor_unit.dto.SensorUnitCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor_unit.dto.SensorUnitDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Tag(name = "SensorUnitApi", description = "API управления спровочником типов измерения сенсоров")
public interface SensorUnitApi {

    @Operation(summary = "Создание записи справочника типа измерения сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Создано успещно"),
        @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    ResponseEntity<SensorUnitDto> create(SensorUnitCreateDto createDto);

    @Operation(summary = "Получение всех записей справочника типа измерения сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успех"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<List<SensorUnitDto>> getAll(@PageableDefault Pageable pageable);


    @Operation(summary = "Удаление записи справочника типа измерения сенсора")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Успешно удален"),
        @ApiResponse(responseCode = "401", description = "Отказ в доступе"),
        @ApiResponse(responseCode = "403", description = "Запрещен доступ"),
        @ApiResponse(responseCode = "404", description = "Ресурс не был найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    ResponseEntity<Void> delete(Long id);
}
