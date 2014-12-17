package com.junior.stouring;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCityListFragmentAdapter extends ArrayAdapter<City>{
	
	private List<City> mCities;
	
	public CustomCityListFragmentAdapter(Context context, List<City> items) {
		super(context, R.layout.row_activity_fragment_touringlist, items);
		mCities = items;
	}
	
	 private static class ViewHolder {
		 ImageView ivIcon;
		 TextView tvName;
		 }
	   
	 @Override
	 public int getCount() {
		 int s = mCities.size();
		 Log.e("Nombre de villes adapter",""+s);
		 return s;
		
	 }
	
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder viewHolder;
		 
		 if(convertView == null) {
			 
			 // inflate the GridView item layout
			 LayoutInflater inflater = LayoutInflater.from(getContext());
			 convertView = inflater.inflate(R.layout.row_activity_fragment_citylist, parent, false);
			 // initialize the view holder
			 viewHolder = new ViewHolder();
			 viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
			 viewHolder.tvName = (TextView) convertView.findViewById(R.id.firstrow);
			 convertView.setTag(viewHolder);
		 
		 } else {
		 
			 // recycle the already inflated view
			 viewHolder = (ViewHolder) convertView.getTag();
			 }
			 // update the item view
			 City item = getItem(position);
			 viewHolder.tvName.setText(item.getName());
			 return convertView;
	 }

}
