package com.example.d2_project;

import javax.crypto.spec.PSource;

import com.example.d2_project.data.Achievement;
import com.example.d2_project.data.ColorUtil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AchievementAdapter extends BaseAdapter {
	
	private Achievement[] achievements;
	private Context c;
	
	public AchievementAdapter(Context context,Achievement[] achievements) {
		this.c=context;
		this.achievements=achievements;
	}

	@Override
	public int getCount() {
		return achievements.length;
	}

	@Override
	public Object getItem(int position) {
		return achievements[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;		
		if (v==null) v=((Activity)c).getLayoutInflater().inflate(R.layout.item_achievement, null);
		ImageView iv=(ImageView)v.findViewById(R.id.imageView1);
		TextView tv1=(TextView)v.findViewById(R.id.textView1);
		TextView tv2=(TextView)v.findViewById(R.id.textView2);
		LinearLayout ll=(LinearLayout)v.findViewById(R.id.LinearLayout2);
		Achievement a=achievements[position];
		iv.setImageResource(Util.getResourceId(c.getResources(), a.icon));
		tv1.setText(a.name);
		tv2.setText(a.description);
		ll.setBackgroundColor(ColorUtil.getRandomColor());
		return v;
	}

}
