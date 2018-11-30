package com.example.adrien.morpion;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PageUneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageune);

        final EditText nameJoueur1;
        final EditText nameJoueur2;
        final Button buttonLancer;

        buttonLancer = findViewById(R.id.buttonLancer);

        nameJoueur1 = findViewById(R.id.nomJoueur1);
        nameJoueur2 = findViewById(R.id.nomJoueur2);

        nameJoueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameJoueur1.setText(nameJoueur1.getText());
            }
        });

        nameJoueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameJoueur2.setText(nameJoueur2.getText());
            }
        });

        buttonLancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameeJoueur1 = nameJoueur1.getText().toString();
                String nameeJoueur2 = nameJoueur2.getText().toString();

                Player player1 = new Player(nameeJoueur1);
                Player player2 = new Player(nameeJoueur2);

                Intent intent = new Intent(buttonLancer.getContext(), MainActivity.class);
                intent.putExtra("Joueur1", player1);
                intent.putExtra("Joueur2", player2);

                startActivity(intent);
            }
        });

    }



}


