package org.xiangbalao.app;

import android.app.Application;
import android.os.Environment;

import org.xiangbalao.address.bean.Province;
import org.xiangbalao.bean.City;
import org.xiangbalao.bean.DataTest;
import org.xiangbalao.bean.User;
import org.xiangbalao.common.db.DatabaseHelper;

public class OrmliteApplication extends Application {
	/**
	 * 数据库名称及路径
	 */
	private static String databasesPath = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/xiangbalaotest.db";

	// + "/city.db";

	@Override
	public void onCreate() {
		// 初始化
		DatabaseHelper.initialize(databasesPath, 1, new Class<?>[] {
				DataTest.class, User.class, City.class, Province.class, Province.City.class

		});
		// 初始化
		/*DatabaseHelper.initialize(databasesPath, 1, new Class<?>[] {
				 Province.class, Province.City.class

		});*/

		super.onCreate();

	}

}
