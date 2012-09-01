package com.mustafa.finaldistributed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.basic);
	Class ourClass;
	try {
		ourClass = Class.forName("com.mustafa.finaldistributed.ABOUT");
		Intent ourIntent = new Intent(About.this, ourClass);
		startActivity(ourIntent);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
	}
	

}
