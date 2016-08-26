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

    @DatabaseField
    private  String addressCode;
    @DatabaseField
    private String provinceName;

    private List<City1> cityList;

    @Override
    public String toString() {
        return "Province{" +
                "addressCode='" + addressCode + '\'' +
                ", id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
