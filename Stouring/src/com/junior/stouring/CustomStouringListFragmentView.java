package com.junior.stouring;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomStouringListFragmentView extends RelativeLayout{
	
	private TextView mTextView;

	public CustomStouringListFragmentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomStouringListFragmentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomStouringListFragmentView(Context context) {
		super(context);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.row_activity_fragment_touringlist, this);
		mTextView = (TextView) findViewById(R.id.firstrow);
	}

	public void bind(int text) {
		mTextView.setText(getResources().getString(text));
	}

}
