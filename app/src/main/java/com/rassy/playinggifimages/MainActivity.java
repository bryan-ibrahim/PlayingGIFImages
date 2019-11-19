package com.rassy.playinggifimages;

import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Drawable drawable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playGIF();
    }

    private void playGIF() {
        ImageView imgGif = findViewById(R.id.imgGif);
        final Button btnStopAnimation = findViewById(R.id.btnStopAnimation);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(getResources(), R.drawable.giphy));
                imgGif.setImageDrawable(drawable);

                //WE CAN APPLY COLOR FILTERS TO THE GIF
//                LightingColorFilter lightingColorFilter = new LightingColorFilter(Color.YELLOW, Color.GREEN);
//                drawable.setColorFilter(lightingColorFilter);

                if (drawable instanceof AnimatedImageDrawable) {
                    ((AnimatedImageDrawable) drawable).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            btnStopAnimation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (drawable instanceof AnimatedImageDrawable) {
                        AnimatedImageDrawable drawable = (AnimatedImageDrawable) MainActivity.this.drawable;
                        if (drawable.isRunning()) {
                            drawable.stop();
                            btnStopAnimation.setText(R.string.start);
                        } else {
                            drawable.start();
                            btnStopAnimation.setText(R.string.stop);
                        }
                    }
                }
            });
        }

    }
}
