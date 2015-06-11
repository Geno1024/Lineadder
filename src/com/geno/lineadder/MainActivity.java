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
	public int[] charcode;
	public String total = "";
	public String chars = "";
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		charcode = new int[0xE2];
		/*	Name 		*	Position 	*	Length	*	Sum
		 *				*				*	(Hex)	*
		 *				*				*			*
		 *	Combining	*	0300-036F	*	70		*	0
		 *	Cyrillic	*	0483-0489	*	7		*	70
		 *	Hebrew		*	0591-05C7	*	37		*	77
		 *	Arabic		*	0610-061A	*	B		*	AE
		 *				*				*			*
		 *				*	064B-065F	*	15		*	B7
		 *				*	06D6-06ED	*	18		*	CB
		 *	Syriac		*	0730-074A	*	1B		*	E2
		 *	Thaana		*	07A6-07B0	*	B		*	FC
		 *				*				*			*
		 *	NKo			*	07EB-07F3	*	A		*	106
		 *	Samaritan	*	0816-082D	*	1A		*	10F
		 *	Mandaic		*	0859-085B	*	3		*	128
		 *	ArabExtA	*	08E3-08FF	*	1D		*	12B
		 *				*				*			*
		 *	Devanagari	*	0900-0903	*	4		*	148
		 *				*	093A-0957	*	1B		*	14C
		 *	Thai		*	0E31-0E4F	*	1E		*	167
		 *	Tibetan		*	0F70-0FDA	*	6A		*	185
		 *				*				*			*
		 *	Myanmar		*	102B-109D	*	2		*	1FF
		 *	Tai Tham	*	1A55-1A7F	*			*
		 *	CDEExt		*	1AB0-1ABE	*			*
		 *	Lepcha		*	1C24-1C37	*			*
		 *				*				*			*
		 *	VedicExt	*	1CD0-1CFF	*			*
		 *	CDMSupple	*	1DC0-1DFF	*			*
		 *	CDMSymbol	*	20D0-20FF	*			*
		 *	CyrillicExt	*	2DE0-2DFF	*			*
		 **/
		for(int i = 0; i < 0x70; i++){charcode[i] = i + 0x0300;}
		for(int i = 0; i < 0x7; i++){charcode[0x70 + i] = i + 0x0483;}
		for(int i = 0; i < 0x36; i++){charcode[0x77 + i] = i + 0x0591;}
		for(int i = 0; i < 0xA; i++){charcode[0xAD + i] = i + 0x0610;}

		for(int i = 0; i < 0x14; i++){charcode[0xB7 + i] = i + 0x064B;}
		for(int i = 0; i < 0x17; i++){charcode[0xCB + i] = i + 0x06D6;}
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
				return charcode.length;
			}

			@Override
			public Object getItem(int p1)
			{
				return charcode[p1];
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
				String s = " ";
				for(int i=0;i<5;i++)
					s=s+Character.toChars(charcode[position])[0]+" ";
				s="0x"+Integer.toHexString(charcode[position]).toUpperCase()+" "+s;
				LinearLayout l = new LinearLayout(MainActivity.this);
				TextView t = new TextView(MainActivity.this);
				t.setText(s+Character.getName(charcode[position]));
				t.setTextSize(15);
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
				String s = " ";
				for(int i=0;i<5;i++)
					s=s+Character.toChars(charcode[p1])[0]+" ";
				s="0x"+Integer.toHexString(charcode[p1]).toUpperCase()+" "+s;
				LinearLayout l = new LinearLayout(MainActivity.this);
				TextView t = new TextView(MainActivity.this);
				t.setText(s+Character.getName(charcode[p1]));
				t.setTextSize(15);
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
					chars=String.valueOf(Character.toChars(charcode[p3])[0]);
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
					((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setText(((EditText)findViewById(R.id.in)).getText().toString());
				}
			}
		);
	}
}

