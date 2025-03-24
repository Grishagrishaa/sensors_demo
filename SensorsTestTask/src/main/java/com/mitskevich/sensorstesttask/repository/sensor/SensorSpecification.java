package com.mitskevich.sensorstesttask.repository.sensor;

import com.mitskevich.sensorstesttask.domain.sensor.dto.filter.SearchFilterDto;
import com.mitskevich.sensorstesttask.domain.sensor.entity.Sensor;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@UtilityClass
public class SensorSpecification {

    public static Specification<Sensor> build(SearchFilterDto filter) {
        Specification<Sensor> nameSpec = Optional.of(filter)
            .map(SearchFilterDto::nameModel)
            .map(SensorSpecification::nameModel)
            .orElse(null);

        return Specification.allOf(nameSpec);
    }

    private static Specification<Sensor> nameModel(String nameModel) {
        return (root, query, cb) -> cb.or(
            cb.like(cb.lower(root.get(Sensor.Fields.name)), "%" + nameModel.toLowerCase() + "%"),
            cb.like(cb.lower(root.get(Sensor.Fields.model)), "%" + nameModel.toLowerCase() + "%")
        );
    }
}
