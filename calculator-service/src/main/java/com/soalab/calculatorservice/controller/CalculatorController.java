package com.soalab.calculatorservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class CalculatorController {
    private final RestTemplate restTemplate;

    @GetMapping("/{operation}/{a}/{b}")
    public ResponseEntity<Integer> calculate(@PathVariable String operation,
                                             @PathVariable Integer a,
                                             @PathVariable Integer b) {
//        RestTemplate restTemplate = new RestTemplate();
        Integer result = 0;
        try{
            switch (operation){
                case("add"):
                    result = restTemplate.getForObject("http://addition-service/add/" + a + "/" + b, Integer.class);
                    break;
                case("multiply"):
                    result = restTemplate.getForObject("http://multiplication-service/multiply/" + a + "/" + b, Integer.class);
                    break;
                case("subtract"):
                    String temporary = restTemplate.getForObject("http://subtraction-service/subtract/" + a + "/" + b, String.class);
                    result = Integer.valueOf(temporary);
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }  catch (HttpClientErrorException.TooManyRequests e){
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
//        if (operation.equalsIgnoreCase("add")) {
//
//        } else if (operation.equalsIgnoreCase("multiply")) {
//
//        } else {
//
//        }
//        ResponseEntity responseEntity = ResponseEntity.ok
        return ResponseEntity.ok(result);
    }
}
