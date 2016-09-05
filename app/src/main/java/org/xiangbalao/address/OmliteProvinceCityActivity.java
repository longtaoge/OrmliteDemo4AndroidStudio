package org.xiangbalao.address;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import org.xiangbalao.address.bean.Province;
import org.xiangbalao.bean.User;
import org.xiangbalao.common.db.DBFactory;
import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.util.FileOperate;
import org.xiangbalao.common.util.GsonUtil;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.common.util.PinYin4JUtils;
import org.xiangbalao.ormlite.R;

import java.sql.SQLException;
import java.util.List;

public class OmliteProvinceCityActivity extends Activity implements OnClickListener {

    private DatabaseHelper helper;
    private Dao<Province, String> provinceDao;
    private Dao<Province.City1, String> cityDao;
    private EditText etFirstName;
    private EditText etlastname;
    // 增
    private Button btnSave;
    // 删除
    private Button btnDel;
    // 改
    private Button btnUpDate;
    // 查
    private Button btnQuery;
    private TextView tvFirstName;
    private TextView tvLastName;


    private List<Province> mProvince;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);
        initView();

        initDb();
        initData();


    }

    private void initData() {


        FileOperate mFileOperate = new FileOperate(this);


        try {
            String Jsons = mFileOperate.readFromAsset("area2.json");
            LogUtils.i(OmliteProvinceCityActivity.class.getSimpleName(), Jsons);

            mProvince = GsonUtil.stringToArray(Jsons, Province[].class);




            LogUtils.i(OmliteProvinceCityActivity.class.getSimpleName(), GsonUtil.createGsonString(mProvince));


        } catch (Exception e) {
            e.printStackTrace();

            LogUtils.i(OmliteProvinceCityActivity.class.getSimpleName(), e.toString());
        }


    }

    private void initDb() {

        helper = DatabaseHelper.getInstance(OmliteProvinceCityActivity.this);
        try {

            provinceDao = helper.getDao(Province.class);
            cityDao =helper.getDao(Province.City1.class) ;




            // 简单写法dao=DatabaseHelper.getInstance(OmliteActivity.this).getDao(User.class);
            // 或者 dao=DBFactory.getDao(OmliteActivity.this, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initView() {

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etlastname = (EditText) findViewById(R.id.lastname);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btndel);
        btnUpDate = (Button) findViewById(R.id.btnupdate);

        btnQuery = (Button) findViewById(R.id.btnQuery);

        btnSave.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpDate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:


                for (int i = 0; i < mProvince.size(); i++) {
                    Province  province=mProvince.get(i);

                    province.setPinyin(PinYin4JUtils.getPinYin(province.getProvinceName()));
                    save(province);

                    for (int j=0;j<province.getCityList().size();j++){

                        Province.City1 city1 =province.getCityList().get(j);
                        city1.setProvinceCode(province.getProvinceCode());
                        city1.setPinyin(PinYin4JUtils.getPinYin(city1.getCityName()));


                        saveCity(city1);
                    }


                }


                break;
            case R.id.btndel:
                del();
                break;

            case R.id.btnupdate:
                upDate();
                break;
            case R.id.btnQuery:
                btnQuery();

                break;

            default:
                break;
        }

    }

    private void upDate() {

        Province city = new Province();
        //city.setName(etFirstName.getText().toString());


        //	city.setPinyin( etlastname.getText().toString());


        try {
            provinceDao.update(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除
     */
    private void del() {

        try {
            // 删除id 为44 的
            Dao<User, Integer> dao = DBFactory.getDao(OmliteProvinceCityActivity.this,
                    User.class);
            dao.deleteById(44);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查
     */
    private void btnQuery() {
        try {
            List<Province> citys;
            Province city;
            // 通过字段名
            citys = provinceDao.queryForEq("provinceCode", "120000");

            Log.d("SQLCipherTest", "users: " + citys.size());
            if (!citys.isEmpty()) {
                city = citys.get(citys.size() - 1);

            } else {
                // 通过id
                city = provinceDao.queryForId("44");
            }

            if (city != null) {
                tvFirstName.setText(city.getProvinceCode());
                tvLastName.setText(city.getProvinceName());

            } else {
                tvFirstName.setText("");
                tvLastName.setText("");
            }

        } catch (SQLException e) {
            // handle exception
        }
    }

    /**
     * 增
     */
    @SuppressWarnings("serial")
    private void save(Province mProvince) {

        try {
            provinceDao.createOrUpdate(mProvince);

        } catch (SQLException e) {
            // handle exception
            Log.e("SQLCipherTest", "can't save user: " + mProvince);

        }
        Log.d("SQLCipherTest", "saved user: " + mProvince);
    }


    private void saveCity(Province.City1 city) {

        try {
            cityDao.createOrUpdate(city);
        } catch (SQLException e) {
            // handle exception
            Log.e("SQLCipherTest", "can't save user: " + mProvince);

        }
        Log.d("SQLCipherTest", "saved user: " + mProvince);
    }




}