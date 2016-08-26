package org.xiangbalao;

import java.sql.SQLException;
import java.util.List;

import org.xiangbalao.bean.User;
import org.xiangbalao.common.db.DBFactory;
import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.ormlite.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

public class OmliteActivity extends Activity implements OnClickListener {

	private DatabaseHelper helper;
	private Dao<User, String> dao;

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
	}

	private void initDb() {

		helper = DatabaseHelper.getInstance(OmliteActivity.this);
		try {
			dao = helper.getDao(User.class);
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

		User user = new User();
		user.firstname = etFirstName.getText().toString();

		user.lastname = etlastname.getText().toString();

		user.setId(44);

		try {
			dao.update(user);
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
			Dao<User, Integer> dao = DBFactory.getDao(OmliteActivity.this,
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
			List<User> users;
			User user;
			// 通过字段名
			users = dao.queryForEq("firstname", "aa");

			Log.d("SQLCipherTest", "users: " + users.size());
			if (!users.isEmpty()) {
				user = users.get(users.size() - 1);

			} else {
				// 通过id
				user = dao.queryForId("44");
			}

			if (user != null) {
				tvFirstName.setText(user.firstname);
				tvLastName.setText(user.lastname);

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
		User user = new User() {
			{
				firstname = etFirstName.getText().toString();
				lastname = etlastname.getText().toString();
			}
		};

		user.setId(44);
		Log.d("SQLCipherTest", "saving user: " + user);
		try {

			dao.createOrUpdate(user);
		} catch (SQLException e) {
			// handle exception
			Log.e("SQLCipherTest", "can't save user: " + user);

		}
		Log.d("SQLCipherTest", "saved user: " + user);
	}

}