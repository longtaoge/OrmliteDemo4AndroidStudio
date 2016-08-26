package org.xiangbalao.bean;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by longtaoge on 2016/2/20.
 */
public class City implements Serializable {
	
	@DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
	private int id;
	@DatabaseField
    private String name;
	@DatabaseField
    private String pinyin;
	@DatabaseField
    private String lat;
	@DatabaseField
    private String lng;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    @Override
    public String toString() {
        return "City{" +
                "lat='" + lat + '\'' +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
