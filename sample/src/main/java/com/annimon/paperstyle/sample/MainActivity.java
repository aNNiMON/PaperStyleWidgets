package com.annimon.paperstyle.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import java.util.Random;
import com.annimon.paperstyle.PaperButton;
import com.annimon.paperstyle.PaperProgressBar;
import com.annimon.paperstyle.PaperSeekBar;

public class MainActivity extends Activity {

    private static final Random RND = new Random();
    private static float[] lastHsvColor = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomColor(lastHsvColor);

        final PaperProgressBar progressBar = (PaperProgressBar) findViewById(R.id.progressbar);

        final PaperButton randomButton = (PaperButton) findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                randomButton.setBackgroundColor(Color.HSVToColor(lastHsvColor));

                final float[] hsv = new float[] {
                        0f, 0f,
                        (lastHsvColor[2] > 0.5f) ? 0f : 1f
                };
                randomButton.setTextColor(Color.HSVToColor(hsv));

                randomColor(hsv);
                lastHsvColor = hsv;
                randomButton.setFocusColor(Color.HSVToColor(hsv));

                progressBar.setSecondaryProgress(RND.nextInt(progressBar.getMax()));
                progressBar.setSecondaryProgressColor(Color.HSVToColor(hsv));
            }
        });

        final PaperSeekBar hueSeekBar = (PaperSeekBar) findViewById(R.id.hue_seekbar);
        hueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int color = Color.HSVToColor(new float[] { progress, 1f, 0.8f });
                hueSeekBar.setColor(color);
                progressBar.setProgressColor(color);
                progressBar.setIndeterminate(progress == 360);
                progressBar.setProgress(progress);
            }
        });
    }

    private void randomColor(float[] hsv) {
        hsv[0] = RND.nextInt(360);
        hsv[1] = RND.nextFloat();
        hsv[2] = RND.nextFloat();
    }
}
