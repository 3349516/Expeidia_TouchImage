package com.example.listviewforpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

public class Tools {

	//整个屏幕高度
	public static int getScreenHeigt(Activity context){
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}
	//状态栏高度
	public static int getStatusBarHeight (Activity context){
		 Rect rect = new Rect();
		 context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
	     return  rect.top;
	}
	
	//标题栏高度
	public static int getTitleBarHeight(Activity context){
		 Rect rect = new Rect();
		 context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
	     View view = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
	     return rect.height() - view.getHeight();
	}
	
	 //转换dip为px 
	public static int convertDipOrPx(Context context, int dip) { 
	    float scale = context.getResources().getDisplayMetrics().density; 
	    return (int)(dip*scale + 0.5f*(dip>=0?1:-1)); 
	} 
	 
	//转换px为dip 
	public static int convertPxOrDip(Context context, int px) { 
	    float scale = context.getResources().getDisplayMetrics().density; 
	    return (int)(px/scale + 0.5f*(px>=0?1:-1)); 
	} 

}
