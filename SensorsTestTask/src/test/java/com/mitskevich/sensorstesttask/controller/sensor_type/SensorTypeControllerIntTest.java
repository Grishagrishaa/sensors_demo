package com.mitskevich.sensorstesttask.controller.sensor_type;


import com.fasterxml.jackson.core.type.TypeReference;
import com.mitskevich.sensorstesttask.BaseIntegrationTest;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor_type.dto.SensorTypeDto;
import com.mitskevich.sensorstesttask.repository.sensor_type.SensorTypeRepository;
import com.mitskevich.sensorstesttask.util.FilesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SensorTypeControllerIntTest extends BaseIntegrationTest {

    @Autowired
    private WebTestClient api;

    @Autowired
    private SensorTypeRepository sensorTypeRepository;

    private static final String BASE_URI = "/sensors/types";

    @Test
    void createSensorShouldReturn200() {
        SensorTypeCreateDto incorrectRequest = FilesUtil.getObjectFromString(
            "{ \"name\" : \"Hydrometres\"}",
            new TypeReference<>() {});

        SensorTypeDto actualResponse = api.post()
            .uri(BASE_URI)
            .bodyValue(incorrectRequest)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(SensorTypeDto.class)
            .returnResult()
            .getResponseBody();

        SensorTypeDto actualExpected = FilesUtil.getObjectFromString(
            "{ \"name\" : \"Hydrometres\"}",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).usingRecursiveComparison()
            .ignoringFields(SensorTypeDto.Fields.id)
            .isEqualTo(actualExpected);
    }

    @Test
    void getSensorsShouldReturn200() {
        List<SensorTypeDto> actualResponse = api.get()
            .uri(BASE_URI)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<List<SensorTypeDto>>() {})
            .returnResult()
            .getResponseBody();

        List<SensorTypeDto> actualExpected = FilesUtil.getObjectFromString(
            "[ { \"name\" : \"Pressure\"}, " +
                "{ \"name\" : \"Voltage\"}," +
                "{ \"name\" : \"Temperature\"}," +
                " { \"name\" : \"Humidity\"} ]",
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

        boolean isSensorDeleted = sensorTypeRepository.findById(id).isEmpty();

        assertThat(isSensorDeleted).isTrue();
    }

}
