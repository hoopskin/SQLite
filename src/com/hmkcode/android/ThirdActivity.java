package com.hmkcode.android;

import java.util.ArrayList;
import java.util.List;

import com.hmkcode.android.model.Contact;
import com.hmkcode.android.sqlite.MySQLiteHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends Activity {

	EditText fNameField;
	EditText lNameField;
	EditText addressField;
	EditText emailField;
	EditText phoneField;
	String phoneFieldText;
	String emailFieldText;
	String addressFieldText;
	String lNameFieldText;
	String fNameFieldText;
	MySQLiteHelper db;
	private static final String TAG = "com.hmkcode.android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		Button searchButton = (Button)findViewById(R.id.searchButton);
		final MySQLiteHelper db = new MySQLiteHelper(this);
		fNameField = (EditText)findViewById(R.id.fNameEditText);
		lNameField = (EditText)findViewById(R.id.lNameEditText);
		addressField = (EditText)findViewById(R.id.postalAddressEditText);
		emailField = (EditText)findViewById(R.id.emailEditText);
		phoneField = (EditText)findViewById(R.id.phoneEditText);

		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(allFieldsEmpty()) {
					Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
					//If FourthActivity gets activity = main, it displays all contacts
					intent.putExtra("activity", "main");
					startActivity(intent);
				} else {
					getTexts();
					List<Contact> results = db.searchContacts(fNameFieldText, lNameFieldText, addressFieldText, emailFieldText, phoneFieldText);
					Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
					intent.putExtra("activity", "third");
					intent.putExtra("resultsText", createResultsText(results));
					startActivity(intent);
					Log.v(TAG, "Going to display search results");
				}
			}
		});

	}

	public boolean allFieldsEmpty() {
		if(fNameField.getText().toString().length()==0 &&
				lNameField.getText().toString().length()==0 &&
				addressField.getText().toString().length()==0 &&
				emailField.getText().toString().length()==0 &&
				phoneField.getText().toString().length()==0) {
					return true;
				}
				return false;
	}

	public String createResultsText(List<Contact> results) {
		// TODO Auto-generated method stub
		String resultsText = "";
		for(Contact qrContact : results) {
			resultsText = resultsText + qrContact.toString() + "\n";
		}
		return resultsText;
	}

	public void getTexts() {

		if(fNameField.getText()==null) {
			fNameFieldText="";
		} else {
			fNameFieldText=fNameField.getText().toString();
		}

		if(lNameField.getText()==null) {
			lNameFieldText="";
		} else {
			lNameFieldText=lNameField.getText().toString();
		}

		if(addressField.getText()==null) {
			addressFieldText="";
		} else {
			addressFieldText=addressField.getText().toString();
		}

		if(emailField.getText()==null) {
			emailFieldText="";
		} else {
			emailFieldText=emailField.getText().toString();
		}

		if(phoneField.getText()==null) {
			phoneFieldText="";
		} else {
			phoneFieldText=phoneField.getText().toString();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
