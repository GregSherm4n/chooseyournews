package com.webserver.chooseyournews.model.news.newsAPI;

import com.webserver.chooseyournews.model.news.AbstractRubric;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ToString

public class NewsApiRubric extends AbstractRubric {

    private String name;
    private String identificator;

    @Override
    public String getSlug() {
        if (identificator != null) {
            return identificator;

        } else {
            return "all";
        }
    }

    @Override
    public String getTitle() {
        return name;
    }
}
