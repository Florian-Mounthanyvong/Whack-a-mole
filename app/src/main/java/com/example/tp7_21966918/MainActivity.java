package com.example.tp7_21966918;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private TextView scoreView;
    private TextView maxView;
    private RequestQueue queue;
    private TextView bestView;
    public final String baseAddr = "https://lacl.fr/julien.grange/Enseignements/Programmation_mobile/TP/TP7/guacamole.php";

    public TextView getScoreView() {
        return scoreView;
    }

    public TextView getMaxView() {
        return maxView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
       // SharedPreferences sharedPref = context.getSharedPreferences(
             //   getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.layout);
        Taupiniere taupiniere = new Taupiniere(this);
    /*   taupiniere.setLayoutParams(
                new ConstraintLayout.LayoutParams (
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                )
        );*/
        //setContentView(R.layout.activity_main);
        //layout.addView(taupiniere);
        setContentView(taupiniere);

        scoreView = new TextView(this);
        scoreView.setText("Score : 0");
        scoreView.setBackgroundColor(Color.WHITE);

        maxView = new TextView(this);
        maxView.setText("Mon meilleur score: 0");


        bestView = new TextView(this);
        bestView.setText("Meilleur score: 0");

        taupiniere.addView(scoreView);
        taupiniere.addView(maxView);
        //taupiniere.addView(bestView);
        scoreView.setId(View.generateViewId());
        maxView.setId(View.generateViewId());
        bestView.setId(View.generateViewId());


       /*ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        *//*int[] viewIdArray = {scoreView.getId(),maxView.getId(),bestView.getId()};
        float[] weightArray = {0f,0f};
        constraintSet.createHorizontalChain(
                layout.getId(),ConstraintSet.LEFT,
                layout.getId(),ConstraintSet.RIGHT,
                viewIdArray,weightArray,ConstraintSet.CHAIN_SPREAD
        );*//*

        constraintSet.connect(scoreView.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,0);
        constraintSet.connect(scoreView.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT,0);
        constraintSet.connect(maxView.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,0);
        constraintSet.connect(maxView.getId(),ConstraintSet.LEFT,scoreView.getId(),ConstraintSet.RIGHT,0);
        constraintSet.connect(maxView.getId(),ConstraintSet.RIGHT,bestView.getId(),ConstraintSet.LEFT,0);
        constraintSet.connect(bestView.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,0);
        constraintSet.connect(bestView.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT,0);

        *//*constraintSet.connect(taupiniere.getId(),ConstraintSet.TOP,scoreView.getId(),ConstraintSet.BOTTOM,0);
        constraintSet.connect(taupiniere.getId(),ConstraintSet.BOTTOM,layout.getId(),ConstraintSet.BOTTOM,0);
        constraintSet.connect(taupiniere.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT,0);
        constraintSet.connect(taupiniere.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT,0);

        //constraintSet.connect(taupiniere.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT,10);
        //constraintSet.connect(taupiniere.getTaupe().getId(),ConstraintSet.TOP,scoreView.getId(),ConstraintSet.BOTTOM,10);
        //constraintSet.connect(taupiniere.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT,10);*//*
        constraintSet.applyTo(layout);*/

        queue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(baseAddr, arrayListener, arrayErrorListener);
        queue.add(arrayRequest);
    }

    Response.Listener<JSONArray> arrayListener = new Response.Listener<JSONArray>(){
        @Override
        public void onResponse(JSONArray response){
            for(int i = 0; i < response.length(); i++){
                try{
                    ScoreBoard scoreboard = ScoreBoard.jsonToScoreBoard(response.getJSONObject(i));
                    bestView.setText(scoreboard.toString());
                } catch(JSONException e){
                    Toast.makeText(getApplicationContext(), "Error in Parsing", Toast.LENGTH_SHORT).show ();
                    Log.e("parsing","In json parsing");
                }

            }

        }
    };
    Response.ErrorListener arrayErrorListener = new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError response){
            Toast.makeText(getApplicationContext(), "La connection n'a pas pu s'effectuer", Toast.LENGTH_SHORT).show ();
            Log.e("TP6","La connection n'a pas pu s'effectuer");
        }
    };
}