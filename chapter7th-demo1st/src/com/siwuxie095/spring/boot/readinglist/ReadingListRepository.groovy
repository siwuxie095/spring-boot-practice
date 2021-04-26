package com.siwuxie095.spring.boot.readinglist

/**
 * @author Jiajing Li 
 * @date 2021-04-22 22:47:56
 */
interface ReadingListRepository {

    List<Book> findByReader(String reader)

    void save(Book book)

}
