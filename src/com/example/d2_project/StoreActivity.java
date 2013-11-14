package com.example.d2_project;

import com.example.d2_project.data.Item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class StoreActivity extends BaseActivity implements OnTouchListener,OnGestureListener,OnClickListener{
	
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private ViewFlipper vf;
	private GridView gv1;
	private GridView gv2;
	private GridView gv3;
	private GestureDetector gd;
	private double money=500;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		tv4=(TextView)findViewById(R.id.textView4);
		tv1.setTextColor(Color.RED);
		vf=(ViewFlipper)findViewById(R.id.viewFlipper1);
		gv1=(GridView)findViewById(R.id.gridView1);
		gv2=(GridView)findViewById(R.id.gridView2);
		gv3=(GridView)findViewById(R.id.gridView3);
		tv2.setText(String.format("￥%.2f  ", money));
		tv1.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		gv1.setOnTouchListener(this);
		gv2.setOnTouchListener(this);
		gv3.setOnTouchListener(this);
		gd=new GestureDetector(this, this);
		
		gv1.setOnItemClickListener(gv1ItemClick);
		gv2.setOnItemClickListener(gv2ItemClick);
		gv3.setOnItemClickListener(gv3ItemClick);
		gv1.setAdapter(new ItemGridAdapter(this, getStoreItems()));
		gv2.setAdapter(new ItemGridAdapter(this, getUserItems()));
		gv3.setAdapter(new ItemGridAdapter(this, getMyItems()));
		vf.removeAllViews();
		vf.addView(gv1);		
	}
	
	private Item[] getStoreItems(){
		Item[] items=new Item[30];
		for (int i=0;i<items.length;++i){
			items[i]=new Item();
			Item t=items[i];
			t.name="道具"+i;
			t.icon="@drawable/icon"+(int)(Math.random()*42);
			t.count=0;
			t.price=Math.random()*50;
			t.decription="我是 "+t.name+" 的描述信息。";
		}
		return items;
	}
	
	private Item[] getUserItems(){
		Item[] items=new Item[30];
		for (int i=0;i<items.length;++i){
			items[i]=new Item();
			Item t=items[i];
			t.name="道具"+i;
			t.icon="@drawable/icon"+(int)(Math.random()*42);
			t.count=(int) (Math.random()*10)+1;
			t.price=Math.random()*100;
			t.decription="我是 "+t.name+" 的描述信息。";
		}
		return items;
	}
	
	private Item[] getMyItems(){
		Item[] items=new Item[30];
		for (int i=0;i<items.length;++i){
			items[i]=new Item();
			Item t=items[i];
			t.name="道具"+i;
			t.icon="@drawable/icon"+(int)(Math.random()*42);
			t.count=(int)(Math.random()*10)+1;
			t.price=Math.random()*50;
			t.decription="我是 "+t.name+" 的描述信息。";
			t.isOwned=true;
		}
		return items;
	}
	
	OnItemClickListener gv1ItemClick=new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			final Item t=(Item)parent.getAdapter().getItem(position);
			new AlertDialog.Builder(StoreActivity.this).setTitle("是否买入此道具？")
			.setMessage(t.name+"\n"+t.decription)
			.setPositiveButton("是", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (money>=t.price){
						money-=t.price;
						tv2.setText(String.format("￥%.2f  ", money));
					}else{
						Toast.makeText(StoreActivity.this, "你没有足够的金币购买此道具", Toast.LENGTH_SHORT).show();
					}					
				}
			}).setNegativeButton("否",null).show();		
		}
	};
	
	OnItemClickListener gv2ItemClick=new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			final Item t=(Item)parent.getAdapter().getItem(position);
			final AdapterView<?> Parent=parent;
			final View V=v;
			new AlertDialog.Builder(StoreActivity.this).setTitle("是否买入此道具？")
			.setMessage(t.name+"\n"+t.decription)
			.setPositiveButton("是", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (money>=t.price){
						money-=t.price;
						t.count-=1;
						if (t.count==0) ((ItemGridAdapter)Parent.getAdapter()).remove(t);
						tv2.setText(String.format("￥%.2f  ", money));						
						((ItemGridAdapter)Parent.getAdapter()).notifyDataSetChanged();
					}else{
						Toast.makeText(StoreActivity.this, "你没有足够的金币购买此道具", Toast.LENGTH_SHORT).show();
					}					
				}
			}).setNegativeButton("否",null).show();		
		}
	};
	
	OnItemClickListener gv3ItemClick=new OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
			final ItemGridAdapter adapter=(ItemGridAdapter)parent.getAdapter();
			final Item t=(Item)adapter.getItem(position);			
			new AlertDialog.Builder(StoreActivity.this).setTitle("选择你要进行的操作")
			.setMessage(t.name+"\n"+t.decription).setPositiveButton("出售", new DialogInterface.OnClickListener() {			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					new AlertDialog.Builder(StoreActivity.this).setTitle("选择出售方式")
					.setView(getDialogView(t)).setPositiveButton("确定", new DialogInterface.OnClickListener() {					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							money+=t.price*0.2;
							tv2.setText(String.format("￥%.2f  ", money));
							t.count-=1;
							if (t.count==0) adapter.remove(t);
							adapter.notifyDataSetChanged();
						}
					}).setNegativeButton("取消", null).show();
				}
			}).setNeutralButton("丢弃", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					t.count-=1;
					if (t.count==0) adapter.remove(t);
					adapter.notifyDataSetChanged();
				}			
			}).setNegativeButton("使用", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					t.count-=1;
					if (t.count==0) adapter.remove(t);
					adapter.notifyDataSetChanged();
				}			
			}).show();
		}
		
		private View getDialogView(Item t){
			View v=getLayoutInflater().inflate(R.layout.dialog_sell, null);
			final RadioGroup rg=(RadioGroup)v.findViewById(R.id.radioGroup1);
			final EditText et=(EditText)v.findViewById(R.id.editText1);
			final TextView tv=(TextView)v.findViewById(R.id.textView1);
			tv.setText(String.format("%.2f",t.price*0.2));
			rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch(checkedId){
					case R.id.radio0:
						tv.setEnabled(true);
						et.setEnabled(false);
						break;
					case R.id.radio1:
						tv.setEnabled(false);
						et.setEnabled(true);
						break;
					}
				}
			});
			return v;
		}
	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gd.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}
	
	private void show1(){
		if (vf.getCurrentView()==gv1) return;
		vf.removeView(gv1);
		vf.setInAnimation(getApplicationContext(), R.anim.push_right_in);   
		vf.setOutAnimation(getApplicationContext(), R.anim.push_right_out);
		if (vf.getCurrentView()==gv2){
			vf.removeView(gv3);
			vf.addView(gv1);
			vf.showNext();			
		}else if (vf.getCurrentView()==gv3){
			vf.removeView(gv2);
			vf.addView(gv1);
			vf.showNext();
		}	
		tv1.setTextColor(Color.RED);
		tv3.setTextColor(Color.BLACK);
		tv4.setTextColor(Color.BLACK);
	}
	
	private void show2(){
		if (vf.getCurrentView()==gv2) return;
		vf.removeView(gv2);
		if (vf.getCurrentView()==gv1) {
			vf.setInAnimation(getApplicationContext(), R.anim.push_left_in);   
			vf.setOutAnimation(getApplicationContext(), R.anim.push_left_out);
			vf.removeView(gv3);
			vf.addView(gv2);
			vf.showNext();
		}else if (vf.getCurrentView()==gv3){
			vf.setInAnimation(getApplicationContext(), R.anim.push_right_in);   
			vf.setOutAnimation(getApplicationContext(), R.anim.push_right_out);
			vf.removeView(gv1);
			vf.addView(gv2);
			vf.showNext();
		}		
		tv1.setTextColor(Color.BLACK);
		tv3.setTextColor(Color.RED);
		tv4.setTextColor(Color.BLACK);
	}
	
	private void show3(){
		if (vf.getCurrentView()==gv3) return;
		vf.removeView(gv3);		
		vf.setInAnimation(getApplicationContext(), R.anim.push_left_in);   
		vf.setOutAnimation(getApplicationContext(), R.anim.push_left_out);			
		if (vf.getCurrentView()==gv2){
			vf.removeView(gv1);
			vf.addView(gv3);
			vf.showNext();
		}else if (vf.getCurrentView()==gv1){
			vf.removeView(gv2);
			vf.addView(gv3);
			vf.showNext();
		}
		tv1.setTextColor(Color.BLACK);
		tv3.setTextColor(Color.BLACK);
		tv4.setTextColor(Color.RED);
	}	
	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,	float velocityY) {
		if (e1.getX()-e2.getX()>400){
			if (vf.getCurrentView()==gv1) show2();
			else if (vf.getCurrentView()==gv2) show3();
			return true;
		}else if (e2.getX()-e1.getX()>400){
			if (vf.getCurrentView()==gv3) show2();
			else if (vf.getCurrentView()==gv2) show1();
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

	@Override
	public void onShowPress(MotionEvent e) {}

	@Override
	public boolean onSingleTapUp(MotionEvent e) { return false;	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.textView1:show1();break;
		case R.id.textView3:show2();break;
		case R.id.textView4:show3();break;
		}
	}
}

