package com.example.d2_project;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d2_project.data.Achievement;
import com.example.d2_project.data.RankingList;
import com.example.d2_project.data.User;

public class RankingListAdapter extends BaseAdapter{
	
	private RankingList[] rankingLists;
	private Context c;

	public RankingListAdapter(Context context, RankingList[] rankingLists) {
		// TODO Auto-generated constructor stub
		this.c = context;
		this.rankingLists = rankingLists;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rankingLists.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return rankingLists[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;		
		if (v==null) v=((Activity)c).getLayoutInflater().inflate(R.layout.item_ranking_list, null);
		ImageView iv=(ImageView)v.findViewById(R.id.imageView1);
		TextView tv1=(TextView)v.findViewById(R.id.textView1);
		TextView tv2=(TextView)v.findViewById(R.id.textView2);
		TextView tv3=(TextView)v.findViewById(R.id.textView3);
		RankingList rl=rankingLists[position];
		switch(position){
		case 0:tv1.setTextColor(0xFFFFD700);break;
		case 1:tv1.setTextColor(0xFFC0C0C0);break;
		case 2:tv1.setTextColor(0xFFC0A000);break;
		default:if (position<getCount()/5)
				tv1.setTextColor(Color.RED);
			else 
				tv1.setTextColor(Color.BLACK);
			break;
		}
		User u = rl.user;
		iv.setImageResource(Util.getResourceId(c.getResources(), u.face));
		tv1.setText(""+rl.rankNum);
		tv2.setText(u.name);
		tv3.setText(u.point+"");
		return v;
	}


}
