package com.example.misstank.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {


    Button Next;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        Next = (Button)findViewById(R.id.showgrid);
        context = this;

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, Grid.class);
                startActivity(intent);
            }
        });

    }

}
