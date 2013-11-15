package com.example.d2_project;

import com.example.d2_project.data.Achievement;
import com.example.d2_project.data.Mission;
import com.example.d2_project.data.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MissionActivity extends BaseActivity implements OnClickListener, OnGestureListener, OnTouchListener, OnItemClickListener {

	private TextView tv1;
	private TextView tv2;
	private ViewFlipper vf;
	private ListView lv1;
	private ListView lv2;
	private GestureDetector gd;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission);
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv1.setTextColor(getResources().getColor(R.color.title_selected));
		vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
		lv1 = (ListView) findViewById(R.id.listView1);
		lv2 = (ListView) findViewById(R.id.listView2);

		lv1.setAdapter(new MissionAdapter(this, getMissions()));
		lv2.setAdapter(new MissionAdapter(this, getMissions()));
		
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		lv1.setOnTouchListener(this);
		lv2.setOnTouchListener(this);
		lv1.setOnItemClickListener(this);
		lv2.setOnItemClickListener(this);
		
		gd=new GestureDetector(this, this);
		
	}

	private Mission[] getMissions() {
		Mission[] ret=new Mission[30];
		for (int i=0;i<ret.length;++i){
			Mission m=new Mission();
			m.icon=String.format("@drawable/icon%d",(int)(Math.random()*42));
			m.name="任务"+i;
			m.description = "我是 "+m.name+" 的描述信息";
			ret[i] = m;
		}
		return ret;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.textView1:
			show1();
			break;
		case R.id.textView2:
			show2();
			break;

		default:
			break;
		}
	}

	private void show1() {
		// TODO Auto-generated method stub
		if (vf.getCurrentView()==lv1) return;
		vf.setInAnimation(getApplicationContext(), R.anim.push_right_in);   
		vf.setOutAnimation(getApplicationContext(), R.anim.push_right_out);  
		vf.showNext();
		tv1.setTextColor(getResources().getColor(R.color.title_selected));
		tv2.setTextColor(getResources().getColor(R.color.title));
	}

	private void show2() {
		// TODO Auto-generated method stub
		if (vf.getCurrentView()==lv2) return;
		vf.setInAnimation(getApplicationContext(), R.anim.push_left_in);   
		vf.setOutAnimation(getApplicationContext(), R.anim.push_left_out);		
		vf.showPrevious();
		tv1.setTextColor(getResources().getColor(R.color.title));
		tv2.setTextColor(getResources().getColor(R.color.title_selected));	
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if (e1.getX()-e2.getX()>400){
			show2();
			return true;
		}else if (e2.getX()-e1.getX()>400){
			show1();	
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return  gd.onTouchEvent(arg1);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		Intent i=new Intent(this,MissionOnMapActivity.class);
//		i.putExtra("mission",(Mission)parent.getAdapter().getItem(position));
		startActivity(i);
	}
	
}
