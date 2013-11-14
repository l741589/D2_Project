package com.example.d2_project;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d2_project.data.Achievement;
import com.example.d2_project.data.Mission;

public class MissionAdapter extends BaseAdapter {

	private Mission[] missions;
	private Context c;
	
	public MissionAdapter(Context context,Mission[] missions) {
		this.c = context;
		this.missions = missions;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return missions.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return missions[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=convertView;		
		if (v==null) v=((Activity)c).getLayoutInflater().inflate(R.layout.item_achievement, null);
		ImageView iv=(ImageView)v.findViewById(R.id.imageView1);
		TextView tv1=(TextView)v.findViewById(R.id.textView1);
		TextView tv2=(TextView)v.findViewById(R.id.textView2);
		Mission m=missions[position];
		iv.setImageResource(Util.getResourceId(c.getResources(), m.icon));
		tv1.setText(m.name);
		tv2.setText(m.description);
		return v;
	}

}
