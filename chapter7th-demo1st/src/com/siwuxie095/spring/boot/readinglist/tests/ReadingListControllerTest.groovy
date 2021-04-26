package com.siwuxie095.spring.boot.readinglist.tests

import com.siwuxie095.spring.boot.readinglist.ReadingListController
import com.siwuxie095.spring.boot.readinglist.ReadingListRepository
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.mockito.Mockito.*

/**
 * @author Jiajing Li 
 * @date 2021-04-22 22:56:28
 */
class ReadingListControllerTest {

    @Test
    void shouldReturnReadingListFromRepository() {
        List<Book> expectedList = new ArrayList<Book>()
        expectedList.add(new Book(
                id: 1,
                reader: "Craig",
                isbn: "9781617292545",
                title: "Spring Boot in Action",
                author: "Craig Walls",
                description: "Spring Boot in Action is ..."
        ))

        def mockRepo = mock(ReadingListRepository.class)
        when(mockRepo.findByReader("Craig")).thenReturn(expectedList)

        def controller = new ReadingListController(readingListRepository: mockRepo)

        MockMvc mvc = standaloneSetup(controller)
                .build()
        mvc.perform(get("/"))
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("books", expectedList))

    }

}
