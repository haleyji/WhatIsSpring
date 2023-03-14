package com.example.wis.web.controller;

import com.example.wis.common.MyLogger;
import com.example.wis.web.service.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
        System.out.println(myLogger.getClass());
    }

    @RequestMapping("/log-demo")
    @ResponseBody
    public void logDemo(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        myLogger.setRequestUrl(url);
        myLogger.log("controller test");

        logDemoService.logic("testId");

        System.out.println(myLogger.getClass());
    }
}
