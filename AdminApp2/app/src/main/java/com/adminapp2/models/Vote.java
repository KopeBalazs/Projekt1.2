package com.adminapp2.models;

public class Vote {

    private String id;

    private User user;

    private Question question;

    private int vote;

    public Vote(String id, User user, Question question, int vote) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.vote = vote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
