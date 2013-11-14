package com.example.d2_project.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
   
public class ImgUtil {  
    private static ImgUtil tools = new ImgUtil();  
  
    public static ImgUtil getInstance() {  
        if (tools == null) {  
            tools = new ImgUtil();  
            return tools;  
        }  
        return tools;  
    }  
  
    // ��byte[]ת����InputStream  
    public static InputStream Byte2InputStream(byte[] b) {  
        ByteArrayInputStream bais = new ByteArrayInputStream(b);  
        return bais;  
    }  
  
    // ��InputStreamת����byte[]  
    public static byte[] InputStream2Bytes(InputStream is) {  
        String str = "";  
        byte[] readByte = new byte[1024];  
        int readCount = -1;  
        try {  
            while ((readCount = is.read(readByte, 0, 1024)) != -1) {  
                str += new String(readByte).trim();  
            }  
            return str.getBytes();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    // ��Bitmapת����InputStream  
    public static InputStream Bitmap2InputStream(Bitmap bm) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  
        InputStream is = new ByteArrayInputStream(baos.toByteArray());  
        return is;  
    }  
  
    // ��Bitmapת����InputStream  
    public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);  
        InputStream is = new ByteArrayInputStream(baos.toByteArray());  
        return is;  
    }  
  
    // ��InputStreamת����Bitmap  
    public  static Bitmap InputStream2Bitmap(InputStream is) {  
        return BitmapFactory.decodeStream(is);  
    }  
  
    // Drawableת����InputStream  
    public  static InputStream Drawable2InputStream(Drawable d) {  
        Bitmap bitmap = ImgUtil.drawable2Bitmap(d);  
        return ImgUtil.Bitmap2InputStream(bitmap);  
    }  
  
    // InputStreamת����Drawable  
    public static  Drawable InputStream2Drawable(InputStream is) {  
        Bitmap bitmap = ImgUtil.InputStream2Bitmap(is);  
        return ImgUtil.bitmap2Drawable(bitmap);  
    }  
  
    // Drawableת����byte[]  
    public static  byte[] Drawable2Bytes(Drawable d) {  
    	//Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
    	//ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	//bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
    	//byte[] bitmapdata = stream.toByteArray();
    	//return bitmapdata;
        Bitmap bitmap = drawable2Bitmap(d);  
        return ImgUtil.Bitmap2Bytes(bitmap);  
    }  
  
    // byte[]ת����Drawable  
    public static  Drawable Bytes2Drawable(byte[] b) {  
        Bitmap bitmap = Bytes2Bitmap(b);  
        return bitmap2Drawable(bitmap);  
    }  
  
    // Bitmapת����byte[]  
    public static  byte[] Bitmap2Bytes(Bitmap bm) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  
        bm.setDensity(Bitmap.DENSITY_NONE);
        return baos.toByteArray();  
    }  
  
    // byte[]ת����Bitmap  
    public static  Bitmap Bytes2Bitmap(byte[] b) {  
        if (b.length != 0) {  
        	Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
        	bm.setDensity(Bitmap.DENSITY_NONE);
            return bm;
        }  
        return null;  
    }  
  
    // Drawableת����Bitmap  
    public  static Bitmap drawable2Bitmap(Drawable drawable) {  
      /*  Bitmap bitmap = Bitmap  
                .createBitmap(  
                        drawable.getIntrinsicWidth(),  
                        drawable.getIntrinsicHeight(),  
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                                : Bitmap.Config.RGB_565);
        bitmap.setDensity(bitmap.D)
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),  
                drawable.getIntrinsicHeight());  
        drawable.draw(canvas);
        return bitmap; */
    	// ȡ drawable �ĳ���  
        int w = drawable.getIntrinsicWidth();  
        int h = drawable.getIntrinsicHeight();  
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;  
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);  
        bitmap.setDensity(bitmap.DENSITY_NONE);
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0, 0, w, h);    
        drawable.draw(canvas);  
        return bitmap;          
    }  
  
    // Bitmapת����Drawable  
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap); 
    }  
}