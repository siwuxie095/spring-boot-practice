package com.siwuxie095.spring.boot.chapter5th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-18 22:38:56
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 用 CLI 运行测试
     *
     * 测试是软件项目的重要组成部分，Spring Boot CLI 当然没有忽略测试。因为基于 CLI 的应用程序并未涉及传统
     * 的构建系统，所以 CLI 提供了一个 test 命令来运行测试。
     *
     * 在试验 test 命令前，你先要写一个测试。测试可以放在项目中的任何位置。这里建议将其与主要组件分开放置，最
     * 好放在一个子目录里。这个子目录的名字随意。在这里将其命名为 tests：
     *
     * mkdir tests
     *
     * 在 tests 目录里，创建一个名为 ReadingListControllerTest.groovy 的新 Groovy 脚本，编写针对
     * ReadingListController 的测试。如下代码是个简单的测试，测试控制器能否正确处理 HTTP GET 请求。
     *
     * import org.springframework.test.web.servlet.MockMvc
     * import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
     * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
     * import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
     * import static org.mockito.Mockito.*
     *
     *
     * class ReadingListControllerTest {
     *
     *     @Test
     *     void shouldReturnReadingListFromRepository() {
     *         List<Book> expectedList = new ArrayList<Book>()
     *         expectedList.add(new Book(
     *                 id: 1,
     *                 reader: "Craig",
     *                 isbn: "9781617292545",
     *                 title: "Spring Boot in Action",
     *                 author: "Craig Walls",
     *                 description: "Spring Boot in Action is ..."
     *         ))
     *
     *         def mockRepo = mock(ReadingListRepository.class)
     *         when(mockRepo.findByReader("Craig")).thenReturn(expectedList)
     *
     *         def controller = new ReadingListController(readingListRepository: mockRepo)
     *
     *         MockMvc mvc = standaloneSetup(controller)
     *                 .build()
     *         mvc.perform(get("/"))
     *                 .andExpect(view().name("readingList"))
     *                 .andExpect(model().attribute("books", expectedList))
     *
     *     }
     *
     * }
     *
     * 如你所见，这就是个简单的 JUnit 测试，使用了 Spring 的模拟 MVC 测试支持功能，对控制器发起 GET 请求。
     * 最先设置的是 ReadingListRepository 的一个模拟实现，它会返回一个包含单一 Book 项的列表。随后，测试
     * 创建了一个 ReadingListController 实例，将模拟仓库注入 readingListRepository 属性。最后，配置了
     * 一个 MockMvc 对象，发起 GET 请求，对期望的视图名称和模型内容进行断言。
     *
     * 但是，此处运行测试要比说明测试更重要。使用 CLI 的 test 命令，可以像下面这样在命令行里执行测试：
     *
     * spring test tests/ReadingListControllerTest.groovy
     *
     * 本例中，明确选中了 ReadingListControllerTest 作为要运行的测试。如果 tests/ 目录里有多个测试，你
     * 想要全部运行，可以在 test 命令中指定目录名：
     *
     * spring test tests
     *
     * 如果你倾向于编写 Spock 说明而非 JUnit 测试，那么你一定会很高兴，因为 CLI 的 test 命令也可以运行
     * Spock 说明，如下代码的 ReadingListControllerSpec 就演示了这一功能。
     *
     * import org.springframework.test.web.servlet.MockMvc
     * import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
     * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
     * import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
     * import static org.mockito.Mockito.*
     *
     *
     * class ReadingListControllerSpec extends Specification {
     *
     *     MockMvc mockMvc
     *     List<Book> expectedList
     *
     *     def setup() {
     *         expectedList = new ArrayList<Book>()
     *         expectedList.add(new Book(
     *                 id: 1,
     *                 reader: "Craig",
     *                 isbn: "9781617292545",
     *                 title: "Spring Boot in Action",
     *                 author: "Craig Walls",
     *                 description: "Spring Boot in Action is ..."
     *         ))
     *
     *         def mockRepo = mock(ReadingListRepository.class)
     *         when(mockRepo.findByReader("Craig")).thenReturn(expectedList)
     *
     *         def controller =
     *                 new ReadingListController(readingListRepository: mockRepo)
     *         mockMvc = standaloneSetup(controller).build()
     *     }
     *
     *     def "Should put list returned from repository into model"() {
     *         when:
     *         def response = mockMvc.perform(get("/"))
     *
     *         then:
     *         response.andExpect(view().name("readingList"))
     *                 .andExpect(model().attribute("books", expectedList))
     *     }
     *
     * }
     *
     * ReadingListControllerSpec 只是简单地把 ReadingListControllerTest 从 JUnit 测试翻译成了 Spock
     * 说明。如你所见，它只是直白地表述了这么一个过程。对 "/" 出现 GET 请求时，响应中应该包含名为 readingList
     * 的视图。模型里的 books 键所对应的就是期待的图书列表。
     *
     * Spock 说明也可以通过 spring test tests 来运行 ReadingListControllerSpec。运行方式和基于 JUnit
     * 的测试如出一辙。
     *
     * 一旦写好代码，通过了全部测试，你就该部署项目了。后续来看看 Spring Boot CLI 是如何帮助产生一个可部署的
     * 产物的。
     */
    public static void main(String[] args) {

    }

}
