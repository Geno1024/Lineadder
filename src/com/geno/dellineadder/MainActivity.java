package com.geno.dellineadder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;

public class MainActivity extends Activity
{
	public Button b;
	public EditText e;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		e=(EditText) findViewById(R.id.in);
		b=(Button) findViewById(R.id.out);
		b.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					String o="̶";
					String s=e.getText().toString();
					for(int i = 0;i < s.length();i++)
					{
						o=o+s.charAt(i)+"̶";
					}
					e.setText(o);
					ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
					c.setText(o);
				}
			}
		);
    }
}
