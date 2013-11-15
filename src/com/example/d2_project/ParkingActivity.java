package com.example.d2_project;

import com.example.d2_project.data.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class ParkingActivity extends BaseActivity implements OnItemLongClickListener, OnItemClickListener{

	private GridView gv;
	private String userName ="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		gv=(GridView)findViewById(R.id.gridView1);
		gv.setAdapter(new UserGridAdapter(this, getUsers()));
		gv.setOnItemLongClickListener(this);
		gv.setOnItemClickListener(this);
	}

	private User[] getUsers(){
		User[] us=new User[256];
		for (int i=0;i<us.length;++i){
			User u=new User();
			us[i]=u;
			u.face="@drawable/face"+i%254;
			u.name="User"+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			u.isFriend=true;
			u.isFriend=false;
		}
		return us;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parking, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent i=new Intent(this,UserActivity.class);
		i.putExtra("user",(User)parent.getAdapter().getItem(position));
		startActivity(i);		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		final int position = arg2;
		final UserGridAdapter adapter=(UserGridAdapter)arg0.getAdapter();
		final User u=(User)adapter.getItem(position);
		if (u.isPined){ 
			Toast.makeText(ParkingActivity.this, "Failed, you have already ticketed \""+userName+"\".", Toast.LENGTH_SHORT).show();
			return true;
		}
		AlertDialog.Builder builder = new Builder(ParkingActivity.this);
		userName = ((TextView)v.findViewById(R.id.textView1)).getText().toString().trim();
		builder.setMessage("Do you want to write out a ticket to \""+userName+"\"").setTitle("Ticket");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if( !u.isPined) {
					Toast.makeText(ParkingActivity.this, "Success", Toast.LENGTH_SHORT).show();
					u.isPined = true;
					adapter.notifyDataSetChanged();
				}
			}
		});
		builder.setNegativeButton("No", null);
		builder.create().show();		
		return true;
	}

}
