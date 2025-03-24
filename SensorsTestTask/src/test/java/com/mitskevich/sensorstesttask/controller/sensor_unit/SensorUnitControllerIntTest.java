package com.mitskevich.sensorstesttask.controller.sensor_unit;


import com.fasterxml.jackson.core.type.TypeReference;
import com.mitskevich.sensorstesttask.BaseIntegrationTest;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_unit.dto.SensorUnitDto;
import com.mitskevich.sensorstesttask.repository.sensor_unit.SensorUnitRepository;
import com.mitskevich.sensorstesttask.util.FilesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SensorUnitControllerIntTest extends BaseIntegrationTest {

    @Autowired
    private WebTestClient api;

    @Autowired
    private SensorUnitRepository sensorUnitRepository;

    private static final String BASE_URI = "/sensors/units";

    @Test
    void createSensorShouldReturn200() {
        SensorUnitCreateDto incorrectRequest = FilesUtil.getObjectFromString(
            "{ \"name\" : \"Humidities\"}",
            new TypeReference<>() {});

        SensorUnitDto actualResponse = api.post()
            .uri(BASE_URI)
            .bodyValue(incorrectRequest)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(SensorUnitDto.class)
            .returnResult()
            .getResponseBody();

        SensorUnitDto actualExpected = FilesUtil.getObjectFromString(
            "{ \"name\" : \"Humidities\"}",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).usingRecursiveComparison()
            .ignoringFields(SensorUnitDto.Fields.id)
            .isEqualTo(actualExpected);
    }

    @Test
    void getSensorsShouldReturn200() {
        List<SensorUnitDto> actualResponse = api.get()
            .uri(BASE_URI)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<List<SensorUnitDto>>() {})
            .returnResult()
            .getResponseBody();

        List<SensorUnitDto> actualExpected = FilesUtil.getObjectFromString(
            "[ { \"name\" : \"Bar\"}, " +
                "{ \"name\" : \"Voltage\"}," +
                "{ \"name\" : \"Celsius\"}," +
                " { \"name\" : \"Percentage\"} ]",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).usingRecursiveComparison()
            .ignoringFields(SensorDto.Fields.id)
            .isEqualTo(actualExpected);
    }

    @Test
    void deleteSensorShouldReturn200() {
        Long id = Long.valueOf("3");

        api.delete()
            .uri(BASE_URI.concat("/").concat(id.toString()))
            .exchange()
            .expectStatus()
            .isNoContent();

        boolean isSensorDeleted = sensorUnitRepository.findById(id).isEmpty();

        assertThat(isSensorDeleted).isTrue();
    }

}
