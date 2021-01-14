package com.webserver.chooseyournews.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webserver.chooseyournews.model.news.AbstractNews;
import com.webserver.chooseyournews.model.news.AbstractRubric;
import com.webserver.chooseyournews.model.news.lenta.LentaList;
import com.webserver.chooseyournews.model.news.newsAPI.NewsApiList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {

    private final int AMOUNT_OF_NEWS = 5;

    @GetMapping({"", "/", "home"})
    public String homePage(Model model, @RequestParam(name = "id", required = false, defaultValue = "1") Integer reqId,
                           @RequestParam(name = "slug", required = true, defaultValue = "all") String slug)
            throws URISyntaxException, IOException, InterruptedException {

        var httpClient = HttpClient.newHttpClient();
        var httpRequestLenta = HttpRequest.newBuilder().GET().uri(
                new URI("https://api.lenta.ru/lists/latest")).build();
        HttpResponse<String> responseLenta = null;
        try {
            responseLenta = httpClient.send(httpRequestLenta, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        var mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        var lenta = mapper.readValue(responseLenta.body(), LentaList.class);
        var news = new ArrayList<AbstractNews>(lenta.getNewsList());

        var httpRequestNewsApi = HttpRequest.newBuilder().GET().uri(
                new URI("https://newsapi.org/v2/top-headlines?country=ru&apiKey=ac6da715da0f45858d608a982c403280")).build();
        var responseNewsApi = httpClient.send(httpRequestNewsApi, HttpResponse.BodyHandlers.ofString());
        var apiNews = mapper.readValue(responseNewsApi.body(), NewsApiList.class);
        news.addAll(apiNews.getNewsList());
        List<AbstractNews> filteredNews = new ArrayList<AbstractNews>();

        //сортируем новости по дате
        if (slug.isEmpty() || slug.isBlank() || slug.equals("all")) {
            news.sort(new Comparator<AbstractNews>() {
                @Override
                public int compare(AbstractNews o1, AbstractNews o2) {
                    var news1Date = ((AbstractNews) o1).getDate();
                    var news2Date = ((AbstractNews) o2).getDate();
                    return news1Date.compareTo(news2Date);
                }
            });
            filteredNews = news;
        } else {
            for (var item : news) {
                if (item.getRubric().getSlug().equals(slug)) {
                    filteredNews.add(item);
                }
            }
        }

        HashSet<AbstractRubric> rubrics = new HashSet<AbstractRubric>();
        for (var n : news) {
            if (n.getRubric().getSlug().equals("all"))
                rubrics.add(n.getRubric());
        }

        List<Integer> pageNumber = new ArrayList<>();
        for (int i = 0; i < filteredNews.size() / AMOUNT_OF_NEWS; i++) {
            pageNumber.add(i);
        }

        if (filteredNews.size() >= 5) {
            List<AbstractNews> result = new ArrayList<>();
            var start = (reqId - 1) * AMOUNT_OF_NEWS;
            var end = reqId * AMOUNT_OF_NEWS;

            for (int i = start; i < end; i++) {
                result.add(filteredNews.get(i));
            }
            model.addAttribute("news", result);
        } else
            model.addAttribute("news", filteredNews);

        model.addAttribute("pNums", pageNumber);
        model.addAttribute("rubrics", rubrics);

        return "home";
    }

}
