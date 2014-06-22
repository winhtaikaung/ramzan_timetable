package db_helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class dbhelp extends SQLiteOpenHelper {
		
	
	private static String DB_PATH = "/data/data/com.epicsolutions.ramzantimetable/databases/";
	 
    private static String DB_NAME = "ram_zan_timetable.sqlite";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
	 
	   public dbhelp(Context context)
	   {
	      super(context, DB_NAME , null, 1);
	      this.myContext = context;
	   }
	   
	   public void createDataBase() throws IOException{
		   
	    	boolean dbExist = checkDataBase();
	 
	    	if(dbExist){
	    		//do nothing - database already exist
	    	}else{
	 
	    		//By calling this method and empty database will be created into the default system path
	               //of your application so we are gonna be able to overwrite that database with our database.
	        	this.getReadableDatabase();
	 
	        	try {
	 
	    			copyDataBase();
	 
	    		} catch (IOException e) {
	 
	        		throw new Error("Error copying database");
	 
	        	}
	    	}
	 
	    }
	   
	   /**
	     * Check if the database already exist to avoid re-copying the file each time you open the application.
	     * @return true if it exists, false if it doesn't
	     */
	    private boolean checkDataBase(){
	 
	    	SQLiteDatabase checkDB = null;
	 
	    	try{
	    		String myPath = DB_PATH + DB_NAME;
	    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    	}catch(SQLiteException e){
	 
	    		//database does't exist yet.
	 
	    	}
	 
	    	if(checkDB != null){
	 
	    		checkDB.close();
	 
	    	}
	 
	    	return checkDB != null ? true : false;
	    }
	    
	    /**
	     * Copies your database from your local assets-folder to the just created empty database in the
	     * system folder, from where it can be accessed and handled.
	     * This is done by transfering bytestream.
	     * */
	    private void copyDataBase() throws IOException{
	 
	    	//Open your local db as the input stream
	    	InputStream myInput = myContext.getAssets().open(DB_NAME);
	 
	    	// Path to the just created empty db
	    	String outFileName = DB_PATH + DB_NAME;
	 
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
	 
	    }
	    
	    public void openDataBase() throws SQLException{
	    	 
	    	//Open the database
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    }
	 
	    @Override
		public synchronized void close() {
	 
	    	    if(myDataBase != null)
	    		    myDataBase.close();
	 
	    	    super.close();
	 
		}
	    
	    public void MakeDB(){
	    	
	    		if(!checkDataBase()){
	    			
	    			// if not exist
	    			try {
	    				Toast.makeText(myContext, "Creating database....", Toast.LENGTH_SHORT).show();
						createDataBase();
						copyDataBase();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}else{
	    			try {
						copyDataBase();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    		}
	    }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   public ArrayList<HashMap<String, String>> getDataTable(String sql)
	   {
	      ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( sql, null );
	      
	      int columcount=res.getColumnCount();
	      int rowcount= 0;
	      
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	    	  HashMap<String, String> rowHashMap = new HashMap<String, String>();
	    	  for(int i=0;i<columcount;i++){
	    		  String cname=res.getColumnName(i);
	    		  rowHashMap.put(cname, res.getString(i));
	    		  
	    	  }
	    	  
	    	  res.moveToNext();
	    	  rows.add(rowHashMap);
	    	  
	    	  
	      }
	      db.close();
	   return rows;
	   }
	   
	   public ArrayList<HashMap<String, String>> getDataRow(String sql)
	   {
	      ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( sql, null );
	      
	      int columcount=res.getColumnCount();
	      int rowcount= 0;
	      
	      res.moveToFirst();
	      
	      
	    	  HashMap<String, String> rowHashMap = new HashMap<String, String>();
	    	  for(int i=0;i<columcount;i++){
	    		  String cname=res.getColumnName(i);
	    		  rowHashMap.put(cname, res.getString(i));
	    		  
	    	  }
	    	  
	    	  rows.add(rowHashMap);
	    	  
	    	  db.close();  
	      
	   return rows;
	   }
	   
	   
	   

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}
