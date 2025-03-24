package com.mitskevich.syberrytesttask.controller.sensor;

import com.mitskevich.syberrytesttask.api.sensor.SensorApi;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorUpdateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.filter.SearchFilterDto;
import com.mitskevich.syberrytesttask.service.sensor.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController implements SensorApi {

    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<SensorDto> create(@Valid @RequestBody SensorCreateDto createDto) {
        return ResponseEntity.status(CREATED)
            .body(sensorService.create(createDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDto> get(@PathVariable UUID id) {
        return ResponseEntity.status(OK)
            .body(sensorService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<SensorDto>> getAll(SearchFilterDto filter, Pageable pageable) {
        return ResponseEntity.status(OK)
            .body(sensorService.getAll(filter, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody SensorUpdateDto updateDto) {
        sensorService.update(id, updateDto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        sensorService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
