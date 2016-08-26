package org.xiangbalao.address.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by longtaoge on 2016/8/26.
 */
public class Province  implements Serializable {

    @DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
    private int id;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @DatabaseField
    private  String provinceCode;

    @DatabaseField
    private String provinceName;

    private List<City1> cityList;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City1> getCityList() {
        return cityList;
    }

    public void setCityList(List<City1> cityList) {
        this.cityList = cityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public static class City1 {

        @DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
        private int id;
        @DatabaseField
        private String cityCode;
        @DatabaseField
        private String cityName;

        @DatabaseField
        private String provinceCode;


        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
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

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }


}
