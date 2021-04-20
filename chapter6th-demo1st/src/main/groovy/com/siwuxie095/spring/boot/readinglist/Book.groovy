package com.siwuxie095.spring.boot.readinglist

import grails.persistence.*

/**
 * @author Jiajing Li 
 * @date 2021-04-20 22:06:44
 */
@Entity
class Book {

    Reader reader
    String isbn
    String title
    String author
    String description

}

