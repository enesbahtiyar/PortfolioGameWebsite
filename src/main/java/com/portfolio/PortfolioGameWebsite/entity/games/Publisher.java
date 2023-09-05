package com.portfolio.PortfolioGameWebsite.entity.games;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "Publisher_Table")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Publisher
{
    private String name;
    private String url;
}
