package com.hama.carmanager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hama.vo.Record;
import com.hama.vo.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class QueryRecord extends Activity {

	private ListView listView;
	List<Record> res = new ArrayList<Record>();
	List<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent datas = this.getIntent();
		String json = datas.getStringExtra("JSON");
		User user = (User) datas.getSerializableExtra("user");
		listView = new ListView(this);
		parseJson(json);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list));
		listView.setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
//						String num = String.valueOf(arg2);
//						Toast.makeText(QueryRecord.this ,num , Toast.LENGTH_SHORT).show();
						Record record = res.get(arg2);
						Intent intent = new Intent(QueryRecord.this,Detail.class);
						intent.putExtra("record", record);
						startActivity(intent);
					}

				}
				);
		setContentView(listView);
	}

	public void parseJson(String json) {
		try {
			//			System.out.println(json);
			JSONObject object = new JSONObject(json);
			JSONArray array = object.getJSONArray("content");

			for(int i = 0; i < array.length(); i ++) {
				Record record = new Record();
				JSONArray array2 = (JSONArray)array.get(i);
				//				System.out.println("birth year : " + array2.get(0));
				record.setBirthYear(array2.getString(0));
				//				System.out.println("ensure year : " + array2.get(1));
				record.setEnsureYear(array2.getInt(1));
				//				System.out.println("label : " + array2.get(2));
				record.setLabel(array2.getString(2));
				//				System.out.println("agent : " + array2.get(3));
				record.setAgent(array2.getString(3));
				res.add(record);
				list.add(array2.getString(0));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.query_record, menu);
		return true;
	}

}
