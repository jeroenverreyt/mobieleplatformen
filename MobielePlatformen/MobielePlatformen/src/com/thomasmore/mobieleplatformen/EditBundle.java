package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

public class EditBundle extends FragmentActivity {

	ViewPager Tab;
	TabPagerAdapter TabAdapter;
	ActionBar actionBar;
	private int position;
	private ArrayList<Abo> abolist = new ArrayList<Abo>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(arg0);
		setContentView(R.layout.editbundle);

		Bundle gotBasket = getIntent().getExtras();

		abolist = (ArrayList<Abo>) gotBasket.getSerializable("abolist");
		position = gotBasket.getInt("position");
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		Tab = (ViewPager) findViewById(R.id.pager);
		Tab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@SuppressLint("NewApi")
			@Override
			public void onPageSelected(int position) {
				actionBar = getActionBar();
				actionBar.setSelectedNavigationItem(position);
			}
		});
		Tab.setAdapter(TabAdapter);
		actionBar = getActionBar();
		// Enable Tabs on Action Bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@SuppressLint("NewApi")
			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				Tab.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
		};
		// Add New Tab
		actionBar.addTab(actionBar.newTab().setText("Bellen")
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("SMS")
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Data")
				.setTabListener(tabListener));

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public int getPosition() {
		return position;
	}

	public ArrayList<Abo> getAbolist() {
		return abolist;
	}
}
