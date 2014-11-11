package com.junior.stouring;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class TouringPlace implements Parcelable, Comparable<TouringPlace>{
	
	private int Id;
	private String Name;
	private float Mark;
	private String Type;
	private Bitmap Image;
	private double Latitude;
	private double Longitude;

	public TouringPlace(
			int pId,
			String pName,
			float pMark,
			String pType,
			Bitmap pBitmap,
			double pLatitude,
			double pLongitude) 
	{
		super();
		this.Id = pId;
		this.Name = pName;
		this.Mark = pMark;
		this.Type = pType;
		this.Image = pBitmap;
		this.Latitude = pLatitude;
		this.Longitude = pLongitude;
	}
	
	public TouringPlace(
			int pId,
			String pName,
			float pMark,
			String pType,
			double pLatitude,
			double pLongitude) 
	{
		super();
		this.Id = pId;
		this.Name = pName;
		this.Mark = pMark;
		this.Type = pType;
		this.Latitude = pLatitude;
		this.Longitude = pLongitude;
	}
	
	public TouringPlace(Parcel in) {
		this.Name = in.readString();
		this.Mark = in.readFloat();
		this.Type = in.readString();
		this.Image = in.readParcelable(getClass().getClassLoader());
		this.Latitude = in.readDouble();
		this.Longitude = in.readDouble();
	}
	
	public int getId(){
			return Id;
	}
	
	public String getName(){
		return Name;
	}
	
	public float getMark(){
		return Mark;
	}
	
	public String getType(){
		return Type;
	}
	
	public Bitmap getImage(){
		return Image;
	}
	
	public double getLatitude(){
		return Latitude;
	}
	
	public double getLongitude(){
		return Longitude;
	}
	
	public void setId(int pId){
		Id = pId;
	}
	
	public void setName(String pName){
		Name = pName;
	}

	public void setMark(float pMark){
		Mark = pMark;
	}
	
	public void setType(String pType){
		Type = pType;
	}
	
	public void setImage(Bitmap pImage){
		Image = pImage;
	}

	public void setLatitude(double pLatitude){
		Latitude = pLatitude;
	}
	
	public void setLongitude(double pLongitude){
		Longitude = pLongitude;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(Name);
		dest.writeFloat(Mark);
		dest.writeString(Type);
		dest.writeParcelable(Image, flags);
		dest.writeDouble(Latitude);
		dest.writeDouble(Longitude);
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

