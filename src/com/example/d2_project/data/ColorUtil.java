package com.example.d2_project.data;

public class ColorUtil {
	
	public static final int[] colors={0x252525,0xF4B300,0x78BA00,0x26731C,0xAE113D,0x632F00,0x2E1700,
			0xB01E00,0x4E0000,0x4E0038,0xC1004F,0x7200AC,0x2D004E,0x4617B4,0x1F0068
			};
	
	public static int getRandomColor(){
		return colors[(int)(Math.random()*colors.length)]|0xff000000;
	}

}
