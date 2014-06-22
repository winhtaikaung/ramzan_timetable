package custom_controls;

import java.util.ArrayList;

import com.epicsolutions.ramzantimetable.R;

import entity.Rzn_model;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Custom_List_Adapter extends ArrayAdapter<Rzn_model> {
	
	private final Context context;
	private final  ArrayList<Rzn_model> values;
	
	public Custom_List_Adapter(Context context, ArrayList<Rzn_model> values) {
		super(context,R.layout.custom_listadapter, values);
		this.context=context;
		this.values=values;
		// TODO Auto-generated constructor stub
	}
	
	public View getView(int position,View convertView,ViewGroup parent){
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView=inflater.inflate(R.layout.custom_listadapter,parent,false);
		
		TextView txt_R_date=(TextView)rowView.findViewById(R.id.txt_R_date);
		TextView txt_G_date=(TextView)rowView.findViewById(R.id.txt_G_date);
		TextView txt_R_i_time=(TextView)rowView.findViewById(R.id.txt_R_i_time);
		TextView txt_R_s_time=(TextView)rowView.findViewById(R.id.txt_R_s_time);
		
		
		txt_R_date.setText(values.get(position).getR_date());
		txt_G_date.setText(values.get(position).getG_date());
		txt_R_i_time.setText(values.get(position).getR_iftari());
		txt_R_s_time.setText(values.get(position).getR_sehri());
		
		
		//Typeface tf=Typeface.createFromAsset(rowView.getContext().getAssets(), "fonts/Zawgyi-One_v4.20_August_26_2009.ttf");
		
		
		
		return rowView;
	}
}
