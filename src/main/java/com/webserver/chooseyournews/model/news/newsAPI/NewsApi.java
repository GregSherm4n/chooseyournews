package com.webserver.chooseyournews.model.news.newsAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webserver.chooseyournews.model.news.AbstractNews;
import com.webserver.chooseyournews.model.news.AbstractRubric;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApi extends AbstractNews {

    private String url;
    private String description;
    private String title;

    @JsonProperty("urlToImage")
    private String image;

    @JsonProperty("publishedAt")
    private String date;

    @JsonProperty("source")
    private NewsApiRubric rubric;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public AbstractRubric getRubric() {
        return rubric;
    }

    @Override
    public Long getDate() {
        var dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(this.date.replace("T", " "));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
