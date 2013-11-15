package com.example.d2_project;

import com.example.d2_project.data.Achievement;

import android.os.Bundle;
import android.widget.ListView;

public class AchievementActivity extends BaseActivity {
	
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievement);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(new AchievementAdapter(this, getAchievements()));
	}
	
	private Achievement[] getAchievements(){
		Achievement[] ret=new Achievement[30];
		for (int i=0;i<ret.length;++i){
			Achievement a=new Achievement();
			a.icon=String.format("@drawable/icon%d",(int)(Math.random()*42));
			a.name="Badge"+i;
			a.description = "I am the description of the badge \""+a.name+"\"";
			ret[i] = a;
		}
		return ret;
	}
}
