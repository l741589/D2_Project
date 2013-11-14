package com.example.d2_project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MissionOnMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_on_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission_on_map, menu);
		return true;
	}

}
