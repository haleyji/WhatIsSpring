package com.example.wis.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String msg) {
        log.info("[{}] [{}] [{}]", uuid, requestUrl, msg);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        log.info("[{}] [{}] [{}]", uuid, "request created", this);
    }

    @PreDestroy
    public void destroy() {
        log.info("[{}] [{}] [{}]", uuid, "request destroyed", this);
    }
}
