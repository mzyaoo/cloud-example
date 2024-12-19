package cn.mzyao.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gateway")
public class IndexController {

    @GetMapping("index")
    public String index() {
        return "Hello Gateway";
    }

}
