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
import android.widget.ImageView;

public class PickCar extends AppCompatActivity {


    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_car);

        context=this;


        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);


        Drawable vectorDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.barcode, null);
        Bitmap bitmap = ((BitmapDrawable)vectorDrawable).getBitmap();

        image1.setImageBitmap(bitmap);
        image2.setImageBitmap(bitmap);
        image3.setImageBitmap(bitmap);
        image4.setImageBitmap(bitmap);


        image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);



            }
        });




    }
}
