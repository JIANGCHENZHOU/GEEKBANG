package com.geek.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpClientTest {
    private static void post() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("http://localhost:8801").get().build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody body = response.body();
                System.out.println(body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        post();
    }
}
