package com.epicsolutions.ramzantimetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ZakartView extends Activity {
	TextView txt_gold_amt;
	TextView txt_money_amt;
	TextView txt_gold_price;
	
	Button btn_calculate;
	Button btn_clear;
	Double percentage=(Double) 0.025;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zakert_view);
		
		initialize();
		controller(btn_calculate);
		controller(btn_clear);
		
	}
	
	private void initialize(){
		txt_gold_amt=(TextView) findViewById(R.id.txt_gold_amt);
		txt_money_amt=(TextView) findViewById(R.id.txt_money_amt);
		txt_gold_price=(TextView) findViewById(R.id.txt_gold_price);
		btn_calculate=(Button) findViewById(R.id.btn_calculate);
		btn_clear=(Button) findViewById(R.id.btn_clear);
		
	}
	
	private void controller (final Button btn){
		btn.setOnClickListener(new OnClickListener() {
			String btn_name;
			@Override
			public void onClick(View v) {
				
				btn_name=	((Button) v).getText().toString();
				if(btn_name.equals(btn_calculate.getText().toString())){
					//To do calculate task
					if((txt_gold_amt.length()==0)||(txt_money_amt.length()==0)||(txt_gold_price.length()==0)){
						makealert("သတိ","လိုအပ္ေသာအခ်က္အလက္မ်ားကိုျဖည့္ပါ။");
						
					}else{
						String gold=txt_gold_amt.getText().toString();
						Double gold_amt=Double.valueOf(gold);
						
						String money=txt_money_amt.getText().toString();
						Double money_amt=Double.valueOf(money);
						
						String gold_price=txt_gold_price.getText().toString();
						Double goldprice=Double.valueOf(gold_price);
						
						Double result=((gold_amt*goldprice)+money_amt) * 0.025;
						
						makealert("ရလဒ္","သင္ေပးရမည့္ဇကာသ္ပမာဏမွာ"+result+"က်ပ္ျဖစ္ပါသည္။");
					}
				}
				if(btn_name.equals(btn_clear.getText().toString())){
					txt_gold_amt.setText("");
					txt_money_amt.setText("");
					txt_gold_price.setText("");
				}
				
				
				
			}
		});
		
	}
	
private void makealert(String title,String message){
	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle(title);
	alertDialog.setMessage(message);
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
	// here you can add functions
	}
	});
	//alertDialog.setIcon(R.drawable.icon);
	alertDialog.show();
}
	
	

}
