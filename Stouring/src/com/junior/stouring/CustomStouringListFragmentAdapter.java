package com.junior.stouring;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomStouringListFragmentAdapter extends ArrayAdapter<TouringPlace>{
	
	private List<TouringPlace> mTouringPlaces;
	
	public CustomStouringListFragmentAdapter(Context context, List<TouringPlace> items) {
		super(context, R.layout.row_activity_fragment_touringlist, items);
		mTouringPlaces = items;
	}
	
	 private static class ViewHolder {
		 ImageView ivIcon;
		 TextView tvName;
		 RatingBar rbMark;
		 }
	   
	 @Override
	 public int getCount() {
		 return mTouringPlaces.size();
	 }
	
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder viewHolder;
		 
		 if(convertView == null) {
			 
			 // inflate the GridView item layout
			 LayoutInflater inflater = LayoutInflater.from(getContext());
			 convertView = inflater.inflate(R.layout.row_activity_fragment_touringlist, parent, false);
			 // initialize the view holder
			 viewHolder = new ViewHolder();
			 viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
			 viewHolder.tvName = (TextView) convertView.findViewById(R.id.firstrow);
			 viewHolder.rbMark = (RatingBar) convertView.findViewById(R.id.secondrow);
			 convertView.setTag(viewHolder);
		 
		 } else {
		 
			 // recycle the already inflated view
			 viewHolder = (ViewHolder) convertView.getTag();
			 }
			 // update the item view
			 TouringPlace item = getItem(position);
			 viewHolder.ivIcon.setImageBitmap(item.getImage());
			 viewHolder.tvName.setText(item.getName());
			 viewHolder.rbMark.setStepSize((float) 0.1);
			 viewHolder.rbMark.setIsIndicator(true);
			 viewHolder.rbMark.setRating(item.getMark());
			 return convertView;
	 }

}
