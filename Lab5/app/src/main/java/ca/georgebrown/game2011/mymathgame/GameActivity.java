package ca.georgebrown.game2011.mymathgame;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Game(this));
    }
}
