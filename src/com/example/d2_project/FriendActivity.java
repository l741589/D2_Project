package com.example.d2_project;

import javax.crypto.spec.PSource;

import com.example.d2_project.data.User;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewDebug.FlagToString;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class FriendActivity extends BaseActivity implements OnItemClickListener,OnItemLongClickListener,OnTouchListener,OnClickListener{
	
	private GridView gv;
	private TextView tv;
	private ImageButton btn;
	private EditText et;
	boolean searched=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		gv=(GridView)findViewById(R.id.gridView1);
		btn=(ImageButton)findViewById(R.id.button1);
		gv.setAdapter(new UserGridAdapter(this,getUsers()));
		tv=(TextView)findViewById(R.id.textView1);
		et=(EditText)findViewById(R.id.editText1);
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		gv.setOnItemLongClickListener(this);
		gv.setOnTouchListener(this);
		gv.setOnItemClickListener(this);
		btn.setOnClickListener(this);
	}
	
	private User[] getUsers(){
		User[] us=new User[256];
		for (int i=0;i<us.length;++i){
			User u=new User();
			us[i]=u;
			u.face="@drawable/face"+i%254;
			u.name="用户"+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			if (Math.random()<0.1) u.isWhiteListed=true;
			u.isFriend=true;
		}
		return us;
	}
	
	private User[] getAddUsers(){
		User[] us=new User[10];
		for (int i=0;i<us.length;++i){
			User u=new User();
			int x=(int)(Math.random()*254);
			us[i]=u;
			u.face="@drawable/face"+x;
			u.name="用户"+et.getText()+i;
			u.money=(int)(Math.random()*10000);
			u.point=(int)(Math.random()*10000);
			u.level=(int)(Math.random()*10);
			u.isFriend=false;
		}
		return us;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		tv.setText("与好友 "+((TextView)v.findViewById(R.id.textView1)).getText().toString().trim()+" 通讯中");
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			
			return false;
		case MotionEvent.ACTION_UP:
			tv.setText("好友");
			return false;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		Intent i=new Intent(this,UserActivity.class);
		i.putExtra("user",(User)parent.getAdapter().getItem(position));
		startActivity(i);		
	}

	@Override
	public void onClick(View v) {
		//startActivity(new Intent(this,AddFriendActivity.class));
		if (et.getVisibility()==View.INVISIBLE){
			et.setVisibility(View.VISIBLE);
			et.requestFocus();
			InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
			imm.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS);
			searched=true;
		}else{
			et.setVisibility(View.INVISIBLE);
			gv.setAdapter(new UserGridAdapter(this, getAddUsers()));
			searched=true;
		}		
	}
	
	@Override
	public void onBackClick() {
		if (!searched){
			super.onBackClick();
		}else{
			searched=false;
			et.setVisibility(View.INVISIBLE);
			gv.setAdapter(new UserGridAdapter(this, getUsers()));
		}
		
	}

}
