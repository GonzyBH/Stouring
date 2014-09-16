package com.junior.stouring;

import android.graphics.drawable.Drawable;


public class TouringPlace{
	
	private String Name;
	private float Mark;
	private Drawable Image;

	public TouringPlace(String pName, float pMark, Drawable pImage) {
		this.Name = pName;
		this.Mark = pMark;
		this.Image = pImage;
	}
	
	public String getName(){
		return Name;
	}
	
	public float getMark(){
		return Mark;
	}
	
	public Drawable getImage(){
		return Image;
	}
	
	public void setName(String pName){
		Name = pName;
	}

	public void setMark(String pMark){
		Name = pMark;
	}
	
	public void setImage(String pImage){
		Name = pImage;
	}
	
}

