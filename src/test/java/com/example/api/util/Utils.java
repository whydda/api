package com.example.api.util;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 단위테스트 util 기능 모음
 */
public class Utils {
    private String getUrlencodedParams(Map<String, Object> params) {
        List<String> arr = new ArrayList<>();
        params.forEach((k, v) -> {
            arr.add(k + "=" + v);
        });
        return String.join("&", arr);
    }

    /**
     * @param port
     * @param callUri
     * @param reqMap
     * @param restTemplate
     * @return
     * @throws URISyntaxException
     */
    public int callPostVerificationApi(int port, String callUri, Map<String, Object> reqMap, TestRestTemplate restTemplate) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/" + callUri;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8")));
        HttpEntity request = new HttpEntity(getUrlencodedParams(reqMap), headers);

        ResponseEntity<String> result = restTemplate.postForEntity(
                uri
                , request
                , String.class
        );

        return result.getStatusCodeValue();
    }

    /**
     * @param port
     * @param callUri
     * @param reqMap
     * @param restTemplate
     * @return
     * @throws URISyntaxException
     */
    public int callGetVerificationApi(int port, String callUri, Map<String, Object> reqMap, TestRestTemplate restTemplate) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/" + callUri;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8")));

        ResponseEntity<String> result = restTemplate.getForEntity(
                uri + "?" + getUrlencodedParams(reqMap)
                , String.class
        );

        return result.getStatusCodeValue();
    }
}
