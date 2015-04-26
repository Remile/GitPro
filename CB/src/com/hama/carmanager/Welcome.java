package com.hama.carmanager;

import com.hama.Service.LoginService;
import com.hama.Service.impl.LoginServiceImpl;
import com.hama.vo.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Welcome extends Activity implements OnClickListener {
	
	EditText userName_In = null;
	EditText passWord_In = null;
	Button submit = null;
	
	String userName = "";
	String passWord = "";

	User user = null;
	
	LoginService service = new LoginServiceImpl();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login2);
		
		userName_In = (EditText) this.findViewById(R.id.userName_1);
		passWord_In = (EditText) this.findViewById(R.id.passWord_1);
	
		submit = (Button) this.findViewById(R.id.login_1);
		submit.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submit) {
			System.out.println("ÊÕµ½µÇÂ½ÇëÇó");
			userName = userName_In.getText().toString();
			passWord = passWord_In.getText().toString();
			user = new User(userName, passWord);
			if(service.Login(user)) {
				Intent intent = new Intent(this, Choose.class);
				intent.putExtra("user", user);
				startActivity(intent);
			}
		}
	}

}
