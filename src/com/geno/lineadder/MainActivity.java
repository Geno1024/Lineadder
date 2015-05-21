package com.geno.lineadder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;

public class MainActivity extends Activity
{
	public Button delout;
	public EditText delin;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		OnClickListener o = new OnClickListener()
		{
			@Override
			public void onClick(View p1)
			{
				String total = "";
				String chars = "";
				switch(p1.getId())
				{
					case R.id.delout:
						chars="\u0336";
						break;
					case R.id.slsout:
						chars="\u0338";
						break;
				}
				String s=((EditText)findViewById(p1.getId()-1)).getText().toString();
				for(int i = 0;i < s.length();i++)
				{
					total=total+s.charAt(i)+chars;
				}
				((EditText)findViewById(p1.getId()-1)).setText(total);
				ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				c.setText(total);
			}
		};
		((Button)findViewById(R.id.delout)).setOnClickListener(o);
		((Button)findViewById(R.id.slsout)).setOnClickListener(o);
    }
}
