package com.dev52.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class UbicaMiNaveActivity extends Activity {
	
	private static String tag = "UbicaMiNaveActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
 
        final ImageButton imageButton = (ImageButton) findViewById(R.id.ButtonSign);
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(UbicaMiNaveActivity.this, MapViewActivity.class);
            	try{
                startActivity(intent);
            	}catch(Exception e){
            		e.printStackTrace();
            		Log.d(tag, e.getMessage());
            	}
            }
        });
    }
}