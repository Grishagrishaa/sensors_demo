package com.mitskevich.sensorsstatisticservice.external.gateway;

import com.mitskevich.sensorsstatisticservice.config.FeignClientConfig;
import com.mitskevich.sensorsstatisticservice.external.dto.SensorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
    name = "sensor-statistics-client",
    url = "${app.sensors.url}",
    configuration = FeignClientConfig.class
)
public interface SensorsClient {

    @GetMapping("/sensors")
    List<SensorDto> getAllSensors();

}