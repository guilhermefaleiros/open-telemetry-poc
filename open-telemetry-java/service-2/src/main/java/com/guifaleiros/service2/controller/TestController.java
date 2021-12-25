package com.guifaleiros.service2.controller;

import com.guifaleiros.service2.persistence.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    TestRepository testRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public ResponseEntity test() {
        log.info("requesting /test");
        var response = this.testRepository.findAll();
        return ResponseEntity.ok().body(response);
    }
}
