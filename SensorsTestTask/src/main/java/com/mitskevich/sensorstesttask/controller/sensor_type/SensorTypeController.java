package com.mitskevich.sensorstesttask.controller.sensor_type;

import com.mitskevich.sensorstesttask.api.sensor_type.SensorTypeApi;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeDto;
import com.mitskevich.sensorstesttask.service.sensor_type.SensorTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sensors/types")
@RequiredArgsConstructor
public class SensorTypeController implements SensorTypeApi {

    private final SensorTypeService sensorService;

    @PostMapping
    public ResponseEntity<SensorTypeDto> create(@Valid @RequestBody SensorTypeCreateDto createDto) {
        return ResponseEntity.status(CREATED)
            .body(sensorService.create(createDto));
    }

    @GetMapping
    public ResponseEntity<List<SensorTypeDto>> getAll(Pageable pageable) {
        return ResponseEntity.status(OK)
            .body(sensorService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sensorService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
