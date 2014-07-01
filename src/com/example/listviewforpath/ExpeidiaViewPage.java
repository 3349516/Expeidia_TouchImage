package com.example.listviewforpath;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ExpeidiaViewPage extends ViewPager {

	public ExpeidiaViewPage(Context context) {
		super(context);
		 setClipChildren(false);
		    setPageMargin(15);
	}

	public ExpeidiaViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
	    setClipChildren(false);
	    setPageMargin(15);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// 这句话的作用告诉父view，我的单击事件我自行处理，不要阻碍我。
		getParent().requestDisallowInterceptTouchEvent(true);

		return super.dispatchTouchEvent(ev); 
		 
	}
}