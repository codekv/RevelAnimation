package com.example.revelanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCirculrRevelAnimation(click);
            }
        });
    }

    private void makeCirculrRevelAnimation(View view) {
        final TextView text1= findViewById(R.id.text1);

        int centerX=(view.getLeft()+view.getRight())/2;
        int centerY=(view.getTop()+view.getBottom())/2;

        float radius =Math.max(text1.getWidth(),text1.getHeight()) *2.0f;

        if (text1.getVisibility()==view.INVISIBLE){
            text1.setVisibility(view.VISIBLE);
            text1.setText("Hello Friends, \n Follow For " +
                    "More Content ");

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                ViewAnimationUtils.createCircularReveal(text1,centerX,centerY,0,radius).start();

            }
            else {
                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                    Animator revel =ViewAnimationUtils.createCircularReveal(text1,centerX,centerY,radius,0);
                    revel.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            text1.setVisibility(View.INVISIBLE);
                        }
                    });
                    revel.start();
                }
            }
        }
    }
}