package com.teosprint.readyouback.domain.readme.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadmeServiceImpl implements ReadmeService{

    @Override
    public String getAllItemsText() {
        return "getAllItemsText Test";
    }

    @Override
    public List<String> getItemsText(String[] types) {
        return List.of("items", "test");
    }

    @Override
    public String getItemText(String type) {
        return "getItemText Test";
    }
}
