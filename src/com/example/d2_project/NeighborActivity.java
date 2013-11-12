package com.example.d2_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.d2_project.data.User;

public class NeighborActivity extends BaseActivity implements OnItemClickListener,OnItemLongClickListener,OnTouchListener{
	private GridView gv;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neighbor);
		gv=(GridView)findViewById(R.id.gridView1);
		gv.setAdapter(new UserGridAdapter(this,getUsers()));
		tv=(TextView)findViewById(R.id.textView1);
		gv.setOnItemLongClickListener(this);
		gv.setOnTouchListener(this);
		gv.setOnItemClickListener(this);
	}
	
	private User[] getUsers(){
		User[] us=new User[10];
		for (int i=0;i<us.length;++i){
			User u=new User();
			us[i]=u;
			u.face="@drawable/face"+(int)(Math.random()*254);
			u.name="用户"+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			if (Math.random()<0.1) u.isFriend=true; else u.isFriend=false;
		}
		return us;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		tv.setText("与用户 "+((TextView)v.findViewById(R.id.textView1)).getText().toString().trim()+" 通讯中");
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:			
			return false;
		case MotionEvent.ACTION_UP:
			tv.setText("附近的用户");
			return false;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		Intent i=new Intent(this,UserActivity.class);
		i.putExtra("user",(User)parent.getAdapter().getItem(position));
		startActivity(i);		
	}
}
