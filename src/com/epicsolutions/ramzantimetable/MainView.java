package com.epicsolutions.ramzantimetable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;





import custom_controls.Custom_List_Adapter;
import db_helper.dbhelp;

import entity.Rzn_model;


import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainView extends ListActivity{
	
	 ArrayList<Rzn_model> ListData=new ArrayList<Rzn_model>();
	 TextView txt_desc_s;
	 Calendar c = Calendar.getInstance(); 
	 dbhelp dbhelper;
	 public static Typeface tf;
	 	@Override
		protected void onCreate(Bundle savedInstanceState) {
	 		dbhelper=new dbhelp(getApplicationContext());
	 		dbhelper.MakeDB();
	 		ListData=gettimetable();
	 		
			
			
			super.onCreate(savedInstanceState);
			setListAdapter(new Custom_List_Adapter(this,ListData));
						
		}
	 	
	 	@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
	 		
			//get selected items
			String selectedValue = (String) getListAdapter().getItem(position);
			Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
	 
		}
	 	
	 	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main_view,menu);
			return true;
		}
	 	
	 	@Override
		public boolean onOptionsItemSelected (MenuItem item){
			switch(item.getItemId())
			{
			case R.id.about:
				Intent intent=new Intent(getApplicationContext(),AboutView.class);
				startActivity(intent);
				return true;
			case R.id.zakart:
				Intent zakartintent=new Intent(getApplicationContext(),ZakartView.class);
				startActivity(zakartintent);
				return true;
				
			default:
				return super.onOptionsItemSelected(item);			
			}
			
			
		}
	 	
	 	
	 	private ArrayList<Rzn_model> gettimetable(){
	 		String sql="SELECT * FROM Time_table";
	 		dbhelper=new dbhelp(getApplicationContext());
	 		
	 		ArrayList alist=new ArrayList();
	 		ArrayList<Rzn_model> lsttimetable=new ArrayList<Rzn_model>();
	 		
	 		alist= dbhelper.getDataTable(sql);
	 		
	 		for(int i=0;i<alist.size();i++){
				HashMap tablerow=new HashMap();
				tablerow=(HashMap)alist.get(i);
				Rzn_model model=new Rzn_model();				
				model.setR_date(tablerow.get("R_Date").toString());
				model.setG_date(tablerow.get("R_iftari").toString().substring(0,10));
				model.setR_iftari(tablerow.get("R_iftari").toString().substring(10,16)+""+"pm");
				model.setR_sehri(tablerow.get("R_sehri").toString().substring(10,16)+""+"am");
				lsttimetable.add(model);
				
			} 
	 		return lsttimetable;
	 	}
		
		private void simulate(){
	 		Rzn_model m1=new Rzn_model();
			m1.setR_date("၁");
			m1.setR_iftari("6:00");
			m1.setR_sehri("10:00");
			int date = c.get(Calendar.DATE);
			int month=c.get(Calendar.MONTH);
			int year=c.get(Calendar.YEAR);		
			
			
			m1.setG_date(String.valueOf(date)+"-"+String.valueOf(month)+"-"+String.valueOf(year));
			
			Rzn_model m2=new Rzn_model();
			m2.setR_date("2");
			m2.setR_iftari("6:00");
			m2.setR_sehri("10:00");
			m2.setG_date(""+date+"");
			
			Rzn_model m3=new Rzn_model();
			m3.setR_date("2");
			m3.setR_iftari("6:00");
			m3.setR_sehri("10:00");
			m3.setG_date(""+date+"");
			
			Rzn_model m4=new Rzn_model();
			m4.setR_date("2");
			m4.setR_iftari("7:00");
			m4.setR_sehri("12:00");
			m4.setG_date(""+date+"");
			
			ListData.add(m1);
			ListData.add(m2);
			ListData.add(m3);
			ListData.add(m4);
			txt_desc_s=(TextView) findViewById(R.id.txt_desc_s);
	 	}
}
