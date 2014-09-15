package com.junior.stouring;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomStouringListFragmentAdapter extends ArrayAdapter<TouringPlace>{
	
	
	public CustomStouringListFragmentAdapter(Context context, List<TouringPlace> items) {
		super(context, R.layout.row_activity_fragment_touringlist, items);
		}
	
	//private List<Integer> mModel = new ArrayList<Integer>();
	private Context mContext;
	
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder viewHolder;
		 
		 if(convertView == null) {
		 // inflate the GridView item layout
		 LayoutInflater inflater = LayoutInflater.from(getContext());
		 convertView = inflater.inflate(R.layout.row_activity_fragment_touringlist, parent, false);
		 // initialize the view holder
		 viewHolder = new ViewHolder();
		 //viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
		 viewHolder.tvName = (TextView) convertView.findViewById(R.id.firstrow);
		 viewHolder.tvMark = (RatingBar) convertView.findViewById(R.id.secondrow);
		 convertView.setTag(viewHolder);
	 } else {
		 
	 // recycle the already inflated view
	 viewHolder = (ViewHolder) convertView.getTag();
	 }
	 // update the item view
	 TouringPlace item = getItem(position);
	 //viewHolder.ivIcon.setImageDrawable(item.icon);
	 viewHolder.tvName.setText(item.getName());
	 viewHolder.tvMark.setStepSize((float) 0.1);
	 viewHolder.tvMark.setRating(item.getMark());
	 return convertView;
	 }
	 
	 private static class ViewHolder {
		 ImageView ivIcon;
		 TextView tvName;
		 RatingBar tvMark;
		 }

}
