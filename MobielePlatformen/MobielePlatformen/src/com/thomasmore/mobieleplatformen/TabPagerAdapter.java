package com.thomasmore.mobieleplatformen;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
	public TabPagerAdapter(android.support.v4.app.FragmentManager fm) {
	    super(fm);
	    
}

	@Override
	public Fragment getItem(int i) {
		// TODO Auto-generated method stub
		 switch (i) {
	        case 0:
	            //Fragement for Android Tab
	            return new EditCall();
	        case 1:
	           //Fragment for Ios Tab
	            return new EditSms();
	        case 2:
	            //Fragment for Windows Tab
	            return new EditData();
	        }
	    return null;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
