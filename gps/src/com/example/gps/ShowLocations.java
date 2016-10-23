package com.example.gps;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ShowLocations extends Activity {	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
       setContentView(R.layout.layout);
       
       TableLayout tl=(TableLayout)findViewById(R.id.maintable);
       TableRow tr=new TableRow(this);
       TextView text=new TextView(this);
       TextView text2=new TextView(this);
       TextView text3=new TextView(this);
       
       
       text.setText("Longitude");
       text2.setText("Latitude");
       text3.setText("Timestamp");
       
       tr.addView(text);
       tr.addView(text2);
       tr.addView(text3);
       
       tl.addView(tr);
       try{
       Uri uri=Uri.parse("content://location_provider/Locations");
	   Cursor mCursor=getApplicationContext().getContentResolver().query(uri, null, null, null, null);
	   if(mCursor.moveToFirst()){
		   do{
		       TableRow newRow=new TableRow(this);
		       TextView new_text1=new TextView(this);
		       TextView new_text2=new TextView(this);
		       TextView new_text3=new TextView(this);
		       
		        
		       new_text1.setText(mCursor.getDouble(1)+"");
		       new_text2.setText(mCursor.getDouble(2)+"");
		       new_text3.setText(mCursor.getLong(3)+"");
		       
		       new_text1.setTextSize(TypedValue.COMPLEX_UNIT_SP , 9);
		       new_text2.setTextSize(TypedValue.COMPLEX_UNIT_SP , 9);
		       new_text3.setTextSize(TypedValue.COMPLEX_UNIT_SP , 9);
		       
		       newRow.addView(new_text1);
		       newRow.addView(new_text2);
		       newRow.addView(new_text3);
		      
		       tl.addView(newRow);
		   }while(mCursor.moveToNext());
		}
       }catch(Exception ex){
    	   Toast.makeText(getApplicationContext(), "Could not resolve  the uri content://location_provider/Locations", Toast.LENGTH_LONG).show();//σε περίπτωση λάθους για το οποίο πιθανότατα θα φταίει ο provider δηλαδή κάτι με το uri θα ενημερώνεται ο χρήστης 
       }
       
   }
}
