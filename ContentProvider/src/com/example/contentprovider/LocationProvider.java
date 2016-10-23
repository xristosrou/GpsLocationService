package com.example.contentprovider;


//ακολούθησα την δική σας λογική οπότε πιστέυω ότι τα σχόλια είναι περιττά
//άφησα 2 τύπους uri αν και ξέρω ότι ένας δεν χρειάζεται,δεν είναι ότι το έκανα copy paste απλώς δεν το θεωρώ σπατάλη και για αυτό δεν το έσβησα
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.UriMatcher;

public class LocationProvider extends ContentProvider {

	private LocationHelper lhelper;
	
	private final static String AUTHORITY="location_provider";
	private final static String PATH="Locations";
	private static UriMatcher sUriMathcer=new  UriMatcher(UriMatcher.NO_MATCH);
	
	static{
		sUriMathcer.addURI(AUTHORITY, PATH, 1);
		sUriMathcer.addURI(AUTHORITY,PATH+"/#",2); 
	}
	
	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		lhelper=new LocationHelper(this.getContext());
		return false;
	}
	
	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		
		long id=0;
		switch(sUriMathcer.match(arg0)){
		 case 1:
			id=lhelper.insert(arg1);
			break;
		 case 2:
			id=-1;
			break;
		
		}
		String retVal=arg0.getAuthority()+"/"+arg0.getPath() +"/" +id;
		return arg0.parse(retVal);
	}
	
	
	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		switch(sUriMathcer.match(arg0)){
		case 1:
			arg0=null;
			arg1=null;
			arg2=null;
			arg3=null;
			break;
		case 2:
		   arg2="_ID=?";
		   arg3[0]=arg0.getLastPathSegment();
		}
		Cursor cursor=lhelper.query(arg1, arg2, arg3 ,arg4);
		return cursor;
	}
	
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
