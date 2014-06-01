package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InsertData extends Activity implements OnClickListener {

	private EditText etData, etFreeData;
	private Button bFinish;
	private JSONParser jsonParser = new JSONParser();
	private static final String INSERT_URL = "http://www.abochecker.tk/webservice/insert.php";
	private String name, provider, price, sms, freeSmsEn, freeSmsAn, call,
			freeCallEn, freeCallAn, callInternational, freeCallType,
			freeSmsType, data, freeData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertdata);

		etData = (EditText) findViewById(R.id.etNewData);
		etFreeData = (EditText) findViewById(R.id.etNewFreeData);

		bFinish = (Button) findViewById(R.id.bInsertNew);
		bFinish.setOnClickListener(this);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Bundle b = getIntent().getExtras();
		name = b.getString("name");
		provider = b.getString("provider");
		price = b.getString("price");
		sms = b.getString("sms");
		freeSmsEn = b.getString("freeSmsEn");
		freeSmsAn = b.getString("freeSmsAn");
		call = b.getString("call");
		freeCallEn = b.getString("freeCallEn");
		freeCallAn = b.getString("freeCallAn");
		callInternational = b.getString("callInternational");
		freeCallType = b.getString("freeCallType");
		freeSmsType = b.getString("freeSmsType");
		data = etData.getText().toString();
		freeData = etFreeData.getText().toString();

		FormValidation val = new FormValidation();
		if (data.isEmpty()) {
			etData.setError("Gelieve een waarde in te geven");
		} else if (freeData.isEmpty()) {
			etFreeData.setError("Gelieve een waarde in te geven");

		} else if (!val.isStringNumeric(data)) {
			etData.setError("Voer een correct getal in");
		} else if (!val.isStringNumeric(freeData)) {
			etFreeData.setError("Voer een correct getal in");
		} else if (!val.isPositive(data)) {
			etData.setError("Getal moet positief zijn");
		} else if (!val.isPositive(freeData)) {
			etFreeData.setError("Getal moet positief zijn");
		} else {

			new insert().execute();
			Bundle bundle = new Bundle();
			bundle.putString("provider", provider);

			Intent i = new Intent(InsertData.this, ProviderList.class);
			i.putExtras(bundle);
			startActivity(i);

		}
	}

	class insert extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("newName", name));
			params.add(new BasicNameValuePair("provider", provider));
			params.add(new BasicNameValuePair("price", price));
			params.add(new BasicNameValuePair("sms", sms));
			params.add(new BasicNameValuePair("freeSmsEn", freeSmsEn));

			params.add(new BasicNameValuePair("freeSmsAn", freeSmsAn));
			params.add(new BasicNameValuePair("call", call));
			params.add(new BasicNameValuePair("freeCallEn", freeCallEn));
			params.add(new BasicNameValuePair("freeCallAn", freeCallAn));
			params.add(new BasicNameValuePair("callInternational",
					callInternational));

			params.add(new BasicNameValuePair("freeCallType", freeCallType));
			params.add(new BasicNameValuePair("freeSmsType", freeSmsType));
			params.add(new BasicNameValuePair("data", data));
			params.add(new BasicNameValuePair("freeData", freeData));

			// hier voer je de POST uit op de webservice
			JSONObject json = jsonParser.makeHttpRequest(INSERT_URL, "POST",
					params);
			// de waarde uit de JSONData wordt hier gehaald
			try {
				int succes = json.getInt("succes");
				String query = json.getString("query");

				Log.d("json", "succes: " + succes);
				Log.d("json", "query: " + query);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("json", "failed");
				e.printStackTrace();
			}

			return null;
		}

	}
}
