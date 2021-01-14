package com.webserver.chooseyournews.model.news.lenta;

import com.webserver.chooseyournews.model.news.AbstractRubric;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ToString
public class LentaRubric extends AbstractRubric{

    private String title;
    private String slug;

    @Override
    public String getSlug() {
        if (slug != null) {
            return slug;

        } else {
            return "all";
        }
    }

    @Override
    public String getTitle() {
        return title;
    }
}
