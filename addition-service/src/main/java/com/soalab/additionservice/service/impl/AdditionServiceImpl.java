package com.soalab.additionservice.service.impl;

import com.soalab.additionservice.service.AdditionService;
import org.springframework.stereotype.Service;

@Service
public class AdditionServiceImpl implements AdditionService {

    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }
}
