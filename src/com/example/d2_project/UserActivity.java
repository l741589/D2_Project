package com.example.d2_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.d2_project.data.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends BaseActivity{
	private TextView tv_name;
	private TextView tv_money;
	private TextView tv_point;
	private TextView tv_level;
	private ListView lv;
	private Button btn_add;
	private Button btn_del;
	private Button btn_talk;
	private Button btn_map;
	private ImageView iv;
	private User u = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		Intent intent=getIntent();
		if (intent.getExtras()!=null)
			u=(User)intent.getExtras().getSerializable("user");
		if (u==null){
			u=new User();
			u.face="@drawable/face"+233;
			u.name="Username";
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			u.isFriend=false;
			u.isSelf=true;
		}
		tv_name=(TextView)findViewById(R.id.textView2);
		tv_money=(TextView)findViewById(R.id.textView4);
		tv_point=(TextView)findViewById(R.id.textView6);
		tv_level=(TextView)findViewById(R.id.textView9);
		lv=(ListView)findViewById(R.id.listView1);
		btn_add=(Button)findViewById(R.id.button1);
		btn_del=(Button)findViewById(R.id.button2);
		btn_talk=(Button)findViewById(R.id.button3);
		btn_map=(Button)findViewById(R.id.button4);
		iv=(ImageView)findViewById(R.id.imageView1);
		u.point=0;
		iv.setImageResource(Util.getResourceId(getResources(), u.face));
		//lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roads));		
		lv.setAdapter(new SimpleAdapter(this,getRoadsInfo(),R.layout.item_road
				,new String[]{"name","info"},new int[]{R.id.textView1,R.id.textView2}));
			
		tv_name.setText(""+u.name.trim());
		tv_money.setText(""+u.money);
		tv_point.setText(""+u.point);
		tv_level.setText(""+u.level);		
		
		if (u.isFriend){
			btn_add.setVisibility(View.GONE);
			btn_del.setVisibility(View.VISIBLE);
		}else{
			btn_add.setVisibility(View.VISIBLE);
			btn_del.setVisibility(View.GONE);
		}		
		
		if (u.isSelf){
			btn_del.setVisibility(View.GONE);
			btn_add.setVisibility(View.GONE);
		}
		
		btn_add.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(UserActivity.this).setMessage("Do you really want to add \""+u.name.trim()+"\" to your friend list?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						u.isFriend=true;
						btn_add.setVisibility(View.GONE);
						btn_del.setVisibility(View.VISIBLE);
					}
				}).setNegativeButton("No", null).show();
			}
		});
		
		btn_del.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(UserActivity.this).setMessage("Do you really want to remove \""+u.name.trim()+"\" from your friend list?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						u.isFriend=false;
						btn_add.setVisibility(View.VISIBLE);
						btn_del.setVisibility(View.GONE);
					}
				}).setNegativeButton("No", null).show();
			}
		});
		
		btn_talk.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(UserActivity.this, "Calling "+u.name.trim(), Toast.LENGTH_SHORT).show();				
			}
		});
		
		btn_map.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(UserActivity.this,RoadMaskedMapActivity.class);
				i.putExtra("user",u);
				startActivity(i);
			}
		});
		
		iv.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(UserActivity.this,CarModelActivity.class);
				startActivity(i);
			}
		});
	}
	
	private Map<String,String> buildInfo(String name,String info){
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", name);
		map.put("info", info);
		return map;
	}
	
	private List<Map<String,String>> getRoadsInfo(){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();		
		for(int i=0;i<10;++i){
			int p=(int)(Math.random()*10000);
			u.point+=p;			
			list.add(buildInfo("Road"+i, "Point:"+p));		
		}
		return list;
	}
}
