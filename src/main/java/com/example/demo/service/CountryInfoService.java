package com.example.demo.service;


import com.example.demo.dto.CountryInfo;
import com.example.demo.exception.CountryInfoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CountryInfoService {
    public static final String BASE_URL = "https://date.nager.at/api/v3/CountryInfo/";
    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    public HttpClient client;

    public CountryInfo getCountryInfo(String countryCode) {
        String url = BASE_URL + countryCode;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), CountryInfo.class);
            } else {
                throw new CountryInfoException("Something went wrong with response code: " + response.statusCode());
            }
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new CountryInfoException("Something went wrong ", e);
        }
    }
}
