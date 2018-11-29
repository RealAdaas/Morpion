package com.example.adrien.morpion;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // plateau [colonne][ligne]
    // 0 : case vide
    // 1 : X
    // 2 : O
    private int plateau[][] = new int[3][3];

    // 1 : X
    // 2 : 0
    private int joueurEnCours = 1;

    private TextView tvJoueur;

    private ArrayList<Button> all_buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJoueur = (TextView) findViewById(R.id.joueur);

        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        Button bt4 = (Button) findViewById(R.id.bt4);
        Button bt5 = (Button) findViewById(R.id.bt5);
        Button bt6 = (Button) findViewById(R.id.bt6);
        Button bt7 = (Button) findViewById(R.id.bt7);
        Button bt8 = (Button) findViewById(R.id.bt8);
        Button bt9 = (Button) findViewById(R.id.bt9);

        all_buttons.add(bt1);
        all_buttons.add(bt2);
        all_buttons.add(bt3);
        all_buttons.add(bt4);
        all_buttons.add(bt5);
        all_buttons.add(bt6);
        all_buttons.add(bt7);
        all_buttons.add(bt8);
        all_buttons.add(bt9);

        for (Button bt : all_buttons) {
           bt.setBackground(null);
           bt.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getBackground() != null){
            return;
        }

        switch (view.getId()) {
            case R.id.bt1:
                plateau[0][0] = joueurEnCours;
                break;
            case R.id.bt2:
                plateau[1][0] = joueurEnCours;
                break;
            case R.id.bt3:
                plateau[2][0] = joueurEnCours;
                break;
            case R.id.bt4:
                plateau[0][1] = joueurEnCours;
                break;
            case R.id.bt5:
                plateau[1][1] = joueurEnCours;
                break;
            case R.id.bt6:
                plateau[2][1] = joueurEnCours;
                break;
            case R.id.bt7:
                plateau[0][2] = joueurEnCours;
                break;
            case R.id.bt8:
                plateau[1][2] = joueurEnCours;
                break;
            case R.id.bt9:
                plateau[2][2] = joueurEnCours;
                break;
            default:
                return;
        }

        Drawable drawableJoueur;
        if (joueurEnCours == 1)
            drawableJoueur = ContextCompat.getDrawable(this,R.drawable.x);
        else
            drawableJoueur = ContextCompat.getDrawable(this,R.drawable.o);

        view.setBackground(drawableJoueur);

        if (joueurEnCours == 1){
            joueurEnCours = 2;
            tvJoueur.setText("O");
        }else {
            joueurEnCours = 1;
            tvJoueur.setText("X");
        }

        int res = checkWinner();
        displayAlertDialog(res);
    }

    private int checkWinner (){
        for (int col = 0; col <=2; col++) {
            if (plateau[col][0] != 0 && plateau[col][0] == plateau[col][1] && plateau[col][0] == plateau[col][2]) {
                return plateau[col][0];
            }
        }
        for (int line = 0; line <= 2; line++){
            if (plateau[0][line] != 0 && plateau[0][line] == plateau[1][line] && plateau[0][line] == plateau[2][line])
                return plateau[0][line];
        }

        if(plateau[0][0] != 0 && plateau[0][0] == plateau [1][1] && plateau[0][0] == plateau[2][2]){
            return plateau[0][0];
        }
        if(plateau[2][0] != 0 && plateau[2][0] == plateau [1][1] && plateau[2][0] == plateau[0][2]){
            return plateau[2][0];
        }

        boolean isPlateauPlein = true;
        for (int col = 0; col <= 2; col++){
            for (int line = 0; line <= 2; line++){
                if (plateau[col][line] == 0)
                    isPlateauPlein = false;
            }
            if (!isPlateauPlein)
                break;
        }

        if (isPlateauPlein)
            return 3;

        return 0;
    }


    int pointjoueurX = 0;
    int pointjoueurO = 0;



    private void displayAlertDialog(int res){
        if (res == 0){
            return;
        }

        String strToDisplay = "";
        TextView pointJoueurX = (TextView) findViewById(R.id.pointJoueurX);
        TextView pointJoueurO = (TextView) findViewById(R.id.pointJoueur0);


        if (pointjoueurO <3 || pointjoueurX < 3) {
            if (res == 1) {
                pointjoueurX += 1;
                pointJoueurX.setText(String.valueOf(pointjoueurX));
                if (pointjoueurX == 3)
                    strToDisplay = "Les X ont gagné !";
            }
            if (res == 2){
                pointjoueurO += 1;
                pointJoueurO.setText(String.valueOf(pointjoueurO));
                if (pointjoueurO == 3)
                    strToDisplay = "Les O ont gagné";
            }
            if (res == 3)
                strToDisplay = "Egalité";

        }
        if (pointjoueurO == 3 || pointjoueurX ==3) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Fin de la partie");
            alertDialog.setMessage(strToDisplay);

            alertDialog.setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    resetGame();
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.show();

            pointjoueurO = 0;
            pointjoueurX = 0;

            pointJoueurO.setText("0");
            pointJoueurX.setText("0");
        }
        resetGame();
    }

    private void resetGame(){
        for (int col = 0; col <= 2; col++) {
            for (int line = 0; line <= 2; line++) {
                plateau[col][line] = 0;
            }
        }

        for (Button bt : all_buttons) {
            bt.setBackground(null);
        }

        joueurEnCours = 1;

    }
}


