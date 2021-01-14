package com.webserver.chooseyournews.model.news.lenta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webserver.chooseyournews.model.news.AbstractNews;
import com.webserver.chooseyournews.model.news.AbstractRubric;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lenta extends AbstractNews {

    private LentaInfo info;
    private LentaLink links;
    private LentaRubric rubric;
    private List<LentaTag> tags;

    @JsonProperty("title_image")
    private LentaImage titleImage;

    @Override
    public String getTitle() {
        return info.getTitle();
    }

    @Override
    public String getDescription() {
        return info.getRightcol();
    }

    @Override
    public String getImage() {
        if (titleImage != null)
            return titleImage.getUrl();
        else
            return null;
    }

    @Override
    public AbstractRubric getRubric() {
        return rubric;
    }

    @Override
    public Long getDate() {
        return info.getModified();
    }

    @Override
    public String getUrl() {
        return links.getPublicField();
    }
}
