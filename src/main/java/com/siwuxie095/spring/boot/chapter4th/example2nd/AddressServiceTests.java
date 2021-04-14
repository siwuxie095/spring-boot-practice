package com.siwuxie095.spring.boot.chapter4th.example2nd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Jiajing Li
 * @date 2021-04-14 22:58:49
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AddressBookConfiguration.class)
public class AddressServiceTests {

    @Autowired
    private AddressService addressService;

    @Test
    public void testService() {
        Address address = addressService.findByLastName("Sheman");
        assertEquals("P", address.getFirstName());
        assertEquals("Sherman", address.getLastName());
        assertEquals("42 Wallaby Way", address.getAdressLine1());
        assertEquals("Sydney", address.getCity());
        assertEquals("New South Wales", address.getState());
        assertEquals("2000", address.getPostCode());
    }

}
