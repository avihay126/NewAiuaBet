package com.ashcollege.entities;

import com.ashcollege.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User<T> {
    private int id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String secret;
    private double balance;
    @JsonIgnore
    private List<BetsForm> betsForms;

    public User() {

    }

    public User(String username, String email, String password){
        Faker faker = new Faker();
        this.secret = faker.lorem().characters(Constants.SECRET_LEN);
        this.username =username;
        this.email = email;
        this.password = password;
        this.balance = Constants.JOINING_GIFT;
        this.betsForms = new ArrayList<>();
    }

    public static boolean isCorrectUsername(String username) {
        return username.length() >= Constants.USERNAME_MIN_LEN && isFirstCharUppercase(username);
    }
    public void takeABet(int moneyBet){
        this.setBalance(this.balance-moneyBet);
    }

    public void wonABet(int moneyBet, float ratio){
        this.setBalance(this.balance +  (moneyBet*ratio));
    }




    public static boolean isCorrectEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isCorrectPassword(String password) {
        return password.length() >= Constants.PASSWORD_MIN_LEN && isFirstCharUppercase(password) && containsDigit(password);
    }

    private static boolean isFirstCharUppercase(String str) {
        String regex = "^[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    private static boolean containsDigit(String str) {
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    public static boolean areInputsCorrect(String username, String email, String password) {
        return isCorrectUsername(username) && isCorrectEmail(email) && isCorrectPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<BetsForm> getBets() {
        return betsForms;
    }

    public void setBets(List<BetsForm> betsForms) {
        this.betsForms = betsForms;
    }
}

