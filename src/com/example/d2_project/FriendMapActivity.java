package com.example.d2_project;

import com.example.d2_project.data.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FriendMapActivity extends BaseActivity {
	
	User u;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_roadmasked);
		Intent intent=getIntent();
		//u=(User)intent.getExtras().getSerializable("user");
		tv=(TextView)findViewById(R.id.textView1);
		tv.setText("µØÍ¼");
	}
}
