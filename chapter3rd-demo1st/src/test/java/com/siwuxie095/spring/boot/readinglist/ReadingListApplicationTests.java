package com.siwuxie095.spring.boot.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Jiajing Li
 * @date 2021-04-12 08:31:18
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = ReadingListApplication.class)
@WebAppConfiguration
public class ReadingListApplicationTests {

    @Test
    public void contextLoads() {
    }

}

