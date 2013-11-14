package com.example.d2_project;

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
			text.setSpan(new ImageSpan(c, R.drawable.ic_whitelisted),0,1,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv.setText(text);
		}else{
			tv.setText(u.name);
		}
		
		iv.setImageResource(Util.getResourceId(c.getResources(), u.face));
		ll.setBackgroundColor(ColorUtil.getRandomColor());
		return v;
	} 
}