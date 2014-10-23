package com.junior.stouring;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Offers implements Parcelable {
	
	private String Name;
	private float Price;
	private Bitmap Image;
	private double Latitude;
	private double Longitude;

	public Offers(
			String pName,
			float pPrice,
			Bitmap pBitmap,
			double pLatitude,
			double pLongitude) 
	{
		super();
		this.Name = pName;
		this.Price = pPrice;
		this.Image = pBitmap;
		this.Latitude = pLatitude;
		this.Longitude = pLongitude;
	}
	
	public Offers(
			String pName,
			float pPrice,
			double pLatitude,
			double pLongitude) 
	{
		super();
		this.Name = pName;
		this.Price = pPrice;
		this.Latitude = pLatitude;
		this.Longitude = pLongitude;
	}
	
	public Offers(Parcel in) {
		this.Name = in.readString();
		this.Price = in.readFloat();
		this.Image = in.readParcelable(getClass().getClassLoader());
		this.Latitude = in.readDouble();
		this.Longitude = in.readDouble();
	}
	
	/**
	 * Getters
	 */
	public String getName(){
		return Name;
	}
	
	public float getPrice(){
		return Price;
	}
	
	public double getLatitude(){
		return Latitude;
	}
	
	public double getLongitude(){
		return Longitude;
	}
	
	/**
	 * Setters
	 */
	public void setName(String pName){
		Name = pName;
	}

	public void setMark(float pPrice){
		Price = pPrice;
	}
	
	public void setLatitude(double pLatitude){
		Latitude = pLatitude;
	}
	
	public void setLongitude(double pLongitude){
		Longitude = pLongitude;
	}
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(Name);
		dest.writeFloat(Price);
		dest.writeParcelable(Image, flags);
		dest.writeDouble(Latitude);
		dest.writeDouble(Longitude);
	}
	
	public static final Parcelable.Creator<Offers> CREATOR = new Parcelable.Creator<Offers>()
			{			
				@Override
				public Offers createFromParcel(Parcel source)
				{
					return new Offers(source);
				}

				@Override
				public Offers[] newArray(int size)
				{
					return new Offers[size];
				}
			};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
