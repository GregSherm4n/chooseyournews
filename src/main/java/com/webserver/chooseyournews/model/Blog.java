package com.webserver.chooseyournews.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Blog {

    private String author;
    private String title;
    private String content;
    private Long publishedAt;

    public Blog(String author,String title, String content, Long publishedAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
    }
}
