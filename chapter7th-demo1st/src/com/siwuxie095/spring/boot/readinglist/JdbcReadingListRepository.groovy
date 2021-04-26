package com.siwuxie095.spring.boot.readinglist

/**
 * @author Jiajing Li 
 * @date 2021-04-22 22:46:59
 */
@Repository
class JdbcReadingListRepository implements ReadingListRepository {

    @Autowired
    JdbcTemplate jdbc

    List<Book> findByReader(String reader) {
        jdbc.query(
                "select id, reader, isbn, title, author, description " +
                        "from Book where reader=?",
                { rs, row ->
                    new Book(id: rs.getLong(1),
                            reader: rs.getString(2),
                            isbn: rs.getString(3),
                            title: rs.getString(4),
                            author: rs.getString(5),
                            description: rs.getString(6))
                } as RowMapper,
                reader)
    }

    void save(Book book) {
        jdbc.update("insert into Book " +
                "(reader, isbn, title, author, description) " +
                "values (?, ?, ?, ?, ?)",
                book.reader,
                book.isbn,
                book.title,
                book.author,
                book.description)
    }

}
