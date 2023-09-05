package com.portfolio.PortfolioGameWebsite.entity.games;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "RatingOfTheGames")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AggregateRating
{
    private String bestRating;
    private String ratingValue;
    private String ratingCount;
    private String worstRating;
}
