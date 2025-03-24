package com.mitskevich.sensorsstatisticservice.repository.specifiication;

import com.mitskevich.sensorsstatisticservice.dto.filter.StatisticFilter;
import com.mitskevich.sensorsstatisticservice.entity.SensorsStatisticReport;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Optional;

@UtilityClass
public class SensorStatisticSpecification {

    public static Specification<SensorsStatisticReport> build(StatisticFilter filter) {
        Specification<SensorsStatisticReport> fromTimeSpec = Optional.of(filter)
            .map(StatisticFilter::from)
            .map(SensorStatisticSpecification::from)
            .orElse(null);

        Specification<SensorsStatisticReport> toTimeSpec = Optional.of(filter)
            .map(StatisticFilter::to)
            .map(SensorStatisticSpecification::to)
            .orElse(null);

        return Specification.allOf(fromTimeSpec, toTimeSpec);
    }

    private static Specification<SensorsStatisticReport> from(LocalDateTime from) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(
            root.get(SensorsStatisticReport.Fields.time), from
        );
    }

    private static Specification<SensorsStatisticReport> to(LocalDateTime to) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(
            root.get(SensorsStatisticReport.Fields.time), to
        );
    }

}
