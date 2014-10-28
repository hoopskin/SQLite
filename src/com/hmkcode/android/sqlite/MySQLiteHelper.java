package com.hmkcode.android.sqlite;

import java.util.LinkedList;
import java.util.List;

import com.hmkcode.android.model.Book;
import com.hmkcode.android.model.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ContactDB";
   
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_CONTACT_TABLE = "CREATE TABLE contacts ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"fName TEXT, "+
				"lName TEXT, "+
				"address TEXT, "+
				"email TEXT, "+
				"phone TEXT)";
		
		// create books table
		db.execSQL(CREATE_CONTACT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS contacts");
        
        // create fresh books table
        this.onCreate(db);
	}
	//---------------------------------------------------------------------
   
	/**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
	
	// Books table name
    private static final String TABLE_CONTACTS = "contacts";
    
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "fName";
    private static final String KEY_LNAME = "lName";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    
    private static final String[] COLUMNS = {KEY_FNAME,KEY_LNAME,KEY_ADDRESS,KEY_EMAIL,KEY_PHONE};
    
	public void addContact(Contact contact){
		Log.d("addContact", contact.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, contact.getfName()); 		// get fName
        values.put(KEY_LNAME, contact.getlName()); 		// get lName
        values.put(KEY_ADDRESS, contact.getAddress());	// get address
        values.put(KEY_EMAIL, contact.getEmail());		// get email
        values.put(KEY_PHONE, contact.getPhone());		// get phone
 
        // 3. insert
        db.insert(TABLE_CONTACTS, // table
        		null, //nullColumnHack
        		values); // key/value -> keys = column names/ values = column values
        
        
        // 4. close
        db.close(); 
	}
	
	public Contact getContact(int id){

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. build query
        Cursor cursor = 
        		db.query(TABLE_CONTACTS, // a. table
        		COLUMNS, // b. column names
        		" id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build contact object
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setfName(cursor.getString(1));
        contact.setlName(cursor.getString(2));
        contact.setAddress(cursor.getString(3));
        contact.setEmail(cursor.getString(4));
        contact.setPhone(cursor.getString(5));

		Log.d("getContact("+id+")", contact.toString());

        // 5. return book
        return contact;
	}
	
	public List<Contact> searchContacts(String query) {
		List<Contact> contacts = new LinkedList<Contact>();

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 2. go over each row, build book and add it to list
        Contact contact = null;
        if (cursor.moveToFirst()) {
            do {
            	contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setfName(cursor.getString(1));
                contact.setlName(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
                contact.setPhone(cursor.getString(5));

                // Add contact to contacts
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        
		Log.d("searchContacts()", contacts.toString());

        // return contacts
        return contacts;
	}
	
	// Get All Books
    public List<Contact> getAllContacts() {
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CONTACTS;
        return searchContacts(query);
    }
	
	 // Updating single contact
    public int updateContact(Contact contact) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("fName", contact.getfName()); 		// get fName
        values.put("lName", contact.getlName()); 		// get lName
        values.put("address", contact.getAddress()); 	// get address
        values.put("email", contact.getEmail()); 		//get email
        values.put("phone", contact.getPhone()); 		//get phone
        
        // 3. updating row
        int i = db.update(TABLE_CONTACTS, //table
        		values, // column/value
        		KEY_ID+" = ?", // selections
                new String[] { String.valueOf(contact.getId()) }); //selection args
        
        // 4. close
        db.close();
        
        return i;
        
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        
        // 2. delete
        db.delete(TABLE_CONTACTS,
        		KEY_ID+" = ?",
                new String[] { String.valueOf(contact.getId()) });
        
        // 3. close
        db.close();
        
		Log.d("deleteContact", contact.toString());

    }
}
