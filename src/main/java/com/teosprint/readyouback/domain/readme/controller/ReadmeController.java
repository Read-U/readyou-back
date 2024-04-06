package com.teosprint.readyouback.domain.readme.controller;

import com.teosprint.readyouback.domain.readme.service.ReadmeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/readme")
@AllArgsConstructor
public class ReadmeController {

    private ReadmeService readmeService;

    @GetMapping
    public String getAllItemsText() {
        return readmeService.getAllItemsText();
    }

    @GetMapping("/items")
    public List<String> getItemText(@RequestParam String[] types) {
        return readmeService.getItemsText(types);
    }

    @GetMapping("/item")
    public String getItemText(@RequestParam String type) {
        System.out.println(type);
        return readmeService.getItemText(type);
    }
}
