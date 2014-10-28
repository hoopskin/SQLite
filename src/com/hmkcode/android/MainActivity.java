package com.hmkcode.android;

import java.util.List;

import com.hmkcode.android.model.Contact;
import com.hmkcode.android.sqlite.MySQLiteHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {

	Button addNewContact;
	Button searchContacts;
	Button displayAllContacts;
	Button clearDB;
	private static final String TAG = "com.hmkcode.android";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final MySQLiteHelper db = new MySQLiteHelper(this);
        
		
		addNewContact = (Button)findViewById(R.id.addNewContact);
		searchContacts = (Button)findViewById(R.id.searchContacts);
		displayAllContacts = (Button)findViewById(R.id.displayAllContacts);
		clearDB = (Button)findViewById(R.id.clearDB);
		
		addNewContact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), SecondActivity.class));
				Log.v(TAG, "Going to Add New Contact");
			}
		});
		
		searchContacts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
				Log.v(TAG, "Going to Search Contacts");
			}
		});
		
		displayAllContacts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, FourthActivity.class);
				intent.putExtra("activity", "main");
				startActivity(intent);
				Log.v(TAG, "Going to Display All Contacts");
				
			}
		});
		
		clearDB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<Contact> allContacts = db.getAllContacts();
				for(Contact contact : allContacts) {
					db.deleteContact(contact);
				}
				Log.v(TAG, "After all contacts deleted");
				db.getAllContacts();
			}
		});
		
		
        /**
         * CRUD Operations
         * */
        /*
		// add Contacts
        db.addContact(new Contact("John","Doe","123 Main St.","john.doe@gmail.com","(123) 456-7890"));   
        db.addContact(new Contact("Jane","Doe","123 Main St.","jane.doe@gmail.com","(098) 765-4321"));   
        db.addContact(new Contact("Yellow","Paper","808 Second St.","y.p@gmail.com","(111) 222-3333"));   
        
        // get all books
        List<Contact> list = db.getAllContacts();
        
        // delete one book
        db.deleteContact(list.get(0));
        
        // get all books
        db.getAllContacts();
		*/
        
	}

}
