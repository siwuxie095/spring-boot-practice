package com.siwuxie095.spring.boot.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jiajing Li
 * @date 2021-04-14 21:40:51
 */
@SuppressWarnings("all")
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(Reader reader);

}
