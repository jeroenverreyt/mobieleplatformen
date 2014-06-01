package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Abo> {

	private ArrayList<Abo> objects;
	
	public ItemAdapter(Context context, int textViewResourceId, ArrayList<Abo> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		Abo i = objects.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView t = (TextView) v.findViewById(R.id.tvProvider);
			TextView tt = (TextView) v.findViewById(R.id.tvBundel);
			TextView ttd = (TextView) v.findViewById(R.id.tvPrice);
			

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (t != null){
				t.setText(i.getProvider());
			}
			if (tt != null){
				tt.setText(i.getName());
			}
			if (ttd != null){
				ttd.setText(i.getPrice()+ " EURO");
			}
			
		}

		// the view must be returned to our activity
		return v;

	}

}

