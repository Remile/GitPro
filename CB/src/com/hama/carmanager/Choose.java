package com.hama.carmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hama.vo.User;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Choose extends Activity implements OnClickListener {

	Button test = null;
	Button query = null;
	User user = null;
	private HttpClient client;
	private HttpGet get;
	private HttpResponse response;
	BufferedReader reader;
	StringBuffer sb;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch(msg.what) {
			case 1:
				System.out.println("收到错误信息，准备发出提示");
				Toast.makeText(Choose.this, "连接错误，请检查网络", Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);

		user = (User) this.getIntent().getSerializableExtra("user");

		test = (Button) this.findViewById(R.id.test_1);
		test.setOnClickListener(this);

		query = (Button) this.findViewById(R.id.query_1);
		query.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		System.out.println("开始查询工作");
		Intent intent = null;
		// TODO Auto-generated method stub
		if(v == test) {
			intent = new Intent(this, Scan.class);
			intent.putExtra("user", user);
			startActivity(intent);
		}else if(v == query) {
			intent = new Intent(this, QueryRecord.class);
			intent.putExtra("user", user);
			String url = "http://192.168.0.104:8080/codeCheck/servlet/ReturnContent?userName=lmk";
			client = new DefaultHttpClient();
			get = new HttpGet(url);
			new Thread(runnable).start();
			//			startActivity(intent);
		}
	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			try {
				response = client.execute(get);
				reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				sb = new StringBuffer();
				String line = "";
				while((line = reader.readLine())!= null) {
					sb.append(line);
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(reader != null) {
						reader.close();
						reader = null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(sb == null) {
				handler.sendEmptyMessage(1);
			}else {
				String res = sb.toString();
				if((res != null)&&(!res.trim().equals(""))) {
					Intent intent = new Intent(Choose.this, QueryRecord.class);
					intent.putExtra("user", user);
					intent.putExtra("JSON", sb.toString());
					Choose.this.startActivity(intent);
				}else {
					//				Toast.makeText(Choose.this, "Connection error!", Toast.LENGTH_SHORT).show();
					handler.sendEmptyMessage(1);
				}
			}
		}

	};

}
