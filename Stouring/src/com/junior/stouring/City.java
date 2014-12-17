package com.junior.stouring;

import android.os.Parcel;
import android.os.Parcelable;


public class City implements Parcelable, Comparable<TouringPlace>{
	
	private int Id;
	private String Name;

	public City(
			int pId,
			String pName) 
	{
		super();
		this.Id = pId;
		this.Name = pName;
	}
	
	
	public City(Parcel in) {
		this.Id = in.readInt();
		this.Name = in.readString();
	}
	
	public int getId(){
			return Id;
	}
	
	public String getName(){
		return Name;
	}
	
	public void setId(int pId){
		Id = pId;
	}
	
	public void setName(String pName){
		Name = pName;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(Id);
		dest.writeString(Name);
	}
	
	public static final Parcelable.Creator<TouringPlace> CREATOR = new Parcelable.Creator<TouringPlace>()
			{
				@Override
				public TouringPlace createFromParcel(Parcel source)
				{
					return new TouringPlace(source);
				}

				@Override
				public TouringPlace[] newArray(int size)
				{
					return new TouringPlace[size];
				}
			};

	@Override
	public int compareTo(TouringPlace another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

