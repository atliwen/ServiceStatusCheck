package com.atliwen.news;

import lombok.Data;

/**
 * @author 李文
 * @create 2020-03-19 16:49
 **/
@Data
public class News
{
    private String name;
    private String url;
    private String data;

    public News(){}

    public News(String name, String url, String data) {
        this.name = name;
        this.url = url;
        this.data = data;
    }
}
