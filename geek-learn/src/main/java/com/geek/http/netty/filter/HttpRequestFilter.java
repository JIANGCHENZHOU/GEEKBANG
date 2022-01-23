package com.geek.http.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    public void filter(FullHttpRequest request) throws Exception;
}
