package com.mitskevich.sensorsstatisticservice.mapping;

import com.mitskevich.sensorsstatisticservice.dto.SensorStatisticDto;
import com.mitskevich.sensorsstatisticservice.entity.SensorsStatisticReport;
import com.mitskevich.sensorsstatisticservice.external.dto.SensorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Mapper
public abstract class SensorStatisticMapper {

    @Mapping(target = "typeCountMap", source = "sensorTypeCount")
    public abstract SensorStatisticDto toSensorStatisticDto(SensorsStatisticReport report);

    public SensorsStatisticReport toSensorsStatisticReport(List<SensorDto> sensorDtos) {
        Map<String, Long> sensorTypeCount = sensorDtos.stream()
            .collect(groupingBy(SensorDto::type, counting()));

        return SensorsStatisticReport.builder()
            .total((long) sensorDtos.size())
            .sensorTypeCount(sensorTypeCount)
            .build();
    }
}
