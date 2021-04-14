package com.siwuxie095.spring.boot.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jiajing Li
 * @date 2021-04-14 22:44:37
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest(randomPort=true)
public class ServerWebTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testSomething() throws Exception {

        RestTemplate test = new RestTemplate();
        String s = test.getForObject("http://localhost:{port}", String.class, port);
        System.out.println(s);
    }

}

