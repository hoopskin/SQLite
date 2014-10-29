package com.hmkcode.android;

import java.util.ArrayList;
import java.util.List;

import com.hmkcode.android.model.Contact;
import com.hmkcode.android.sqlite.MySQLiteHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class FourthActivity extends Activity {

	TextView queryResultsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);

		/*TODO: Force landscape somehow?
		Configuration config = getResources().getConfiguration();
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
		*/

		queryResultsTextView = (TextView)findViewById(R.id.queryResultsTextView);
		//Clearing it out incase they left and came back
		queryResultsTextView.setText("");
		queryResultsTextView.setMovementMethod(new ScrollingMovementMethod());

		if(getIntent().getStringExtra("activity").equalsIgnoreCase("main")) {
			//Came from home page, show all results
			MySQLiteHelper db = new MySQLiteHelper(this);

			queryResultsTextView.setText(createResultsText(db.getAllContacts()));
		} else if (getIntent().getStringExtra("activity").equalsIgnoreCase("third")) {
			queryResultsTextView.setText(getIntent().getStringExtra("resultsText"));
		}
		if(queryResultsTextView.getText().toString().trim().length() == 0) {
			queryResultsTextView.setText("No results found, please go back and either change your query or add more contacts.");
		}
	}

	public String createResultsText(List<Contact> queryResultsContacts) {
		String result = "";
		for (Contact qrContact : queryResultsContacts) {
			int id = qrContact.getId();
			result = result + qrContact.toString() + "\n";
		}
		return result;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fourth, menu);
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
