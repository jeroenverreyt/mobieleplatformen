package com.thomasmore.mobieleplatformen;

import java.io.Serializable;

public class Abo implements Comparable<Abo>, Serializable {

	String name;
	int id;
	String provider;
	double price;
	String type;
	String gr_bel;
	String gr_sms;
	double gr_mi;
	double bel_en;
	double bel_an;
	double bel_vl;
	double bel_bui;
	double sms_en;
	double sms_an;
	double mms;
	double mi;
	double totalPrice;
	double priceDiff;
	String gr_bel_en;
	String gr_sms_en;
	String gr_bel_type;
	String gr_sms_type;
	public Abo() {

	}
	public String toString(){
		return name;
		
	}

	public String getGr_sms_type() {
		return gr_sms_type;
	}


	public void setGr_sms_type(String gr_sms_type) {
		this.gr_sms_type = gr_sms_type;
	}


	public String getGr_bel_type() {
		return gr_bel_type;
	}


	public void setGr_bel_type(String gr_bel_type) {
		this.gr_bel_type = gr_bel_type;
	}


	public String getGr_bel_en() {
		return gr_bel_en;
	}

	public void setGr_bel_en(String gr_bel_en) {
		this.gr_bel_en = gr_bel_en;
	}

	public String getGr_sms_en() {
		return gr_sms_en;
	}

	public void setGr_sms_en(String gr_sms_en) {
		this.gr_sms_en = gr_sms_en;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGr_bel() {
		return gr_bel;
	}

	public void setGr_bel(String gr_bel) {
		this.gr_bel = gr_bel;
	}

	public String getGr_sms() {
		return gr_sms;
	}

	public void setGr_sms(String gr_sms) {
		this.gr_sms = gr_sms;
	}

	public double getGr_mi() {
		return gr_mi;
	}

	public void setGr_mi(Double gr_mi) {
		this.gr_mi = gr_mi;
	}

	public double getBel_en() {
		return bel_en;
	}

	public void setBel_en(Double bel_en) {
		this.bel_en = bel_en;
	}

	public double getBel_an() {
		return bel_an;
	}

	public void setBel_an(Double bel_an) {
		this.bel_an = bel_an;
	}

	public double getBel_vl() {
		return bel_vl;
	}

	public void setBel_vl(Double bel_vl) {
		this.bel_vl = bel_vl;
	}

	public double getBel_bui() {
		return bel_bui;
	}

	public void setBel_bui(Double bel_bui) {
		this.bel_bui = bel_bui;
	}

	public double getSms_en() {
		return sms_en;
	}

	public void setSms_en(Double sms_en) {
		this.sms_en = sms_en;
	}

	public double getSms_an() {
		return sms_an;
	}

	public void setSms_an(Double sms_an) {
		this.sms_an = sms_an;
	}

	public double getMms() {
		return mms;
	}

	public void setMms(Double mms) {
		this.mms = mms;
	}

	public double getMi() {
		return mi;
	}

	public void setMi(Double mi) {
		this.mi = mi;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	public double getPriceDiff() {
		return priceDiff;
	}

	public void setPriceDiff(double priceDiff) {
		this.priceDiff = priceDiff;
	}

	@Override
	public int compareTo(Abo a) {
		// TODO Auto-generated method stub
		double p = a.getTotalPrice();
		double d = a.getPriceDiff();
		
		if(totalPrice > p){
			return 1;
		}else if(totalPrice < p){
			return -1;
		}
		
		if(priceDiff < d){
			return 1;
		}else if(priceDiff > d){
			return -1;
		}
		return 0;
		

	}
	
}
