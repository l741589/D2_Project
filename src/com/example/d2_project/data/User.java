package com.example.d2_project.data;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7237473898127403772L;
	
	public String face;
	public String name;
	public int money;
	public int point;
	public int level;
	public boolean isWhiteListed = false;
	public boolean isFriend = false;
	public boolean isSelf = false;
	public boolean isPined = false;
}
