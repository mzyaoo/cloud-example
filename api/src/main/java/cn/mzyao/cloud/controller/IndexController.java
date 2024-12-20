package cn.mzyao.cloud.controller;

import cn.mzyao.cloud.tools.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class IndexController {

    @GetMapping("index")
    public Result<String> index(){
        return Result.success("Hello World");
    }

}
