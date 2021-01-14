package com.webserver.chooseyournews.model.news.lenta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LentaLink {

    private String self;

    @JsonProperty("public")
    private String publicField;

}
