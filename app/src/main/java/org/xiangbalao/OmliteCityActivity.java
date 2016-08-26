package org.xiangbalao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xiangbalao.bean.City;
import org.xiangbalao.bean.User;
import org.xiangbalao.common.db.DBFactory;
import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.util.FileOperate;
import org.xiangbalao.ormlite.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OmliteCityActivity extends Activity implements OnClickListener {

	private DatabaseHelper helper;
	private Dao<City, String> dao;

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
	        List<City> mCityList = new ArrayList<City>();

	        try {
	            String Jsons = mFileOperate.readFromAsset("Citys.txt");
	            Log.i(OmliteCityActivity.class.getSimpleName(), Jsons);
	            JSONArray JS = new JSONArray(Jsons);
	            for (int i = 0; i < JS.length(); i++) {
	                JSONObject  jsonObject = (JSONObject) JS.get(i);

	                City city =new City();
	                city.setName( jsonObject.getString("name"));
	                city.setPinyin(jsonObject.getString("pinyin"));
	                city.setLat(jsonObject.getString("lat"));
	                city.setLng(jsonObject.getString("lng"));
	                mCityList.add(city);
	            }


	            Iterator<City> it = mCityList.iterator();
	            while (it.hasNext()) {
	                City p = (City) it.next();
	                Log.i(OmliteCityActivity.class.getSimpleName(), p.getName());
	                
	                dao.createOrUpdate(p);
	                
	            }


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
		
	}

	private void initDb() {

		helper = DatabaseHelper.getInstance(OmliteCityActivity.this);
		try {
			dao = helper.getDao(City.class);
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

			save();
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

		City city = new City();
		city.setName(etFirstName.getText().toString());

		city.setPinyin( etlastname.getText().toString());

	

		try {
			dao.update(city);
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
			Dao<User, Integer> dao = DBFactory.getDao(OmliteCityActivity.this,
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
			List<City> citys;
			City city;
			// 通过字段名
			citys = dao.queryForEq("name", "aa");

			Log.d("SQLCipherTest", "users: " + citys.size());
			if (!citys.isEmpty()) {
				city = citys.get(citys.size() - 1);

			} else {
				// 通过id
				city = dao.queryForId("44");
			}

			if (city != null) {
				tvFirstName.setText(city.getName());
				tvLastName.setText(city.getPinyin());

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
	private void save() {
		City city = new City();
		
			
				city.setName(etFirstName.getText().toString());
				city.setPinyin(etlastname.getText().toString() )  ;
			
		

				
		Log.d("SQLCipherTest", "saving user: " + city);
		try {

			dao.createOrUpdate(city);
		} catch (SQLException e) {
			// handle exception
			Log.e("SQLCipherTest", "can't save user: " + city);

		}
		Log.d("SQLCipherTest", "saved user: " + city);
	}

}