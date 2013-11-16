package com.example.d2_project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.button4).setOnClickListener(this);
		findViewById(R.id.button5).setOnClickListener(this);
		findViewById(R.id.button6).setOnClickListener(this);
		findViewById(R.id.button7).setOnClickListener(this);
		findViewById(R.id.button8).setOnClickListener(this);
		findViewById(R.id.button9).setOnClickListener(this);
		findViewById(R.id.button10).setOnClickListener(this);
		
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setMessage("Here is the body of the agreement").setTitle("Agreement");
		builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MainActivity.this.finish();
			}
		});
		builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);	
		return true;
	}

	@Override
	public void onClick(View v) {
		Class<? extends Activity> target=getTarget(v.getId());
		if (target!=null) startActivity(new Intent(this,target));
	}
	
	public Class<? extends Activity> getTarget(int id){
		switch(id){
		case R.id.button1:return FriendActivity.class;
		case R.id.button2:return NeighborActivity.class;
		case R.id.button3:return StoreActivity.class;
		case R.id.button4:return FriendMapActivity.class;
		case R.id.button5:return AchievementActivity.class;
		case R.id.button6:return MissionActivity.class;
		case R.id.button7:return ParkingActivity.class;
		case R.id.button8:return RankingListActivity.class;
		case R.id.button9:return ReleaseActivity.class;
		case R.id.button10:return UserActivity.class;
		}
		return null;
	}
}
