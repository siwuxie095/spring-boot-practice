package com.siwuxie095.spring.boot.readinglist

/**
 * @author Jiajing Li 
 * @date 2021-04-18 17:46:11
 */
@Controller
@RequestMapping("/")
class ReadingListController {

    String reader = "Craig"

    @Autowired
    ReadingListRepository readingListRepository

    @RequestMapping(method=RequestMethod.GET)
    def readersBooks(Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader)

        if (readingList != null) {
            model.addAttribute("books", readingList)
        }

        "readingList"
    }

    @RequestMapping(method=RequestMethod.POST)
    def addToReadingList(Book book) {
        book.setReader(reader)
        readingListRepository.save(book)
        "redirect:/"
    }

}
