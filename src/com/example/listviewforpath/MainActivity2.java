package com.example.listviewforpath;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * Class: MainActivity.java<br>
 * Date: 2013/04/04<br>
 * Author: TiejianSha <br>
 * Email: tntshaka@gmail.com<br>
 */

public class MainActivity2 extends Activity  {
	public   ViewPager viewpage;
	ArrayList<Integer> listViews = new ArrayList<Integer>();
	 LayoutInflater mInflater;
	 public  MyPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		viewpage = (ViewPager) findViewById(R.id.viewpage);
		 mInflater = getLayoutInflater();
		   listViews.add(R.drawable.item1);
		   listViews.add(R.drawable.item2);
		   listViews.add(R.drawable.item3);
		   listViews.add(R.drawable.item4);
		   adapter = new MyPagerAdapter(listViews);
		  viewpage.setAdapter(adapter);
		  viewpage.setCurrentItem(0);
		  
		
	

	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {

			data.add(i + "");
			data.add("Name:TiejianSha");
			data.add("Email:tntshaka@gmail.com");
		}

		return data;
	}
	
	/**
     * ViewPager适配器
*/
    public class MyPagerAdapter extends PagerAdapter {
        public List<Integer> mListViews;

        public MyPagerAdapter(List<Integer> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup  container, int position, Object object) {
        	container.removeView((View)object);  
        }


        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View container, int arg1) {
        	 View iv =  mInflater.inflate(R.layout.item_scene_viewheader_item, null);
        	 ImageView	imageView = (ImageView) iv.findViewById(R.id.path_headimage);
        	 imageView.setImageResource(mListViews.get(arg1));
      
             ((ViewPager)container).addView(iv, 0,new RelativeLayout.LayoutParams(900,500));  
             return iv;  
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
        	 return view == (View) obj;  
        }
        
       
 
 
    }

}
