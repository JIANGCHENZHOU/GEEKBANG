package com.geek.http.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class UrlHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest request) throws Exception {
        String uri = request.uri();
        if(!uri.contains("/test")) {
            throw new Exception("uri invalid");
        }
    }
    
}
