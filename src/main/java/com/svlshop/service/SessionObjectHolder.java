package com.svlshop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/** Подсчет количества кликов на сайте */

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionObjectHolder {

    private long amountClicks;

    public long getAmountClicks() {
        return amountClicks;
    }

    public void addClick() {
        amountClicks++;
    }
    //WebApplicationContext.SCOPE_SESSION - активен только в сессии
}
