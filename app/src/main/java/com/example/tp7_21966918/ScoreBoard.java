package com.example.tp7_21966918;

import org.json.JSONException;
import org.json.JSONObject;

public class ScoreBoard {
private String pseudo;
private int score;
    public static final String strPseudo = "pseudo", strScore = "score";
    public ScoreBoard(String pseudo,int score){
        this.pseudo=pseudo;
        this.score=score;
    }
    public static ScoreBoard jsonToScoreBoard(JSONObject jsonObject) throws JSONException {
        return new ScoreBoard(jsonObject.getString(strPseudo),jsonObject.getInt(strScore));

    }
    @Override
    public String toString(){
        return "Meilleur score : "+ score + " ("+pseudo+")";
    }
}

