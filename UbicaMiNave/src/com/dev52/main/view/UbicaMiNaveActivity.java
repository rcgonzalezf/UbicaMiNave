package com.dev52.main.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class UbicaMiNaveActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
 
        final ImageButton imageButton = (ImageButton) findViewById(R.id.ButtonSign);
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //TODO Change current view
                setContentView(R.layout.map_view);
            }
        });
    }
}