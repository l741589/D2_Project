package com.example.d2_project;

import com.example.d2_project.data.Item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

@Deprecated
public class ItemActivity extends BaseActivity implements OnItemClickListener{
	
	private TextView tv1;
	private TextView tv2;
	private GridView gv;
	private Button btn;
	private ItemGridAdapter adapter;
	private double money=300;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		gv=(GridView)findViewById(R.id.gridView1);
		btn=(Button)findViewById(R.id.button1);
		gv.setAdapter(adapter=new ItemGridAdapter(this, getItems()));
		gv.setOnItemClickListener(this);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ItemActivity.this,StoreActivity.class));
				finish();
			}
		});
		tv2.setText(String.format("��%.2f  ", money));
	}
	
	private Item[] getItems(){
		Item[] items=new Item[30];
		for (int i=0;i<items.length;++i){
			items[i]=new Item();
			Item t=items[i];
			t.name="����"+i;
			t.icon="@drawable/icon"+(int)(Math.random()*42);
			t.count=(int)(Math.random()*10)+1;
			t.price=Math.random()*50;
			t.decription="���� "+t.name+" ��������Ϣ��";
			t.isOwned=true;
		}
		return items;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		final Item t=(Item)adapter.getItem(position);
		new AlertDialog.Builder(this).setTitle("ѡ����Ҫ���еĲ���")
		.setMessage(t.name+"\n"+t.decription).setPositiveButton("����", new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new AlertDialog.Builder(ItemActivity.this).setTitle("ѡ����۷�ʽ")
				.setView(getDialogView(t)).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						money+=t.price*0.2;
						tv2.setText(String.format("��%.2f  ", money));
						t.count-=1;
						if (t.count==0) adapter.remove(t);
						adapter.notifyDataSetChanged();
					}
				}).setNegativeButton("ȡ��", null).show();
			}
		}).setNeutralButton("����", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				t.count-=1;
				if (t.count==0) adapter.remove(t);
				adapter.notifyDataSetChanged();
			}			
		}).setNegativeButton("ȡ��", null).show();
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
}
