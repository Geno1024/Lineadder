package com.geno.lineadder;

import android.annotation.SuppressLint;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;
import android.database.*;
import android.graphics.*;

@SuppressLint("NewApi") 
public class MainActivity extends Activity
{
	public Button delout;
	public EditText delin;
	public int[] charcode;
	public String total = "";
	public String chars = "";
	public int[] len, pos;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        len = new int[]{0x0300 - 0x036F , 0x0483 - 0x0489 , 0x0591 - 0x05C7 , 0x0610 - 0x061A ,
        				0x064B - 0x065F , 0x06D6 - 0x06ED , 0x0730 - 0x074A , 0x07A6 - 0x07B0 ,
        				0x07EB - 0x07F3 , 0x0816 - 0x082D , 0x0859 - 0x085B , 0x08E3 - 0x08FF ,
        				0x0900 - 0x0903 , 0x093A - 0x0957 , 0x0E31 - 0x0E4F , 0x0F70 - 0x0FDA ,
        				0x102B - 0x109D , 0x1A55 - 0x1A7F , 0x1AB0 - 0x1ABE , 0x1C24 - 0x1C37 ,
        				0x1CD0 - 0x1CFF , 0x1DC0 - 0x1DFF , 0x20D0 - 0x20FF , 0x2DE0 - 0x2DFF };
        pos = new int[]{0x0300 , 0x0483 , 0x0591 , 0x0610 , 
						0x064B , 0x06D6 , 0x0730 , 0x07A6 , 
						0x07EB , 0x0816 , 0x0859 , 0x08E3 , 
						0x0900 , 0x093A , 0x0E31 , 0x0F70 , 
						0x102B , 0x1A55 , 0x1AB0 , 0x1C24 , 
						0x1CD0 , 0x1DC0 , 0x20D0 , 0x2DE0 };
        for(int i = 0; i < len.length; i++)
        {
        	len[i] = - len[i];
        }
		charcode = new int[sum(len, len.length)];
		for(int i = 0; i < len.length; i++)
		{
			for(int j = 0; j < len[i]; j++)
			{charcode[sum(len, i) + j] = j + pos[i];}
		}
		//	Name 		*	Position 	
		//				*
		//	Combining	*	0x0300-0x036F
		//	Cyrillic	*	0x0483-0x0489
		//	Hebrew		*	0x0591-0x05C7
		//	Arabic		*	0x0610-0x061A
		//				*
		//				*	0x064B-0x065F
		//				*	0x06D6-0x06ED
		//	Syriac		*	0x0730-0x074A
		//	Thaana		*	0x07A6-0x07B0
		//				*
		//	NKo			*	0x07EB-0x07F3
		//	Samaritan	*	0x0816-0x082D
		//	Mandaic		*	0x0859-0x085B
		//	ArabExtA	*	0x08E3-0x08FF
		//				*
		//	Devanagari	*	0x0900-0x0903
		//				*	0x093A-0x0957
		//	Thai		*	0x0E31-0x0E4F
		//	Tibetan		*	0x0F70-0x0FDA
		//				*
		//	Myanmar		*	0x102B-0x109D
		//	Tai Tham	*	0x1A55-0x1A7F
		//	CDEExt		*	0x1AB0-0x1ABE
		//	Lepcha		*	0x1C24-0x1C37
		//				*
		//	VedicExt	*	0x1CD0-0x1CFF
		//	CDMSupple	*	0x1DC0-0x1DFF
		//	CDMSymbol	*	0x20D0-0x20FF
		//	CyrillicExt	*	0x2DE0-0x2DFF
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

	private int sum(int[] len, int j)
	{
		int sum = 0;
		for (int i = 0; i < j; i ++) 
		{
			sum = sum + len[i];
		}
		return sum;
	}

    private static native String getNameImpl(int codePoint);
    
    private static void checkValidCodePoint(int codePoint) {
        if (!java.lang.Character.isValidCodePoint(codePoint)) {
            throw new IllegalArgumentException("Invalid code point: " + codePoint);
        }
    }
	
    public static String getName(int codePoint) {
        checkValidCodePoint(codePoint);
        if (java.lang.Character.getType(codePoint) == Character.UNASSIGNED) {
            return null;
        }
        String result = getNameImpl(codePoint);
        if (result == null) {
            String blockName = Character.UnicodeBlock.of(codePoint).toString().replace('_', ' ');
            result = blockName + " " + Integer.toHexString(codePoint);
        }
        return result;
    }
}

