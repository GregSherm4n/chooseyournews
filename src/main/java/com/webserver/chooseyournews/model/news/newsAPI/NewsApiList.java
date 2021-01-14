package com.webserver.chooseyournews.model.news.newsAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiList {

    private List<NewsApi> newsList;

}
