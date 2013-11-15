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
	private boolean[] isPined;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		gv=(GridView)findViewById(R.id.gridView1);
		gv.setAdapter(new UserGridAdapter(this, getUsers()));
		gv.setOnItemLongClickListener(this);
		gv.setOnItemClickListener(this);
		
		for(int i = 0; i < isPined.length; i++) {
			isPined[i] = false;
		}
	}

	private User[] getUsers(){
		User[] us=new User[256];
		isPined=new boolean[us.length];
		for (int i=0;i<us.length;++i){
			User u=new User();
			us[i]=u;
			u.face="@drawable/face"+i%254;
			u.name="用户"+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			u.isFriend=true;
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
		AlertDialog.Builder builder = new Builder(ParkingActivity.this);
		userName = ((TextView)v.findViewById(R.id.textView1)).getText().toString().trim();
		builder.setMessage("你是否要给 "+userName+" 贴罚条").setTitle("贴罚条");
		builder.setPositiveButton("贴条", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if( !isPined[position] ) {
					Toast.makeText(ParkingActivity.this, "你已经成功给 "+userName+" 贴条", Toast.LENGTH_SHORT).show();
					isPined[position] = true;
				}else {
					Toast.makeText(ParkingActivity.this, "贴条失败，你已经给 "+userName+" 贴过条了", Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
		
		return true;
	}

}
