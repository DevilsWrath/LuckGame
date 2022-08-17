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

    private String homeTeam;

    private String awayTeam;

    private String matchDate;

    private Float homeTeamRate;

    private Float awayTeamRate;

    private Float drawRate;

    private Boolean isResulted = false;

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

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }


    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Float getHomeTeamRate() {
        return homeTeamRate;
    }

    public void setHomeTeamRate(Float homeTeamRate) {
        this.homeTeamRate = homeTeamRate;
    }

    public Float getAwayTeamRate() {
        return awayTeamRate;
    }

    public void setAwayTeamRate(Float awayTeamRate) {
        this.awayTeamRate = awayTeamRate;
    }

    public Float getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(Float drawRate) {
        this.drawRate = drawRate;
    }

    public boolean isResulted() {
        return isResulted;
    }

    public void setResulted(boolean isResulted) {
        this.isResulted = isResulted;
    }
}
