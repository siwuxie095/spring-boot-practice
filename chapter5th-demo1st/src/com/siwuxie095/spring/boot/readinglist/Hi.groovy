package com.siwuxie095.spring.boot.readinglist

/**
 * @author Jiajing Li 
 * @date 2021-04-18 17:44:08
 */
@Controller
class Hi {
    @RequestMapping("/hi")
    def hi() {
        "hiya"
    }
}
