package com.mitskevich.sensorsstatisticservice.service;

import com.mitskevich.sensorsstatisticservice.entity.SensorsStatisticReport;
import com.mitskevich.sensorsstatisticservice.external.dto.SensorDto;
import com.mitskevich.sensorsstatisticservice.external.gateway.SensorsClient;
import com.mitskevich.sensorsstatisticservice.mapping.SensorStatisticMapper;
import com.mitskevich.sensorsstatisticservice.repository.SensorStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SensorStatisticJob {

    private final SensorStatisticRepository statisticRepository;
    private final SensorStatisticMapper sensorStatisticMapper;
    private final SensorsClient sensorsClient;

    @Async("jobExecutor")
    @Scheduled(cron = "0 0 2 * * ?", zone = "Europe/Moscow")
    public void getSensorStatistic() {
        List<SensorDto> allSensors = sensorsClient.getAllSensors();
        SensorsStatisticReport statisticReport = sensorStatisticMapper.toSensorsStatisticReport(allSensors);

        statisticRepository.save(statisticReport);
    }
    
}
