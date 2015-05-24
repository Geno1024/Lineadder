package com.geno.lineadder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;
import android.database.*;

public class MainActivity extends Activity
{
	public Button delout;
	public EditText delin;
	public String[] charname,charcode;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		charname = new String[]{"Underline"};
		charcode = new String[]{"\u0330"};
		Spinner s = (Spinner)findViewById(R.id.selector);
		SpinnerAdapter a = new SpinnerAdapter()
		{
			@Override
			public void registerDataSetObserver(DataSetObserver p1)
			{
			}

			@Override
			public void unregisterDataSetObserver(DataSetObserver p1)
			{
			}

			@Override
			public int getCount()
			{
				return charname.length;
			}

			@Override
			public Object getItem(int p1)
			{
				return charname[p1];
			}

			@Override
			public long getItemId(int p1)
			{
				return 0;
			}

			@Override
			public boolean hasStableIds()
			{
				return false;
			}

			@Override
			public View getView(int p1, View p2, ViewGroup p3)
			{
				return null;
			}

			@Override
			public int getItemViewType(int p1)
			{
				return 0;
			}

			@Override
			public int getViewTypeCount()
			{
				return 1;
			}

			@Override
			public boolean isEmpty()
			{
				return false;
			}

			@Override
			public View getDropDownView(int p1, View p2, ViewGroup p3)
			{
				return null;
			}
		};
		s.setAdapter(a);
		OnClickListener o = new OnClickListener()
		{
			@Override
			public void onClick(View p1)
			{
				String total = "";
				String chars = "";
				switch(p1.getId())
				{
					case R.id.udwout:
						chars="\u0330";
						break;
					case R.id.udlout:
						chars="\u0332";
						break;
					case R.id.wavout:
						chars="\u0334";
						break;
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
		((Button)findViewById(R.id.udwout)).setOnClickListener(o);
		((Button)findViewById(R.id.udlout)).setOnClickListener(o);
		((Button)findViewById(R.id.wavout)).setOnClickListener(o);
		((Button)findViewById(R.id.delout)).setOnClickListener(o);
		((Button)findViewById(R.id.slsout)).setOnClickListener(o);
    }
}

