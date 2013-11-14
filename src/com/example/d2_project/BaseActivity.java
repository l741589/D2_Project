package com.example.d2_project;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//changeFonts((ViewGroup)findViewById(android.R.id.content), this);
	}
	
	public static void changeFonts(ViewGroup root, Activity act) {    
	       Typeface tf = Typeface.createFromAsset(act.getAssets(),  
	              "msyhbd.ttc");  	    
	       for (int i = 0; i < root.getChildCount(); i++) {  
	           View v = root.getChildAt(i);  
	           if (v instanceof TextView) {  
	              ((TextView) v).setTypeface(tf);  
	           } else if (v instanceof Button) {  
	              ((Button) v).setTypeface(tf);  
	           } else if (v instanceof EditText) {  
	              ((EditText) v).setTypeface(tf);  
	           } else if (v instanceof ViewGroup) {  
	              changeFonts((ViewGroup) v, act);  
	           }  
	       }  
	    
	    }  

}
