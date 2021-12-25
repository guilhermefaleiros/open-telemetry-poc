package com.guifaleiros.service2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public ResponseEntity test() throws URISyntaxException, IOException, InterruptedException {

        var request = HttpRequest.newBuilder()
                .uri(new URI("http://service-2:8081/test"))
                .GET()
                .build();

        var client = HttpClient.newBuilder().build();

        log.info("making request to service-2");
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("request to service-2 successfully");
        log.info("statusCode: " + response.statusCode());

        return ResponseEntity.internalServerError().build();
    }
}
