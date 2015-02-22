package com.coolweather.app.db;

import java.util.ArrayList;

import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeaherDB {
	
	/**
	 * 数据库名
	 */
	public static final String DB_NAME="coo_weaher";
	
	/**
	 * 数据库版本
	 */
	public static final int VERSION=1;
	
	private static CoolWeaherDB coolWeaherDB;
	
	private SQLiteDatabase db;
	
	/**
	 * 将构造方法私有化
	 * @param context
	 */
	private CoolWeaherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
	}
	
	/**
	 * 获取CoolWeaherDB的实例
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeaherDB getInstance(Context context){
		if(coolWeaherDB ==null){
			coolWeaherDB = new CoolWeaherDB(context);
		}
		return coolWeaherDB;
	}
	
	/**
	 * 将Province实例存储到数据库
	 * @param province
	 */
	public void saveProvince(Province province){
		if (province!=null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	/**
	 * 从数据库读取全国所有省份的信息
	 * @return
	 */
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	
	/**
	 * 将City实例存储到数据库
	 * @param city
	 */
	public void saveCity(City city){
		if (city!=null) {
			ContentValues values = new ContentValues();
			values.put("province_name", city.getCityName());
			values.put("province_code", city.getCityCode());
			db.insert("City", null, values);
		}
	}
	
	/**
	 * 从数据库读取某省份下所有城市的信息
	 * @return
	 */
	public List<City> loadCites(){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
				list.add(city);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	
	/**
	 * 将County实例存储到数据库
	 * @param county
	 */
	public void saveCounty(County county){
		if (county!=null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			db.insert("County", null, values);
		}
	}
	
	/**
	 * 从数据库读取某城市下所有县的信息
	 * @return
	 */
	public List<County> loadCounties(){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cursor.getInt(cursor.getColumnIndex("county_id")));
				list.add(county);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	
}
