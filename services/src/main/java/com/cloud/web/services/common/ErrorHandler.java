package com.cloud.web.services.common;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ErrorHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Object error(HttpServletResponse res, HttpServletRequest req) {
        // 错误处理逻辑
        String msg = "系统错误，请联系管理员";
        return msg;
    }
}
