package com.mitskevich.sensorstesttask.service.sensor_type;

import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.entity.SensorType;
import com.mitskevich.sensorstesttask.mapping.sensor_type.SensorTypeMapper;
import com.mitskevich.sensorstesttask.repository.sensor_type.SensorTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorTypeService {

    private final SensorTypeMapper sensorTypeMapper;
    private final SensorTypeRepository sensorTypeRepository;

    @Transactional
    public SensorTypeDto create(SensorTypeCreateDto createDto) {
        SensorType sensorType = sensorTypeMapper.toSensorType(createDto);
        return sensorTypeMapper.toSensorTypeDto(sensorTypeRepository.save(sensorType));
    }


    public List<SensorTypeDto> getAll(Pageable pageable) {
        return sensorTypeRepository.findAll(pageable)
            .map(sensorTypeMapper::toSensorTypeDto)
            .toList();
    }

    @Transactional
    public void delete(Long id) {
        sensorTypeRepository.findById(id)
            .ifPresentOrElse(
                sensorTypeRepository::delete,
                () -> { throw new EntityNotFoundException("Sensor type not found"); }
            );
    }
}
