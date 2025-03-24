package com.mitskevich.sensorsstatisticservice.controller;

import com.mitskevich.sensorsstatisticservice.api.SensorStatisticApi;
import com.mitskevich.sensorsstatisticservice.dto.SensorStatisticDto;
import com.mitskevich.sensorsstatisticservice.dto.filter.StatisticFilter;
import com.mitskevich.sensorsstatisticservice.service.SensorStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors/statistic")
public class SensorStatisticController implements SensorStatisticApi {

    private final SensorStatisticService sensorStatisticService;

    @Override
    @GetMapping
    public List<SensorStatisticDto> getSensorStatistic(Pageable pageable, StatisticFilter statisticFilter) {
        return sensorStatisticService.getSensorStatistic(pageable, statisticFilter);
    }
}
