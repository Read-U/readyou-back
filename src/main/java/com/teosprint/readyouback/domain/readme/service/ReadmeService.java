package com.teosprint.readyouback.domain.readme.service;

import java.util.List;

public interface ReadmeService {
    String getAllItemsText();

    List<String> getItemsText(String[] types);
    String getItemText(String type);
}
