package com.junior.stouring;


public class TouringPlace{
	
	private String Name;
	private float Mark;
	private int Image;

public TouringPlace(String pName, float pMark, int pImage) {
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

}

