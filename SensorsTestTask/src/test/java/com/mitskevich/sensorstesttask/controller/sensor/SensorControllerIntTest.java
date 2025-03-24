package com.mitskevich.sensorstesttask.controller.sensor;


import com.fasterxml.jackson.core.type.TypeReference;
import com.mitskevich.sensorstesttask.BaseIntegrationTest;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorCreateDto;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorDto;
import com.mitskevich.sensorstesttask.domain.sensor.dto.SensorUpdateDto;
import com.mitskevich.sensorstesttask.domain.sensor.entity.Sensor;
import com.mitskevich.sensorstesttask.repository.sensor.SensorRepository;
import com.mitskevich.sensorstesttask.util.FilesUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SensorControllerIntTest extends BaseIntegrationTest {

    @Autowired
    private WebTestClient api;

    @Autowired
    private SensorRepository sensorRepository;

    private static final String BASE_URI = "/sensors";

    @CsvSource({
        "/requests/sensor/create/createRequestIncorrect1.json",
        "/requests/sensor/create/createRequestIncorrect2.json",
        "/requests/sensor/create/createRequestIncorrect3.json",
        "/requests/sensor/create/createRequestIncorrect4.json"
    })
    @ParameterizedTest
    void createSensorShouldReturn400(String filePath) {
        SensorCreateDto request = FilesUtil.getObjectFromTestFiles(filePath, new TypeReference<>() {});
        api.post()
            .uri(BASE_URI)
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isBadRequest();
    }

    @Test
    void createSensorShouldReturn200() {
        SensorCreateDto request = FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/create/createRequest.json",
            new TypeReference<>() {}
        );

        SensorDto actualResponse = api.post()
            .uri(BASE_URI)
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(SensorDto.class)
            .returnResult()
            .getResponseBody();

        SensorDto actualExpected = FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/create/expected/createSensorExpected.json",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).usingRecursiveComparison()
            .ignoringFields(SensorDto.Fields.id)
            .isEqualTo(actualExpected);
    }

    @Test
    void getSensorShouldReturn200() {
        UUID id = UUID.fromString("4afd37b6-db76-49c4-85ee-94c52e945389");
        SensorDto actualResponse = api.get()
            .uri(BASE_URI.concat("/").concat(id.toString()))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(SensorDto.class)
            .returnResult()
            .getResponseBody();

        SensorDto actualExpected = FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/get/expected/getSensorExpected.json",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).isEqualTo(actualExpected);
    }

    @Test
    void getSensorsShouldReturn200() {
        List<SensorDto> actualResponse = api.get()
            .uri(BASE_URI)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(new ParameterizedTypeReference<List<SensorDto>>() {})
            .returnResult()
            .getResponseBody();

        List<SensorDto> actualExpected = FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/get/expected/getSensorsExpected.json",
            new TypeReference<>() {}
        );

        assertThat(actualResponse).usingRecursiveComparison()
            .ignoringFields(SensorDto.Fields.id)
            .isEqualTo(actualExpected);
    }

    @Test
    void updateSensorShouldReturn200() {
        UUID id = UUID.fromString("4afd37b6-db76-49c4-85ee-94c52e945389");
        SensorUpdateDto request = FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/update/updateRequest.json",
            new TypeReference<>() {}
        );

        api.put()
            .uri(BASE_URI.concat("/").concat(id.toString()))
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isAccepted();

        SensorDto expected= FilesUtil.getObjectFromTestFiles(
            "/requests/sensor/update/expected/updateSensorExpected.json",
            new TypeReference<>() {}
        );
        Sensor actual = sensorRepository.getReferenceById(id);

        assertThat(expected).isNotEqualTo(actual);
    }

    @Test
    void deleteSensorShouldReturn200() {
        UUID id = UUID.fromString("4afd37b6-db76-49c4-85ee-94c52e945389");

        api.delete()
            .uri(BASE_URI.concat("/").concat(id.toString()))
            .exchange()
            .expectStatus()
            .isNoContent();

        boolean isSensorDeleted = sensorRepository.findById(id).isEmpty();

        assertThat(isSensorDeleted).isTrue();
    }

}
