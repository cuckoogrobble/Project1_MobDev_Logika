package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView boxResult0, boxResult1, boxResult2, boxResult3, fullyCorrect, halfCorrect;
    HashMap<Integer, TextView> resultBoxesMap = new HashMap<>();
    int correctPosition = 0, correctColor = 0, tries = 0;
    String congratulationsMessage = "";
    List<Integer> generatedColorList = new ArrayList<>(), userColorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialSetup();
        generateResultBoxesHash();
        Intent receivedIntent = getIntent();
        correctPosition = receivedIntent.getIntExtra("FULLY_CORRECT", 0);
//        System.out.println("I received this as fully correct: "+correctPosition);
        correctColor = receivedIntent.getIntExtra("HALF_CORRECT", 0);
//        System.out.println("I received this as half correct: "+correctColor);
        tries = receivedIntent.getIntExtra("TRIES", 0);
        userColorList.add(receivedIntent.getIntExtra("USER_0", 0));
        userColorList.add(receivedIntent.getIntExtra("USER_1", 0));
        System.out.println("I RECEIVED THIS COLOR B: "+userColorList.get(1));
        userColorList.add(receivedIntent.getIntExtra("USER_2", 0));
        userColorList.add(receivedIntent.getIntExtra("USER_3", 0));
        System.out.println("I RECEIVED THIS TRIES: "+tries);

        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_0", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_1", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_2", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_3", 0));

        paintBoxes();
        setResultText();

    }

    public void returnOnClick(View v){

        if (correctPosition ==4){
            congratulationsMessage = "CONGRATULATIONS, YOU HAVE WON!";
            Intent myIntent = new Intent(v.getContext(), MainActivity.class);
            myIntent.putExtra("MESSAGE", congratulationsMessage);
            v.getContext().startActivity(myIntent);
        }
        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
        myIntent.putExtra("TRIES", tries);

        myIntent.putExtra("USER_0", userColorList.get(0));
        myIntent.putExtra("USER_1", userColorList.get(1));
        myIntent.putExtra("USER_2", userColorList.get(2));
        myIntent.putExtra("USER_3", userColorList.get(3));

        myIntent.putExtra("GENERATED_0", generatedColorList.get(0));
        myIntent.putExtra("GENERATED_1", generatedColorList.get(1));
        myIntent.putExtra("GENERATED_2", generatedColorList.get(2));
        myIntent.putExtra("GENERATED_3", generatedColorList.get(3));

        myIntent.putExtra("FULLY_CORRECT", correctPosition);

        v.getContext().startActivity(myIntent);
    }

    public void initialSetup() {

        fullyCorrect = (TextView) findViewById(R.id.textView17);
        halfCorrect = (TextView) findViewById(R.id.textView18);

        boxResult0 = (TextView) findViewById(R.id.textView16);
        boxResult1 = (TextView) findViewById(R.id.textView15);
        boxResult2 = (TextView) findViewById(R.id.textView10);
        boxResult3= (TextView) findViewById(R.id.textView5);
    }

    public void generateResultBoxesHash(){
        resultBoxesMap.put(0, boxResult0);
        resultBoxesMap.put(1, boxResult1);
        resultBoxesMap.put(2, boxResult2);
        resultBoxesMap.put(3, boxResult3);
    }

    public void paintBoxes(){

        //painting black if its the case
        for (int i = 0; i < correctPosition; i++) resultBoxesMap.get(i).setBackgroundColor(Color.parseColor("#000000"));


        //painting white, if its the case
        for (int i = correctPosition; i < correctPosition+correctColor; i++)resultBoxesMap.get(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    public void setResultText(){
        fullyCorrect.setText(correctPosition+" pin(s) are fully correct.");
        halfCorrect.setText(correctColor+" pin(s) have correct color, but wrong position");
    }
}
