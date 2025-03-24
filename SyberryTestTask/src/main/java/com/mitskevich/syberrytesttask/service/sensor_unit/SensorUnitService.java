package com.mitskevich.syberrytesttask.service.sensor_unit;

import com.mitskevich.syberrytesttask.domain.sensor_unit.dto.SensorUnitCreateDto;
import com.mitskevich.syberrytesttask.domain.sensor_unit.dto.SensorUnitDto;
import com.mitskevich.syberrytesttask.domain.sensor_unit.entity.SensorUnit;
import com.mitskevich.syberrytesttask.mapping.sensor_unit.SensorUnitMapper;
import com.mitskevich.syberrytesttask.repository.sensor_unit.SensorUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorUnitService {

    private final SensorUnitMapper sensorUnitMapper;
    private final SensorUnitRepository sensorUnitRepository;

    @Transactional
    public SensorUnitDto create(SensorUnitCreateDto createDto) {
        SensorUnit unit = sensorUnitMapper.toSensorUnit(createDto);
        return sensorUnitMapper.toSensorUnitDto(sensorUnitRepository.save(unit));
    }


    public List<SensorUnitDto> getAll(Pageable pageable) {
        return sensorUnitRepository.findAll(pageable)
            .map(sensorUnitMapper::toSensorUnitDto)
            .toList();
    }

    @Transactional
    public void delete(Long id) {
        sensorUnitRepository.findById(id)
            .ifPresentOrElse(
                sensorUnitRepository::delete,
                () -> { throw new EntityNotFoundException("Sensor type not found"); }
            );
    }
}
