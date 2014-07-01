package com.example.listviewforpath;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

public class ExpeidiaScrollView extends ScrollView {

	private Scroller mScroller;
	private float currentX, currentY, startX, startY;
	private boolean scrollerType;
	private Activity activity;
	private ViewPager viewPager;
	private int bottom;
	private int len = 50;
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;
	private int imageViewHeight, moveHeight ;
	private  boolean isYScrolling = false;
	private  int initHeadHeight ;
	public ExpeidiaScrollView(Context context) {
		super(context);
		init();
	}

	public ExpeidiaScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init(){
		activity  = (Activity) getContext();
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();

		viewPager = (ViewPager) findViewById(R.id.viewpage);
		mScroller = new Scroller(getContext());
		initHeadHeight = Tools.convertDipOrPx(getContext(), 190);
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (!mScroller.isFinished()) {
			return super.onTouchEvent(event);
		}
		currentX = event.getX();
		currentY = event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = event.getX();
			yLast = event.getY();

			startX = currentX;
			startY = currentY;
			bottom = viewPager.getBottom();
			imageViewHeight = viewPager.getHeight();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = event.getX();
			final float curY = event.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if(xDistance < yDistance){
				isYScrolling = true;
			}else{
				isYScrolling = false;
			}
			
			if (xDistance < yDistance &&viewPager.isShown() && viewPager.getTop() >= 0) {
				
				viewPager.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						return !scrollerType;
					}
				});
				
				int y = (int) (bottom + (currentY - startY) / 2.5f); //移动的高度
				moveHeight = y;
				if (y < viewPager.getBottom() + len && y >= bottom) {

					viewPager.setLayoutParams(new RelativeLayout.LayoutParams(
							new RelativeLayout.LayoutParams(viewPager
									.getWidth(), y)));
				}
				scrollerType = false;
			
			}
			 
			break;
		case MotionEvent.ACTION_UP:
			scrollerType = true;
			int l = moveHeight-imageViewHeight;
			if(isYScrolling){
				if(l>100){
					mScroller.startScroll(viewPager.getLeft(), viewPager.getBottom(),
							0 - viewPager.getLeft(), Tools.getScreenHeigt(activity) -Tools.getStatusBarHeight(activity) - viewPager.getBottom() - Tools.convertDipOrPx(activity, 50),
							200);
				}else{
					mScroller.startScroll(viewPager.getLeft(), viewPager.getBottom(),
							0 - viewPager.getLeft(), initHeadHeight - viewPager.getBottom(),
							200);
				}
				postInvalidate();
			}
			break;
		}
		return super.dispatchTouchEvent(event);
	}

	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int x = mScroller.getCurrX();
			int y = mScroller.getCurrY();
			viewPager.layout(0, 0, x + viewPager.getWidth(), y);
		 
			if (!mScroller.isFinished() && scrollerType ) {
				viewPager.setLayoutParams(new RelativeLayout.LayoutParams(
						viewPager.getWidth(), y));

			}
			postInvalidate();
		}
	}
	
	 
}
