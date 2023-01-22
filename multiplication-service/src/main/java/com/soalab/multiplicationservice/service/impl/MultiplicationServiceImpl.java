package com.soalab.multiplicationservice.service.impl;

import com.soalab.multiplicationservice.service.MultiplicationService;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    @Override
    public Integer multiply(Integer a, Integer b) {
        return a*b;
    }
}
