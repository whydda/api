package com.example.api.comm;

import com.example.api.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserVerification extends Utils {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 로그인 검증
     *
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void login() throws URISyntaxException, IOException, ClassNotFoundException {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("ID", "whydda");
        reqMap.put("PASSWORD", "1234");

        Assert.assertEquals(200, super.callPostVerificationApi(randomServerPort, "login", reqMap, this.restTemplate));
    }
}
