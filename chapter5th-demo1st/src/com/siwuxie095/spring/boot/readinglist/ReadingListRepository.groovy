package com.siwuxie095.spring.boot.readinglist

/**
 * @author Jiajing Li 
 * @date 2021-04-18 17:44:49
 */
interface ReadingListRepository {

    List<Book> findByReader(String reader)

    void save(Book book)

}
