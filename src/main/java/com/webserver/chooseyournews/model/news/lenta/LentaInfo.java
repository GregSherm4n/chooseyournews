package com.webserver.chooseyournews.model.news.lenta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LentaInfo {

    private String id;
    private String title;
    private String rightcol;
    private Long modified;

}
