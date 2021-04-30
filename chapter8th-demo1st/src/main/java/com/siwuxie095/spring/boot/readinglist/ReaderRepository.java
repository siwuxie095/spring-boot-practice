package com.siwuxie095.spring.boot.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiajing Li
 * @date 2021-04-30 21:25:08
 */
@SuppressWarnings("all")
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
