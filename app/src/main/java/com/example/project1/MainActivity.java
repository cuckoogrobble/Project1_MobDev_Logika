package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int tries = 0, numberChecked = 0, correctPositions = 0, correctColor = 0, receivedCorrectPositions;
    CheckBox checkA, checkB, checkC, checkD;
    TextView attempts, colorA, colorB, colorC, colorD;
    RadioButton red, green, blue, yellow, cyan, magenta;
    RadioGroup allColors;
    List<Integer> generatedColorList = new ArrayList<>(), generatedColorListWrong = new ArrayList<>();
    List<Integer> userColorList = new ArrayList<>(), userColorListWrong = new ArrayList<>();
    String savedColorA = "", savedColorB = "", savedColorC = "", savedColorD = "";
    static int DEFAULT_COLOR_SIZE = 6, DEFAULT_COMBINATION_SIZE = 4;
    Bundle localState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate", "ON CREATE "+savedInstanceState);

//        if(savedInstanceState != null ){
//            localState = savedInstanceState;
//            System.out.println("THERE WAS INFO HERE");
//            int savedCodeA = savedInstanceState.getInt("ColorCodeA");
//            savedColorA = convertColorCodeInHexa(savedCodeA);
//            colorA.setBackgroundColor(Color.parseColor(savedColorA));
//            int savedCodeB = savedInstanceState.getInt("ColorCodeB");
//            savedColorB = convertColorCodeInHexa(savedCodeB);
//            colorB.setBackgroundColor(Color.parseColor(savedColorB));
//            System.out.println("IM  PRINTING COLOR B IN HEXA: "+savedColorB);
//            int savedCodeC = savedInstanceState.getInt("ColorCodeC");
//            savedColorC = convertColorCodeInHexa(savedCodeC);
//            colorC.setBackgroundColor(Color.parseColor(savedColorC));
//            int savedCodeD = savedInstanceState.getInt("ColorCodeD");
//            savedColorD = convertColorCodeInHexa(savedCodeD);
//            colorD.setBackgroundColor(Color.parseColor(savedColorD));
//        }

        initialSetup();

        Intent receivedIntent = getIntent();
//        String finalToast = receivedIntent.getStringExtra("MESSAGE");
        tries = receivedIntent.getIntExtra("TRIES", 0);
        attempts.setText(""+tries);

            int savedCodeA = receivedIntent.getIntExtra("USER_0", 0);
            savedColorA = convertColorCodeInHexa(savedCodeA);
            System.out.println(" I RECEIVED THIS COLOR IN A: "+savedColorA);
            if (savedColorA != "") {
            colorA.setBackgroundColor(Color.parseColor(savedColorA));
            }
            int savedCodeB = receivedIntent.getIntExtra("USER_1", 0);
            savedColorB = convertColorCodeInHexa(savedCodeB);
        if (savedColorB != "") {
            colorB.setBackgroundColor(Color.parseColor(savedColorB));
        }

            int savedCodeC = receivedIntent.getIntExtra("USER_2", 0);
            savedColorC = convertColorCodeInHexa(savedCodeC);
        if (savedColorC != "") {
            colorC.setBackgroundColor(Color.parseColor(savedColorC));
        }
            int savedCodeD = receivedIntent.getIntExtra("USER_3", 0);
            savedColorD = convertColorCodeInHexa(savedCodeD);
        if (savedColorD != "") {
            colorD.setBackgroundColor(Color.parseColor(savedColorD));
        }

        receivedCorrectPositions = receivedIntent.getIntExtra("FULLY_CORRECT", 0);
        if (receivedCorrectPositions == 4){
            endGame();
        }

        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_0", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_1", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_2", 0));
        generatedColorList.add(receivedIntent.getIntExtra("GENERATED_3", 0));


        if (userColorList.isEmpty()){

        } else {
//            userColorList.add(receivedIntent.getIntExtra("USER_0", 0));
//            userColorList.add(receivedIntent.getIntExtra("USER_1", 0));
//            userColorList.add(receivedIntent.getIntExtra("USER_2", 0));
//            userColorList.add(receivedIntent.getIntExtra("USER_3", 0));
//            colorB.setBackgroundColor(Color.parseColor(convertColorCodeInHexa(userColorList.get(1))));
        }

//        Toast generateToast = Toast.makeText(getApplicationContext(), finalToast, Toast.LENGTH_LONG);
//        generateToast.show();


    }


    public void initialSetup() {

        attempts = (TextView) findViewById(R.id.textView3);

        checkA = (CheckBox) findViewById(R.id.checkBox3);
        checkB = (CheckBox) findViewById(R.id.checkBox);
        checkC = (CheckBox) findViewById(R.id.checkBox4);
        checkD = (CheckBox) findViewById(R.id.checkBox5);


        colorA = (TextView) findViewById(R.id.textView7);
        colorB = (TextView) findViewById(R.id.textView11);
        colorC = (TextView) findViewById(R.id.textView12);
        colorD = (TextView) findViewById(R.id.textView13);

        red = (RadioButton)findViewById(R.id.radioButton);
        green = (RadioButton)findViewById(R.id.radioButton7);
        blue = (RadioButton)findViewById(R.id.radioButton9);
        yellow = (RadioButton)findViewById(R.id.radioButton10);
        cyan = (RadioButton)findViewById(R.id.radioButton11);
        magenta = (RadioButton)findViewById(R.id.radioButton12);


    }



    public String extractingTheColor(int letter){
        String color  = null;
        if (letter == 0) color="#ff4d4d";   //0 is red
        if (letter == 1) color="#a6ff4d";   //1 is green
        if (letter == 2) color="#4d4dff";   //2 is blue
        if (letter == 3) color="#ffff4d";   //3 is yellow
        if (letter == 4) color="#81e0ff";   //4 is cyan
        if (letter == 5) color="#ff4dd3";   //5 is magenta
        return color;
    }

    public void enableRadioBoxes() {
        red.setEnabled(true);
        blue.setEnabled(true);
        green.setEnabled(true);
        yellow.setEnabled(true);
        cyan.setEnabled(true);
        magenta.setEnabled(true);
    }

    public void disableRadioBoxes(){
        red.setEnabled(false);
        blue.setEnabled(false);
        green.setEnabled(false);
        yellow.setEnabled(false);
        cyan.setEnabled(false);
        magenta.setEnabled(false);
    }

    public void radioBoxesToUncheck(){
       allColors = (RadioGroup)findViewById(R.id.radioGroup);
       allColors.clearCheck();
    }


    public void radioRedOnChecked(View v) {
    String color = extractingTheColor(0);
    if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
    if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
    if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
    if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));

    }

    public void radioGreenOnChecked(View v) {
        String color = extractingTheColor(1);
        if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
        if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
        if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
        if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));
    }

    public void radioBlueOnChecked(View v) {
        String color = extractingTheColor(2);
        if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
        if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
        if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
        if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));
    }

    public void radioYellowOnChecked(View v) {
        String color = extractingTheColor(3);
        if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
        if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
        if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
        if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));
    }

    public void radioCyanOnChecked(View v) {
        String color = extractingTheColor(4);
        if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
        if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
        if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
        if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));
    }

    public void radioMagentaOnChecked(View v) {
        String color = extractingTheColor(5);
        if (checkA.isChecked()) colorA.setBackgroundColor(Color.parseColor(color));
        if (checkB.isChecked()) colorB.setBackgroundColor(Color.parseColor(color));
        if (checkC.isChecked()) colorC.setBackgroundColor(Color.parseColor(color));
        if (checkD.isChecked()) colorD.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "ON RESUME count: " + tries);
        //colorA.setBackgroundColor(Color.parseColor(savedColorA));
        //System.out.println("HA! I SAVED COLOR A, and this is: "+savedColorA);.
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "ON STOP count: " + tries);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        Log.d("onSave", "!!!!!!!!!!!!!!!MAIN ACTIVITY ON SAVE!!!!!!!!: ");

        outState.putInt("myTries", tries);

        outState.putInt("colorCodeA", userColorList.get(0));
        Log.d("onSave colorCodeA", "ON SAVE COLOR CODE A: " + userColorList.get(0));
        outState.putInt("colorCodeB", userColorList.get(1));
        Log.d("onSave colorCodeB", "ON SAVE COLOR CODE B: " + userColorList.get(1));
        outState.putInt("colorCodeC", userColorList.get(2));
        Log.d("onSave colorCodeC", "ON SAVE COLOR CODE C: " + userColorList.get(2));
        outState.putInt("colorCodeD", userColorList.get(3));
        Log.d("onSave colorCodeD", "ON SAVE COLOR CODE D: " + userColorList.get(3));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("onRestore", "!!!!!!!!!!!!!!!MAIN ACTIVITY ON RESTORE!!!!!!!!: ");

        int myTries = savedInstanceState.getInt("myTries");
        Log.d("onRestore", "ON RESTORE count: " + myTries);
        int colorCodeA = savedInstanceState.getInt("colorCodeA");
        Log.d("onRestore ColorCodeA", "ON RESTORE COLOR CODE A: " + colorCodeA);
        colorA.setBackgroundColor(Color.parseColor(convertColorCodeInHexa(colorCodeA)));
        int colorCodeB = savedInstanceState.getInt("colorCodeB");
        Log.d("onRestore ColorCodeB", "ON RESTORE COLOR CODE B: " + colorCodeB);
        int colorCodeC = savedInstanceState.getInt("colorCodeC");
        Log.d("onRestore ColorCodeC", "ON RESTORE COLOR CODE C: " + colorCodeC);
        int colorCodeD = savedInstanceState.getInt("colorCodeA");
        Log.d("onRestore ColorCodeD", "ON RESTORE COLOR CODE D: " + colorCodeD);

    }

    public void generateButton(View v) {

        List<Integer> gettingNumbers = gettingARandomListOfNumbers();
        generatedColorList.clear();
        tries = 0;
        attempts.setText(""+tries);

        for (int i=0; i < gettingNumbers.size() ; i++) generatedColorList.add(whatColorIs(gettingNumbers.get(i)));

        //printing to be sure
        System.out.println("Generated Color Map just like thaaaat, whooosh: ");
        System.out.println(generatedColorList);

        Toast generateToast = Toast.makeText(getApplicationContext(), "New Color Combination was generated", Toast.LENGTH_LONG);
        generateToast.show();
    }

    public List<Integer> gettingARandomListOfNumbers(){
        List<Integer> allTheNumbers = new ArrayList<>(), futureListOfColors = new ArrayList<>();
        for (int i=0; i < DEFAULT_COLOR_SIZE; i++) allTheNumbers.add(i);
        for (int i=0; i < DEFAULT_COMBINATION_SIZE; i++) {
            Collections.shuffle(allTheNumbers);
            futureListOfColors.add(allTheNumbers.get(i));
        }

        return futureListOfColors;
    }

    public void checkButton(View v) {
        //check if there was a color generated already
        if (generatedColorList.isEmpty()) {
            Toast noGColorMap = Toast.makeText(getApplicationContext(), "Please generate a Combination of Colors first", Toast.LENGTH_SHORT);
            noGColorMap.show();
        }  else if (!isAnyBoxGrey()) {  //Color was already generated AND user also gave every box a color

            attempts.setText(""+tries);

            System.out.println("Generated Color Map: ");
            System.out.println(generatedColorList);

            //PRINTING USER COLOR LIST
            System.out.println("USER COLOR MAP: ");
            System.out.println(userColorList);


            tries++;
            generateWrongLists();
            correctPositions = checkPositions();
            //printing
            System.out.println(correctPositions+" pin(s) are fully correct");

            checkColors();

            System.out.println((correctColor)+" pin(s) have correct color, but wrong position.");

            Intent myIntent = new Intent(v.getContext(), SecondActivity.class);
            myIntent.putExtra("FULLY_CORRECT", correctPositions);
            myIntent.putExtra("HALF_CORRECT", correctColor);
            myIntent.putExtra("TRIES", tries);
            myIntent.putExtra("GENERATED_0", generatedColorList.get(0));
            myIntent.putExtra("GENERATED_1", generatedColorList.get(1));
            myIntent.putExtra("GENERATED_2", generatedColorList.get(2));
            myIntent.putExtra("GENERATED_3", generatedColorList.get(3));
            myIntent.putExtra("USER_0", userColorList.get(0));
            myIntent.putExtra("USER_1", userColorList.get(1));
            myIntent.putExtra("USER_2", userColorList.get(2));
            myIntent.putExtra("USER_3", userColorList.get(3));
            v.getContext().startActivity(myIntent);


        } else {
           Toast keepTryingToast = Toast.makeText(getApplicationContext(), "Dude, every box has to have a color", Toast.LENGTH_SHORT);
           keepTryingToast.show();
        }
    }

    public void checkButtonA(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        if (checked) {
            enableRadioBoxes();
            numberChecked++;
        } else {
            numberChecked--;
            checkIfAllUncheck();
            radioBoxesToUncheck();
        }
    }

    public void checkButtonB(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        if (checked) {
            enableRadioBoxes();
            numberChecked++;

        } else {
            numberChecked--;
            radioBoxesToUncheck();
            checkIfAllUncheck();
        }
    }

    public void checkButtonC(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        if (checked) {
            enableRadioBoxes();
            numberChecked++;

        } else {
            numberChecked--;
            checkIfAllUncheck();
            radioBoxesToUncheck();
        }
    }

    public void checkButtonD(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        if (checked) {
            enableRadioBoxes();
            numberChecked++;

        } else {
            numberChecked--;
            checkIfAllUncheck();
            radioBoxesToUncheck();

        }
    }

    public void checkIfAllUncheck(){
        if (numberChecked == 0) {
            disableRadioBoxes();
            Toast checkToast = Toast.makeText(getApplicationContext(), "nothing is checked", Toast.LENGTH_SHORT);
            checkToast.show();
        }
    }

    public Boolean isAnyBoxGrey(){
        return  (isAGrey() || isBGrey() || isCGrey() || isDGrey());

    }

    public Boolean isAGrey() {
        int colorCodeA = boxAHasTheColor();
        return (colorCodeA == -2697257);
    }

    public Boolean isBGrey() {
        int colorCodeB = boxBHasTheColor();
        return (colorCodeB == -2697257);
    }

    public Boolean isCGrey() {
        int colorCodeC = boxCHasTheColor();
        return (colorCodeC == -2697257);
    }

    public Boolean isDGrey() {
        int colorCodeD = boxDHasTheColor();
        return (colorCodeD == -2697257);
    }

    public int boxAHasTheColor(){
        ColorDrawable cdA = (ColorDrawable) colorA.getBackground();
        int colorCodeA = cdA.getColor();
        Log.d("colorPrintA", "A has the following color: " + colorCodeA);
        userColorList.add(colorCodeA);
        return colorCodeA;
    }

    public int boxBHasTheColor(){
        ColorDrawable cdB = (ColorDrawable) colorB.getBackground();
        int colorCodeB = cdB.getColor();
        Log.d("colorPrintB", "B has the following color: " + colorCodeB);
        userColorList.add(colorCodeB);
        return colorCodeB;
    }

    public int boxCHasTheColor(){  //need to make it sequential
        ColorDrawable cdC = (ColorDrawable) colorC.getBackground();
        int colorCodeC = cdC.getColor();
        Log.d("colorPrintC", "C has the following color: " + colorCodeC);
        userColorList.add(colorCodeC);
        return colorCodeC;
    }

    public int boxDHasTheColor(){
        ColorDrawable cdD = (ColorDrawable) colorD.getBackground();
        int colorCodeD = cdD.getColor();
        Log.d("colorPrintD", "D has the following color: " + colorCodeD);
        userColorList.add(colorCodeD);
        return colorCodeD;
    }

    public int whatColorIs(int number){
        if (number == 0) {System.out.println("0 is red and its integer is -45747"); return -45747;}
        if (number == 1) {System.out.println("1 is green and its integer is -5832883"); return -5832883;}
        if (number == 2) {System.out.println("2 is blue and its integer is -11710977"); return -11710977;}
        if (number == 3) {System.out.println("3 is yellow and its integer is -179"); return -179;}
        if (number == 4) {System.out.println("4 is cyan and its integer is -8265473"); return -8265473;}
        if (number == 5)  System.out.println("5 is magenta and its integer is -45613"); return -45613;
    }

    public void checkColors() {
        if (correctPositions == 4 || generatedColorListWrong.isEmpty()){
        }else {
//            generatedColorListWrong.retainAll(userColorListWrong);
//            correctColor = generatedColorListWrong.size();

            for(int i=0; i <generatedColorListWrong.size(); i++) {
                for(int j = 0; j < generatedColorListWrong.size(); j++){
                    if (generatedColorListWrong.get(i).equals(userColorListWrong.get(j))){
                        correctColor++;
                        generatedColorListWrong.remove(i);
                        userColorListWrong.remove(j);
                        System.out.println("COMPLETE WRONG USER LIST SO FAR: \n"+userColorListWrong);
                        checkColors();
                    }
                }
            }
        }
    }

    public int checkPositions(){
        for (int i = userColorList.size()-1; i > -1; i--) {
                if (generatedColorList.get(i).equals(userColorList.get(i)) ) {
                    correctPositions++;
                    if (correctPositions != 4) {
                        userColorListWrong.remove(i);
                        System.out.println("USER COLOR WRONG LIST: "+userColorListWrong);
                        generatedColorListWrong.remove(i);
                    }

                }
            }
        return correctPositions;
    }

    public void generateWrongLists(){
        for (int i = 0; i < userColorList.size(); i++) {
            userColorListWrong.add(userColorList.get(i));
            generatedColorListWrong.add(generatedColorList.get(i));
        }
    }

    public String convertColorCodeInHexa(int colorCode){
        String hexaColor = "";
        if (colorCode == -45747) hexaColor = "#ff4d4d";
        if (colorCode == -5832883) hexaColor = "#a6ff4d";
        if (colorCode == -11710977) hexaColor = "#4d4dff";
        if (colorCode == -179) hexaColor = "#ffff4d";
        if (colorCode == -8265473) hexaColor = "#81e0ff";
        if (colorCode == -45613) hexaColor = "#ff4dd3";

        return hexaColor;
    }

    public void endGame(){

        //TOAST CONGRATS with number of attempts
        //

        Toast endGameToast = Toast.makeText(getApplicationContext(), "CONGRATULATIONS!! You solved the game with "+tries+" attempts", Toast.LENGTH_LONG);
        endGameToast.show();

    }
}

