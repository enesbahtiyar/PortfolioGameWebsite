package com.portfolio.PortfolioGameWebsite.entity.games;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "Games_Table")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Games
{
    private String image;
    private String description;
    private String operatingSystem;
    private String url;
    private String datePublished;
    private String name;
    private ArrayList<String> genre;

    @OneToMany
    private ArrayList<Publisher> publisher;
    private String contentRating;
    private String gamePlatform;

    @OneToOne
    private AggregateRating aggregateRating;
}
