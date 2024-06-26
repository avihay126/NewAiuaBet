package com.ashcollege.entities;

import com.ashcollege.utils.Constants;
import com.ashcollege.utils.Persist;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Team<T> {

    private int id;
    private String name;
    @JsonIgnore
    private List<Player> players;
    @JsonIgnore
    private int rating;
    @JsonIgnore
    private int morale;
    @JsonIgnore
    private League league;

    private TeamStatistics teamStatistics;



    public Team() {

    }



    public Team(String name, int rating, int morale, League league) {
        this.name = name;
        this.players = new ArrayList<>();
        this.rating = rating;
        this.morale = morale;
        this.league = league;
        this.teamStatistics = new TeamStatistics();
    }

    public void reInitTeam(Persist persist){
        setPlayers(persist.loadPlayersFromTeam(getId()));
        setTeamStatistics(persist.loadTeamStatistics(getId()));
    }

    public int calcTeamScore(){
        int score = this.rating + this.morale;
        score -= calcNumOfInjuredPlayers()* Constants.INJURY_PENALTY;
        score += (this.teamStatistics.getWins()*Constants.WIN_BONUS);
        if(score<Constants.MIN_SCORE){
            score = Constants.MIN_SCORE;
        }
        if (score > Constants.MAX_SCORE){
            score = Constants.MAX_SCORE;
        }


        return score;
    }

    public void win(){
        this.morale +=Constants.MORALE_WIN_INCREASE;
        if (this.morale>Constants.MAX_MORALE){
            this.morale = Constants.MAX_MORALE;
        }
    }

    public void lose(){
        this.morale -=Constants.MORALE_LOSE_DECREASE;
        if (this.morale<Constants.MIN_MORALE){
            this.morale = Constants.MIN_MORALE;
        }
    }

    public void playersRecovery(){
        for (Player player: this.players) {
            player.setInjured(false);
        }
    }



    public int calcNumOfInjuredPlayers(){
        int injured = 0;
        for (Player player:this.players) {
            if (player.isInjured()){
                injured++;
            }
        }
        return injured;
    }


    public TeamStatistics getTeamStatistics() {
        return teamStatistics;
    }

    public void setTeamStatistics(TeamStatistics teamStatistics) {
        this.teamStatistics = teamStatistics;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
