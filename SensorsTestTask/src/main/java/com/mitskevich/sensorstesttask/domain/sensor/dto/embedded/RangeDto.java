package com.mitskevich.sensorstesttask.domain.sensor.dto.embedded;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;

@Schema(description = "Радиус работы")
public record RangeDto(@Schema(description = "Радиус от") Integer from,
                       @Schema(description = "Радиус до") Integer to) {

    @AssertTrue
    public boolean isValid() {
        boolean isValuesValid = (from == null || from > 0) && (to != null && to > 0);

        if (from != null && isValuesValid) {
            return from < to;
        }

        return isValuesValid;
    }
}
