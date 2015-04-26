package com.hama.carmanager;

import com.hama.vo.Record;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Detail extends Activity {
	
	private Intent intent_In;
	private Record record;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		intent_In = this.getIntent();
		record = (Record)intent_In.getSerializableExtra("record");
		
		text_1 = (TextView) this.findViewById(R.id.detail_01);
		text_2 = (TextView) this.findViewById(R.id.detail_02);
		text_3 = (TextView) this.findViewById(R.id.detail_03);
		text_4 = (TextView) this.findViewById(R.id.detail_04);
		
		text_1.setText(record.getBirthYear());
		text_2.setText(record.getAgent());
		text_3.setText(record.getLabel());
		text_4.setText(String.valueOf(record.getEnsureYear()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
