package com.mitskevich.sensorsstatisticservice.service;

import com.mitskevich.sensorsstatisticservice.dto.SensorStatisticDto;
import com.mitskevich.sensorsstatisticservice.dto.filter.StatisticFilter;
import com.mitskevich.sensorsstatisticservice.entity.SensorsStatisticReport;
import com.mitskevich.sensorsstatisticservice.mapping.SensorStatisticMapper;
import com.mitskevich.sensorsstatisticservice.repository.SensorStatisticRepository;
import com.mitskevich.sensorsstatisticservice.repository.specifiication.SensorStatisticSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorStatisticService {

    private final SensorStatisticRepository statisticRepository;
    private final SensorStatisticMapper sensorStatisticMapper;
    
    public List<SensorStatisticDto> getSensorStatistic(Pageable pageable, StatisticFilter filter) {
        Specification<SensorsStatisticReport> spec = SensorStatisticSpecification.build(filter);

        return statisticRepository.findAll(spec, pageable)
            .map(sensorStatisticMapper::toSensorStatisticDto)
            .toList();
    }
    
}
