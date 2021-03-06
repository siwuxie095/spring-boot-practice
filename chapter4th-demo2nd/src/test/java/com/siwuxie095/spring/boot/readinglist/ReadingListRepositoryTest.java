package com.siwuxie095.spring.boot.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Jiajing Li
 * @date 2021-04-14 22:42:31
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
public class ReadingListRepositoryTest {

    @Autowired
    private ReadingListRepository repo;

    @Test
    @Transactional
    public void saveABook() throws Exception {
        assertEquals(0, repo.findAll().size());

        Book book = new Book();
        book.setTitle("TITLE");
        book.setDescription("DESCRIPTION");
        book.setAuthor("AUTHOR");
        book.setIsbn("1234567890");
        book.setReader("reader");
        Book saved = repo.save(book);

        Book found = repo.findOne(saved.getId());
        assertEquals(saved.getId(), found.getId());
        assertEquals("TITLE", found.getTitle());
        assertEquals("DESCRIPTION", found.getDescription());
        assertEquals("AUTHOR", found.getAuthor());
        assertEquals("1234567890", found.getIsbn());
        assertEquals("reader", found.getReader());
    }

}

