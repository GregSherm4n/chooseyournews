package com.webserver.chooseyournews.model.news.lenta;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LentaList {

    private List<Lenta> newsList;

}
