package com.fw.domain;

import java.util.concurrent.Callable;

/**
 * @author yqf
 */
public class ProcessThread implements Callable {
    @Override
    public Object call() throws Exception {
        return "ok";
    }
}
