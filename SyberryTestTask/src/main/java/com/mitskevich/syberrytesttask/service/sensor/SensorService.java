package com.mitskevich.syberrytesttask.service.sensor;

import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.SensorUpdateDto;
import com.mitskevich.syberrytesttask.domain.sensor.dto.filter.SearchFilterDto;
import com.mitskevich.syberrytesttask.domain.sensor.entity.Sensor;
import com.mitskevich.syberrytesttask.mapping.sensor.SensorMapper;
import com.mitskevich.syberrytesttask.repository.sensor.SensorRepository;
import com.mitskevich.syberrytesttask.repository.sensor.SensorSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorService {

    private final SensorMapper sensorMapper;
    private final SensorRepository sensorRepository;

    @Transactional
    public SensorDto create(SensorCreateDto createDto) {
        Sensor sensor = sensorMapper.toSensor(createDto);
        return sensorMapper.toSensorDto(sensorRepository.save(sensor));
    }

    public SensorDto get(UUID id) {
        return sensorRepository.findById(id)
            .map(sensorMapper::toSensorDto)
            .orElseThrow(() -> new EntityNotFoundException("Sensor not found"));
    }

    public List<SensorDto> getAll(SearchFilterDto filter, Pageable pageable) {
        Specification<Sensor> specification = SensorSpecification.build(filter);

        return sensorRepository.findAll(specification, pageable)
            .map(sensorMapper::toSensorDto)
            .toList();
    }

    @Transactional
    public void update(UUID id, SensorUpdateDto updateDto) {
        sensorRepository.findById(id)
            .ifPresentOrElse(
                it -> sensorMapper.updateSensor(it, updateDto),
                () -> { throw new EntityNotFoundException("Sensor not found"); }
            );
    }

    @Transactional
    public void delete(UUID id) {
        sensorRepository.findById(id)
            .ifPresentOrElse(
                sensorRepository::delete,
                () -> { throw new EntityNotFoundException("Sensor not found"); }
            );
    }
}
