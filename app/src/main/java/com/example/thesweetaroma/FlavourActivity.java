package com.example.thesweetaroma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class FlavourActivity extends AppCompatActivity {

       ImageButton btn_BackMenu;
       Button btn_flavour;
       TextView flovour;
       CheckBox Butter,Chocolate,Vanila,RedVelvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flavour);

        btn_BackMenu = findViewById(R.id.btn_BackMenu);
        btn_flavour = findViewById(R.id.btn_flavour);
        flovour = findViewById(R.id.flavour);
        Butter = findViewById(R.id.Butter);
        Chocolate = findViewById(R.id.Chocolate);
        Vanila = findViewById(R.id.Vanila);
        RedVelvet = findViewById(R.id.RedVelvet);




        btn_BackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlavourActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btn_flavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlavourActivity.this, ShapeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



    }
}