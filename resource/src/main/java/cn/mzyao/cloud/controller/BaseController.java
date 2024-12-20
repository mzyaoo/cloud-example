package cn.mzyao.cloud.controller;

import cn.mzyao.cloud.service.SysConfigService;
import cn.mzyao.cloud.tools.enums.ResponseCode;
import cn.mzyao.cloud.tools.exception.GatewayException;
import cn.mzyao.cloud.tools.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("/index")
    public Result<Long> index() {
        long count = sysConfigService.count();
        return Result.success(count);
    }


    @GetMapping("/ex")
    public Result<String> ex() {
        throw new GatewayException(ResponseCode.SYSTEM_ERROR);
//        return Result.success("Hello Ex");
    }

}
