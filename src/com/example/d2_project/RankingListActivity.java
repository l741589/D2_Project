package com.example.d2_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.d2_project.data.Achievement;
import com.example.d2_project.data.RankingList;
import com.example.d2_project.data.User;

public class RankingListActivity extends Activity implements OnItemClickListener{

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking_list);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(new RankingListAdapter(this, getRankingLists()));
		lv.setOnItemClickListener(this);
	}

	private RankingList[] getRankingLists() {
		// TODO Auto-generated method stub
		int[] points = getSortedPoints();
		RankingList[] rankingLists = new RankingList[30];
		for (int i=0;i<rankingLists.length;++i) {
			RankingList rankingList = new RankingList();
			User u = new User();
			u.face="@drawable/face"+i%254;//???????????????
			u.name="ÓÃ»§"+i;
			u.money=(int)(Math.random()*10000);
			u.point=points[i];
			u.level=(int)(Math.random()*10);
			if (Math.random()<0.1) u.isWhiteListed=true;
			u.isFriend=true;
			
			rankingList.user = u;
			rankingList.rankNum = i+1;
			rankingLists[i] = rankingList;
		}
		return rankingLists;
	}

	private int[] getSortedPoints() {
		// TODO Auto-generated method stub
		int[] points = new int[30];
		for(int i = 0; i < 30; i++) {
				points[i] = (int)(Math.random()*10000);
		}
		for(int i=0; i<30; i++) {
			for(int j=29; j>i; j--) {
				if(points[j] > points[j-1]) {
					int temp = points[j];
					points[j] = points[j-1];
					points[j-1] = temp;
				}
			}
		}
		
		return points;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking_list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent i=new Intent(this,UserActivity.class);
		i.putExtra("user",((RankingList)parent.getAdapter().getItem(position)).user);
		startActivity(i);	
	}

}
