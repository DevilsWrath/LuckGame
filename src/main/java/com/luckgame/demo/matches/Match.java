package com.luckgame.demo.matches;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table
@Data
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long matchId;

    private String matchName;

    private String matchDate;

    private Float homeTeamRate;

    private Float awayTeamRate;

    private Float drawRate;

    public Match(String matchName, String matchDate, Float homeTeamRate, Float awayTeamRate, Float drawRate) {
        this.matchName = matchName;
        this.matchDate = matchDate;
        this.homeTeamRate = homeTeamRate;
        this.awayTeamRate = awayTeamRate;
        this.drawRate = drawRate;
    }

    public Match(Long matchId, String matchName, String matchDate, Float homeTeamRate, Float awayTeamRate, Float drawRate) {
        this.matchId = matchId;
        this.matchName = matchName;
        this.matchDate = matchDate;
        this.homeTeamRate = homeTeamRate;
        this.awayTeamRate = awayTeamRate;
        this.drawRate = drawRate;
    }


}
