package com.hmkcode.android;

import com.hmkcode.android.model.Contact;
import com.hmkcode.android.sqlite.MySQLiteHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {

	EditText fNameField;
	EditText lNameField;
	EditText addressField;
	EditText emailField;
	EditText phoneField;
	MySQLiteHelper db;
	private static final String TAG = "com.hmkcode.android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		db = new MySQLiteHelper(this);

		Button submit = (Button)findViewById(R.id.submitButton);
		Button cancel = (Button)findViewById(R.id.cancelButton);
		fNameField = (EditText)findViewById(R.id.fNameEditText);
		lNameField = (EditText)findViewById(R.id.lNameEditText);
		addressField = (EditText)findViewById(R.id.postalAddressEditText);
		emailField = (EditText)findViewById(R.id.emailEditText);
		phoneField = (EditText)findViewById(R.id.phoneEditText);

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!anyFieldEmpty()) {
					addNewContact();
					Toast.makeText(getApplicationContext(), "Contact Created", Toast.LENGTH_LONG).show();
					emptyFields();
				} else {
					Toast.makeText(getApplicationContext(), "Submit Failed. Fill in all Fields.", Toast.LENGTH_LONG).show();
				}
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v(TAG, "Cancelling and returning to home screen");
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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

	public boolean anyFieldEmpty() {
		if(fNameField.getText().toString().length()==0 ||
				lNameField.getText().toString().length()==0 ||
				addressField.getText().toString().length()==0 ||
				emailField.getText().toString().length()==0 ||
				phoneField.getText().toString().length()==0) {
			return true;
		}
		return false;
	}

	public void emptyFields() {
		fNameField.setText("");
		lNameField.setText("");
		addressField.setText("");
		emailField.setText("");
		phoneField.setText("");
	}

	public void addNewContact() {
		db.addContact(new Contact(fNameField.getText().toString(),
									lNameField.getText().toString(),
									addressField.getText().toString(),
									emailField.getText().toString(),
									phoneField.getText().toString()));
	}
}
