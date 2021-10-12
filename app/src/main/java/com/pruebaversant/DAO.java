package com.pruebaversant;

public class DAO {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCefr() {
        return cefr;
    }

    public void setCefr(String cefr) {
        this.cefr = cefr;
    }

    public String getGse() {
        return gse;
    }

    public void setGse(String gse) {
        this.gse = gse;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    String email, score, cefr, gse;
    long time;
}
