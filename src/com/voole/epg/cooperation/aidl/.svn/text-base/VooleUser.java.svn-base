package com.voole.epg.cooperation.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class VooleUser implements Parcelable{
	private String oemid;
	private String uid;
	private String hid;
	
	public VooleUser(){
	}
	
	public VooleUser(String oemid, String uid, String hid){
		this.oemid = oemid;
		this.uid = uid;
		this.hid = hid;
	}
	
	public VooleUser(Parcel parcel){
		oemid = parcel.readString();
		uid = parcel.readString();
		hid = parcel.readString();
	}

	public String getOemid() {
		return oemid;
	}

	public void setOemid(String oemid) {
		this.oemid = oemid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(oemid);
		dest.writeString(uid);
		dest.writeString(hid);
	}
	
	public static final Parcelable.Creator<VooleUser> CREATOR = new Parcelable.Creator<VooleUser>() {

		@Override
		public VooleUser createFromParcel(Parcel source) {
			return new VooleUser(source);
		}

		@Override
		public VooleUser[] newArray(int size) {
			return new VooleUser[size];
		}
	};
	
	

}
