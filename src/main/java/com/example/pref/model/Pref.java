package com.example.pref.model;

public class Pref {

    // 都道府県コード
    private String prefCode;

    // 都道府県名
    private String prefName;

    // 市区名
    private String cityName;

    // 緯度（北緯）[60進数]
    private String latitude_60;

    // 経度（東経）[60進数]
    private String longitude_60;

    // 緯度（北緯）[10進数]
    private String latitude_10;

    // 経度（東経）[10進数]
    private String longitude_10;

	public String getPrefCode() {
		return prefCode;
	}

	public void setPrefCode(String prefCode) {
		this.prefCode = prefCode;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLatitude_60() {
		return latitude_60;
	}

	public void setLatitude_60(String latitude_60) {
		this.latitude_60 = latitude_60;
	}

	public String getLongitude_60() {
		return longitude_60;
	}

	public void setLongitude_60(String longitude_60) {
		this.longitude_60 = longitude_60;
	}

	public String getLatitude_10() {
		return latitude_10;
	}

	public void setLatitude_10(String latitude_10) {
		this.latitude_10 = latitude_10;
	}

	public String getLongitude_10() {
		return longitude_10;
	}

	public void setLongitude_10(String longitude_10) {
		this.longitude_10 = longitude_10;
	}

    
}
