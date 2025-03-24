package com.mitskevich.sensorstesttask.controller.sensor_unit;

import com.mitskevich.sensorstesttask.api.sensor_unit.SensorUnitApi;
import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitDto;
import com.mitskevich.sensorstesttask.service.sensor_unit.SensorUnitService;
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
@RequestMapping("/sensors/units")
@RequiredArgsConstructor
public class SensorUnitController implements SensorUnitApi {

    private final SensorUnitService sensorService;

    @PostMapping
    public ResponseEntity<SensorUnitDto> create(@Valid @RequestBody SensorUnitCreateDto createDto) {
        return ResponseEntity.status(CREATED)
            .body(sensorService.create(createDto));
    }

    @GetMapping
    public ResponseEntity<List<SensorUnitDto>> getAll(Pageable pageable) {
        return ResponseEntity.status(OK)
            .body(sensorService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sensorService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
