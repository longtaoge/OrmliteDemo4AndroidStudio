package org.xiangbalao.address.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by longtaoge on 2016/8/26.
 */
public class City1 implements Serializable {


    @DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
    private int id;
    @DatabaseField
    private String cityCode;
    @DatabaseField
    private String cityName;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode='" + cityCode + '\'' +
                ", id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
