package com.catatan.x;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.webkit.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.provider.*;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;



public class About extends AppCompatActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Tentang Catatan X");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		

	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
}
}
