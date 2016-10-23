package com.example.gps;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener  {
	
	
	
	public static boolean pressed=false;//����������� ��� ��������� ��� �� ���� �� �� service ����� ������ � ���
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       Button button=(Button)this.findViewById(R.id.button1);
       button.setOnClickListener(this);
       Button button2=(Button)this.findViewById(R.id.button2);
       button2.setOnClickListener(this);
       
    }
	@Override
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	 Intent i=new Intent();
	  switch(v.getId()){
		  case R.id.button1:
				i.setClassName("com.example.locationservices","com.example.locationservices.LocationServices");//�� intent ��� ��  service
				if(pressed == false){//�� �� ������ ��� ����� �������� ������ �� servcice
			     startService(i);
			     pressed=true;
			     break;
			    }
				else {
			     if(stopService(i)){//��� �� �� ��� ����� ���� ���������� �� service
			    	 pressed=false;
			    	 break;
			     }else{
			    	 Toast.makeText(this, "Service dindnt stop please try again", Toast.LENGTH_LONG).show();//�� ��� ���� ���������� ���� �� ������ ������
			        break;
			     }
			    }
		  case R.id.button2:
			  i.setClassName("com.example.gps","com.example.gps.ShowLocations");//�� intent ��� �� �������� ��� �������������� ����������
			  startActivity(i);
	  }
		
	}
	
	
	
}

