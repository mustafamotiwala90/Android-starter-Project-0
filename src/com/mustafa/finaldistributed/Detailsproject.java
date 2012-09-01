package com.mustafa.finaldistributed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Detailsproject extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.basic1);
		Class ourClass;
		try {
			ourClass = Class.forName("com.mustafa.finaldistributed.DETAILSPROJECT");
			Intent ourIntent = new Intent(Detailsproject.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
	
}
