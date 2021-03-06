package com.example.androidclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PublicKey;

import android.support.v7.app.ActionBarActivity;
import android.text.StaticLayout;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{
	
	String str;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Thread(){
			public void run(){
			        try {
			        	Socket s = new Socket("192.168.1.102",9999);
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
						try {
							str = (String)ois.readObject();
							System.out.println(str);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}				  
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
			}
		}.start();
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
