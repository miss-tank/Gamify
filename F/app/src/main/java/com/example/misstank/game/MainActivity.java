package com.example.misstank.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView barcode;
    Context context;
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;
        barcode= (ImageView) findViewById(R.id.Barcode);

        Drawable vectorDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.barcode, null);
        Bitmap bitmap = ((BitmapDrawable)vectorDrawable).getBitmap();

        barcode.setImageBitmap(bitmap);

        Next = (Button)findViewById(R.id.next);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, WelcomeScreen.class);
                startActivity(intent);

            }
        });


    }

}
