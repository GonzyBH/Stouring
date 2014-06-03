package com.junior.stouring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class FragmentTouringList extends ListFragment {
	
	String[] countries = {"Belgique", "France", "Allemagne", "Italie", "Russie", "Portugal", "Hollande", "Espagne", "Suisse", "Luxembourg"};
	String[] note = new String[]{
	        "1",
	        "2",
	        "3",
	        "4",
	        "5",
	        "6",
	        "7",
	        "8",
	        "9",
	        "10"
	    };
	
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		 List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
		 
	        for(int i=0;i<10;i++){
	            HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put("txt", "Pays : " + countries[i]);
	            hm.put("cur","Note : " + note[i]);
	            aList.add(hm);
	        }	       
	        
	        String[] from = {"txt","cur"};
	        
	        int[] to = {R.id.txt,R.id.cur};
	        
	        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.row_activity_fragment_touringlist, from, to);
	        
	        setListAdapter(adapter);
	 
	        return super.onCreateView(inflater, container, savedInstanceState);
		
		//return inflater.inflate(R.layout.activity_fragment_touringlist, container, false);
	}



	/*@Override
		public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final String[] items = myArray;
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, items);

		setListAdapter(aa);
	}*/




}
