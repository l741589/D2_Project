package com.example.d2_project;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d2_project.data.ColorUtil;
import com.example.d2_project.data.User;

class UserGridAdapter extends BaseAdapter{
	private User[] users;
	private Context c;
	private Map<Integer,Integer> colormap=new HashMap<Integer, Integer>();
	
	public UserGridAdapter(Context context,User[] users){
		c=context;
		this.users=users;
	}

	@Override
	public int getCount() {
		return users.length;
	}

	@Override
	public Object getItem(int position) {
		return users[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if (v==null) v=((Activity)c).getLayoutInflater().inflate(R.layout.griditem_friend, null);
		TextView tv=(TextView) v.findViewById(R.id.textView1);
		ImageView iv=(ImageView)v.findViewById(R.id.imageView1);
		LinearLayout ll=(LinearLayout)v.findViewById(R.id.LinearLayout2);

		User u=users[position];
		if (u.isWhiteListed){
			SpannableString text=new SpannableString(" "+u.name);
			ImageSpan span=new ImageSpan(c, R.drawable.ic_whitelisted);
			text.setSpan(span,0,1,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv.setText(text);
		}else{
			tv.setText(u.name);
		}		
		if (colormap.get(position)==null) colormap.put(position, ColorUtil.getRandomColor());
		iv.setImageResource(Util.getResourceId(c.getResources(), u.face));
		ll.setBackgroundColor(colormap.get(position));
		if (u.isPined) ll.setAlpha(0.2f); else ll.setAlpha(1f); 
		return v;
	} 
}