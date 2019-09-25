package com.study.jsp.dto;

import java.sql.Timestamp;

public class MDto {
	String mId;
	String mPw;
	String mName;
	String mEmail;
	Timestamp mRdate;
	String mAddress;
	
	public MDto() {
	}
	
	
	
	public MDto(String mId, String mName, String mEmail, Timestamp mRdate, String mAddress) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mRdate = mRdate;
		this.mAddress = mAddress;
	}



	public MDto(String mId, String mPw, String mName, String mEmail, Timestamp mRdate, String mAddress) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mRdate = mRdate;
		this.mAddress = mAddress;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Timestamp getmRdate() {
		return mRdate;
	}

	public void setmRdate(Timestamp mRdate) {
		this.mRdate = mRdate;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	
	
}
