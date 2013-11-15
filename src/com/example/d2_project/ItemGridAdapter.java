package com.example.d2_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d2_project.data.ColorUtil;
import com.example.d2_project.data.ImgUtil;
import com.example.d2_project.data.Item;
import com.example.d2_project.data.User;

class ItemGridAdapter extends BaseAdapter{
	private List<Item> items;
	private Context c;
	private Map<Integer,Integer> colormap=new HashMap<Integer, Integer>();
	
	public void remove(Item item){
		items.remove(item);
	}
	
	public ItemGridAdapter(Context context,Item[] items){
		c=context;
		this.items=new ArrayList<Item>();
		for (Item i:items) this.items.add(i);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if (v==null) v=((Activity)c).getLayoutInflater().inflate(R.layout.griditem_item, null);
		LinearLayout ll=(LinearLayout)v.findViewById(R.id.LinearLayout2);
		TextView tv1=(TextView) v.findViewById(R.id.textView1);
		TextView tv2=(TextView) v.findViewById(R.id.textView2);
		TextView tv3=(TextView) v.findViewById(R.id.textView3);
		ImageView iv=(ImageView)v.findViewById(R.id.imageView1);
		Item u=items.get(position);
		tv1.setText(u.name);
		if (u.count>0){
			if (u.count==1) tv2.setText(String.format("%d item left", u.count));
			else tv2.setText(String.format("%d items left", u.count));			
		}else{
			tv2.setVisibility(View.GONE);
		}
		
		if (u.isOwned){
			tv3.setVisibility(View.GONE);
		}else{
			tv3.setText(String.format("$%.2f", u.price));
		}
		
		if (colormap.get(position)==null) colormap.put(position, ColorUtil.getRandomColor());
		iv.setImageResource(Util.getResourceId(c.getResources(), u.icon));
		ll.setBackgroundColor(colormap.get(position));
		return v;
	} 
}