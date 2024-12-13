package cn.mzyao.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/index")
    public String index() {
        return "资源数据";
    }

}
