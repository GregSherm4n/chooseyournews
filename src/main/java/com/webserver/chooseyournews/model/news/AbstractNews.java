package com.webserver.chooseyournews.model.news;

public abstract class AbstractNews {
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract String getImage();
    public abstract AbstractRubric getRubric();
    public abstract Long getDate();
    public abstract String getUrl();
}
