package com.example.d2_project;

import android.content.Context;
import android.content.res.Resources;
import android.text.GetChars;

public class Util {
	static int getResourceId(Resources r, String s){
		if (s.charAt(0)=='@') s=s.substring(1);
		String[] ss=s.split("/");
		return r.getIdentifier(ss[1], ss[0], R.class.getPackage().getName());
	}
}
