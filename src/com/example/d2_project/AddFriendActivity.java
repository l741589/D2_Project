package com.example.d2_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.d2_project.data.User;

public class AddFriendActivity extends BaseActivity implements OnItemClickListener {

	GridView gv;
	Button btn;
	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfriend);
		gv=(GridView)findViewById(R.id.gridView1);
		et=(EditText)findViewById(R.id.editText1);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				gv.setAdapter(new UserGridAdapter(AddFriendActivity.this, getUsers()));
			}
		});
		gv.setOnItemClickListener(this);
	}
	
	private User[] getUsers(){
		User[] us=new User[10];
		for (int i=0;i<us.length;++i){
			User u=new User();
			int x=(int)(Math.random()*254);
			us[i]=u;
			u.face="@drawable/face"+x;
			u.name="ÓÃ»§"+et.getText()+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			u.isFriend=false;
		}
		return us;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		Intent i=new Intent(this,UserActivity.class);
		i.putExtra("user",(User)parent.getAdapter().getItem(position));
		startActivity(i);		
	}
}
