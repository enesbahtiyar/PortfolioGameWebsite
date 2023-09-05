package com.portfolio.PortfolioGameWebsite.Scrapping;

import com.portfolio.PortfolioGameWebsite.entity.games.Games;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetaCriticScrapper
{
    public static void main(String[] args) throws ParseException {
        String url = "https://www.metacritic.com/browse/games/score/metascore/all/all/filtered";
        Document doc;

        try
        {
            // fetching the target website
            doc = Jsoup.connect(url).
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").
                    header("Accept-Language", "*").
                    get();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        List<Games> gamesList = new ArrayList<>();
        Elements tables = doc.select("table");
        String string = tables.toString();
        String[] stringArray = string.split("<tr>");

        for(String w: stringArray)
        {
            Games games = new Games();
            Document document = Jsoup.parse(w);


            try {
                games.setUrl(document.selectFirst("a").attr("href"));
            }catch (NullPointerException e)
            {
                System.out.println("bum");
            }
            if(games.getUrl() != null)
            {
                goToGamePageAndRetrieveData(games);
            }
        }
    }

    private static void goToGamePageAndRetrieveData(Games games) throws ParseException
    {
        String metaCriticUrl = "https://www.metacritic.com/";
        String gamePageUrl = metaCriticUrl + games.getUrl();
        Document document;

        try
        {
            // fetching the target website
            document = Jsoup.connect(gamePageUrl).
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").
                    header("Accept-Language", "*").
                    get();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Elements data = document.select("script[type=application/ld+json]");
        List<DataNode> dataNodes = data.dataNodes();

        for (DataNode dataNode: dataNodes)
        {
            String w = dataNode.toString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(w);
            JSONArray publisher = (JSONArray) json.get("publisher");

            //
            for (Object publisherInfo: publisher)
            {
                JSONObject a = (JSONObject) publisherInfo;
                System.out.println(a.get("name"));
            }
        }
    }
}
