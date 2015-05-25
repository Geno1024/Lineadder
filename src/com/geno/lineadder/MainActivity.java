package com.geno.lineadder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;
import android.database.*;
import android.graphics.*;

public class MainActivity extends Activity
{
	public Button delout;
	public EditText delin;
	public String[] charname,charcode;
	public String total = "";
	public String chars = "";
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		charname = getResources().getStringArray(R.array.charname);
		charcode = new String[112];
		for(int i = 0;i < 112;i++)
		{
			charcode[i]=String.valueOf(Character.toChars(i+0x0300)[0]);
		}
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
			public View getView(int position, View convertView, ViewGroup parent)
			{
				String s = "   ";
				for(int i=0;i<5;i++)
					s=s+charcode[position]+" ";
				s=s+" 0x0"+Integer.toHexString(position+0x0300).toUpperCase();
				LinearLayout l = new LinearLayout(MainActivity.this);
				TextView t = new TextView(MainActivity.this);
				t.setText(charname[position]+s);
				t.setTextSize(20);
				t.setPadding(20,20,0,20);
				t.setTypeface(Typeface.MONOSPACE);
				l.addView(t,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
				return l;
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
				String s = "   ";
				for(int i=0;i<5;i++)
					s=s+charcode[p1]+" ";
				s=s+" 0x0"+Integer.toHexString(p1+0x0300).toUpperCase()+"    Debug:"+(p1+5);
				LinearLayout l = new LinearLayout(MainActivity.this);
				TextView t = new TextView(MainActivity.this);
				t.setText(charname[p1]+s);
				t.setTextSize(20);
				t.setTypeface(Typeface.MONOSPACE);
				t.setPadding(20,20,0,20);
				l.addView(t,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
				return l;
			}
		};
		s.setAdapter(a);
		s.setOnItemSelectedListener
		(new AdapterView.OnItemSelectedListener()
			{

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					chars=charcode[p3];
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}
			}
		);	
		((Button)findViewById(R.id.out)).setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					String res="";
					total=((EditText)findViewById(R.id.in)).getText().toString();
					for(int i = 0;i<total.length();i++)
						res=res+total.charAt(i)+chars;
					((EditText)findViewById(R.id.in)).setText(res);
				}
			}
		);
	}
}

