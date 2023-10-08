package com.opentech.application.controller;

import com.opentech.application.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/stocks")
public record StockController(StockService service) {
}
