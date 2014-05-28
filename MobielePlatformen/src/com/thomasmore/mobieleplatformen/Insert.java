package com.thomasmore.mobieleplatformen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Insert extends Activity implements OnClickListener {
	private EditText etName, etPrice;
	private Button bNext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertnew);
		
		etName = (EditText)findViewById(R.id.etNewName);
		etPrice = (EditText)findViewById(R.id.etNewPrice);
		
		bNext = (Button)findViewById(R.id.bNext);
		bNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FormValidation val = new FormValidation();
		String name = etName.getText().toString();
		String price = etPrice.getText().toString();
		if(name.isEmpty()){
			etName.setError("Gelieve een naam in te vullen!");
		}else if (price.isEmpty()){
			etPrice.setError("Gelieve een prijs in te vullen!");
		}else if(!val.isStringNumeric(price)){
			etPrice.setError("Prijs moet een getal zijn!");
		}else if(!val.isPositive(price)){
			etPrice.setError("Prijs moet een positief getal zijn!");
			
		}else{
			Bundle b = getIntent().getExtras();
			String provider = b.getString("provider");
			Log.d("basket", "insert: " + provider);
			Bundle basket = new Bundle();
			basket.putString("name", name);
			basket.putString("price", price);
			basket.putString("provider", provider);
			Intent i = new Intent(Insert.this, InsertSms.class);
			i.putExtras(basket);
			
			startActivity(i);
		}
		
		
		
		
	}

	
}
