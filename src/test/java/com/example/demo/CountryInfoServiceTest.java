package com.example.demo;

import com.example.demo.dto.CountryInfo;
import com.example.demo.exception.CountryInfoException;
import com.example.demo.service.CountryInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CountryInfoServiceTest {

    @Spy
    HttpClient client;

    @Mock
    HttpResponse<String> mockResponse;

    @Spy
    ObjectMapper objectMapper;

    @InjectMocks
    CountryInfoService countryInfoService;

    public static String response = "{\n" +
            "    \"commonName\": \"Latvia\",\n" +
            "    \"officialName\": \"Republic of Latvia\",\n" +
            "    \"countryCode\": \"LV\",\n" +
            "    \"region\": \"Europe\",\n" +
            "    \"borders\": [\n" +
            "        {\n" +
            "            \"commonName\": \"Belarus\",\n" +
            "            \"officialName\": \"Republic of Belarus\",\n" +
            "            \"countryCode\": \"BY\",\n" +
            "            \"region\": \"Europe\",\n" +
            "            \"borders\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"commonName\": \"Estonia\",\n" +
            "            \"officialName\": \"Republic of Estonia\",\n" +
            "            \"countryCode\": \"EE\",\n" +
            "            \"region\": \"Europe\",\n" +
            "            \"borders\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"commonName\": \"Lithuania\",\n" +
            "            \"officialName\": \"Republic of Lithuania\",\n" +
            "            \"countryCode\": \"LT\",\n" +
            "            \"region\": \"Europe\",\n" +
            "            \"borders\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"commonName\": \"Russia\",\n" +
            "            \"officialName\": \"Russian Federation\",\n" +
            "            \"countryCode\": \"RU\",\n" +
            "            \"region\": \"Europe\",\n" +
            "            \"borders\": null\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    void getCountryInfo_Success() throws IOException, InterruptedException {
        when(client.send(any(), any(HttpResponse.BodyHandlers.ofString().getClass()))).thenReturn(mockResponse);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(response);

        CountryInfo result = countryInfoService.getCountryInfo("LV");

        assertEquals(4, result.getBorders().size());
    }

    @Test
    void getCountryInfo_Failed_Status() throws IOException, InterruptedException {
        when(client.send(any(), any(HttpResponse.BodyHandlers.ofString().getClass()))).thenReturn(mockResponse);
        when(mockResponse.statusCode()).thenReturn(404);

        CountryInfoException exception = assertThrows(CountryInfoException.class, () -> countryInfoService.getCountryInfo("LV"));
        assertEquals("Something went wrong with response code: 404", exception.getMessage());
    }

    @Test
    void getCountryInfo_Failed_Client() throws IOException, InterruptedException {
        when(client.send(any(), any(HttpResponse.BodyHandlers.ofString().getClass()))).thenThrow(new InterruptedException());

        CountryInfoException exception = assertThrows(CountryInfoException.class, () -> countryInfoService.getCountryInfo("LV"));
        assertEquals("Something went wrong ", exception.getMessage());
    }
}
