package com.siwuxie095.spring.boot.chapter4th.example2nd;

import org.springframework.stereotype.Service;

/**
 * @author Jiajing Li
 * @date 2021-04-14 23:01:06
 */
@SuppressWarnings("all")
@Service
public interface AddressService {

    Address findByLastName(String lastName);

}
