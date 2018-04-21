package ca.georgebrown.game2011.mymathgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        Toast.makeText(context, "onCreate completed", Toast.LENGTH_LONG).show();

        Button playButtonVariable = (Button) findViewById(R.id.playButton);
        PlayButtonListener playListenerVariable = new PlayButtonListener();
        playButtonVariable.setOnClickListener(playListenerVariable);

        Button quit = (Button) findViewById(R.id.quitButton);
        ButtonListener quitListener = new ButtonListener();
        quit.setOnClickListener(quitListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        Toast.makeText(context, "onStart completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        Toast.makeText(context, "onResume completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Context context = getApplicationContext();
        Toast.makeText(context, "onPause completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Context context = getApplicationContext();
        Toast.makeText(context, "onStop completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Context context = getApplicationContext();
        Toast.makeText(context, "onDestroy completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Context context = getApplicationContext();
        Toast.makeText(context, "onRestart completed", Toast.LENGTH_LONG).show();
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private void startPlay() {
        Intent intentInstance = new Intent(this, GameActivity.class);
        startActivity(intentInstance);
    }

    private class PlayButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startPlay();
        }
    }
}
