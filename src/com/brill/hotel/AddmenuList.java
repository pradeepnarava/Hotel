package com.brill.hotel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.brill.hotel.AddMenu.ImagePickListener;
import com.brill.hotel.AddMenu.TakePictureListener;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddmenuList extends Activity  {
	DataMenuImage data;
	int imageNum = 0;
	 public static final String PREFS_NAME = "MyApp";
	 private String selectedImagePath;
	String Name,Price,Category,Des,Image;
	String Ename,Eprice,Ecat,Edes,Eimage;
	ListView menulist;
	 Dialog d;
	 DataBase db;
	 String saveImage,sevedImage;
	 private static final int IMAGE_PICK 	= 1;
		private static final int IMAGE_CAPTURE 	= 2;
	 public DataAdapter menu;
	 public static List<String>nameList= new ArrayList<String>();
	 public static List<String>priceList= new ArrayList<String>();
	 public static List<String>desList= new ArrayList<String>();
	 public static List<String>catList= new ArrayList<String>();
	 public static List<String>imageList= new ArrayList<String>();
	 public static List<String>stringList= new ArrayList<String>();
	public static List<String>ImageList= new ArrayList<String>();
	 public static List<String>menuList= new ArrayList<String>();
	 ArrayList<Constructormenu> DisplayData = new ArrayList<Constructormenu>();
	 ImageView load;
	 public static int i,p;
	 public static String ctg;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.menulist);
	  stringList.clear();
	  
	 /* Categ=UserMenu.Cate;
	  Log.d("category",""+Categ);*/
	  db=new DataBase(this);
	  data=new DataMenuImage(this);
	  menu=new DataAdapter(this,R.layout.itemslist, DisplayData);
	  menulist=(ListView)findViewById(R.id.list);
	  menulist.setAdapter(menu);
	  menuList.clear();
	  
	  db.open();
		Cursor get=db.getlistitems();
		Log.d("getdetails",""+get);
		Log.d("no of rows",""+get.getCount());
		Log.d("getdetails.moveToFirst()",""+get.moveToFirst());
		Log.d("db.getlistitems()",""+db.getlistitems());
		db.close();
		 if(get.moveToFirst())
		  {
		   do{
			
			   ctg=get.getString(1);
		    Log.d("categories",""+ctg);
		    
	        System.out.println("cat::::::::::"+ctg);
	        stringList.add(ctg);
	        Log.d("stringList",""+ stringList);
	      
			
		   }while(get.moveToNext());
		   get.close();
		}
		MenuItemsData();
	  
	 if(Name==null){
		 Toast.makeText(getApplicationContext(),"Menu is Empty", Toast.LENGTH_SHORT).show();

	 }
   menulist.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
				 i=1+menulist.getPositionForView(view);
				Log.d("i",""+i);
				
				 Context x=view.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.edit_menu);
			 		d.setTitle("ENTER MODIFIED DATA");
			 		d.show();
			 	final EditText name=(EditText)d.findViewById(R.id.name);
			 	name.setText(nameList.get(menulist.getPositionForView(view)));
			 		final EditText price=(EditText)d.findViewById(R.id.price);
			 		price.setText(priceList.get(menulist.getPositionForView(view)));
			 	final EditText des=(EditText)d.findViewById(R.id.description);
			 	des.setText(desList.get(menulist.getPositionForView(view)));
			 		final Button cate = (Button) d.findViewById(R.id.catbutton);
			 		cate.setText(catList.get(menulist.getPositionForView(view)));
			 		load=(ImageView)d.findViewById(R.id.loadimage);
			 		 //Resources res = getResources();
			 		Image=imageList.get(menulist.getPositionForView(view));
			         Bitmap bitmap = BitmapFactory.decodeFile(imageList.get(menulist.getPositionForView(view)));
			         Log.d("Image",""+Image);
			        // BitmapDrawable bd = new BitmapDrawable(res, bitmap);
			       
			         load.setImageBitmap(bitmap);
					 Button sub = (Button)d. findViewById(R.id.sub);
					Button upload = (Button)d. findViewById(R.id.imageupload);
					Button capt = (Button)d. findViewById(R.id.capture);
					cate.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
							
							final CharSequence[] items = stringList.toArray(new CharSequence[ stringList.size()]);;
							Log.d("items",""+items);
							AlertDialog.Builder builder = new AlertDialog.Builder(AddmenuList.this);
							builder.setTitle("Alert Dialog with ListView");
							builder.setIcon(R.drawable.ic_launcher);
							builder.setItems(items, new DialogInterface.OnClickListener() {
							    public void onClick(DialogInterface dialog, int item) {
							       
							    	//Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
							   
							    cate.setText(items[item]);
							    Log.d("select category",""+cate);
							    }
							});
							AlertDialog alert = builder.create();

							alert.show();
			              
						}
					});
					
					upload.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							intent.setType("image/*");
							
				            final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MenuImages");
				           
						    
								 imagesFolder.mkdirs(); //directory is created;

						    
							
				            

							startActivityForResult(Intent.createChooser(intent, "Escolha uma Foto"), IMAGE_PICK);
						}
						});
					capt.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
							
							final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MenuImages");

							
						   
								 imagesFolder.mkdirs(); //directory is created;

						  
							
							startActivityForResult(intent, IMAGE_CAPTURE);
						}
						});
					 sub.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						
							Ename=name.getText().toString();
						    Log.d(" Ename",""+ Ename);
						    Eprice=price.getText().toString();
						    Log.d(" Eprice",""+ Eprice);
						    Ecat=cate.getText().toString();
						    Log.d(" Ecat",""+ Ecat);
						    Log.d("Image",""+Image);
						    Edes=des.getText().toString();
						    Log.d(" Edes",""+ Edes);
						    
						System.out.println("name::::::"+Name);
						if(ImageList.size()>0){
							String EditImage=ImageList.get(0);
							Log.d("editImage",""+EditImage);
							data.open();
							data.updateTitle(i, Ename,Eprice, Ecat,Edes,EditImage);
							Log.d("i",""+i);
							data.close();
							Log.d("saveImage",""+sevedImage);
							
							DisplayData.remove(i-1);
							Log.d("remove",""+i);
							menulist.setAdapter(menu);
							MenuItemsData();
							//DisplayData.add(new Constructormenu(Ename,Eprice, Ecat));
							d.cancel();
							 
						}
						else{
							
							Log.d("Image",""+Image);
							 Log.d("saveImage",""+saveImage);
							data.open();
							data.updateTitle(i, Ename,Eprice, Ecat,Edes,Image);
							Log.d("i",""+i);
							data.close();
						
							DisplayData.remove(i-1);
							menulist.setAdapter(menu);
							MenuItemsData();
							//DisplayData.add(new Constructormenu(Ename,Eprice, Ecat));
							
							 d.cancel();
						}
						}
					});
			 		/*del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							data.open();
							data.deleteTitle(i);
							data.close();
							 menulist.setAdapter(menu);
							d.cancel();
							
						
						}
					});*/
			}
			});
	  
	 }
	 
 private void MenuItemsData() {
		// TODO Auto-generated method stub
	 nameList.clear();
	 priceList.clear();
	 desList.clear();
	 catList.clear();
	 imageList.clear();
	 menuList.clear();
	 DisplayData.clear();
	 data.open();
	  Cursor getdetails=data.getlistitems();
	  data.close();
	 if(getdetails.moveToFirst())
	  {
	   do{
		   
	    Name=getdetails.getString(0);
	   Price=getdetails.getString(1);
	  Category=getdetails.getString(2);
	  Des=getdetails.getString(3);
	  Image=getdetails.getString(4);
	  Log.d("Image",""+Image);
	 /* byte[] bb = getdetails.getBlob(getdetails.getColumnIndex("image"));
	  ByteArrayInputStream inputStream = new ByteArrayInputStream(bb);
	    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
	 // myImage.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));
*/	   nameList.add(Name);
          Log.d("nameList",""+nameList.size());
      priceList.add(Price);
      Log.d("priceList",""+priceList.size());
      desList.add(Des);
      Log.d(" desList",""+ desList.size());
     catList.add(Category);
     Log.d("catList",""+catList.size());
     imageList.add(Image);
     Log.d("imageList",""+imageList.size());
	  Log.d("selected Name",""+Name);
	  Log.d("selected price",""+Price);
	  Log.d("selected Category",""+Category);
       System.out.println("name::::::::::"+Name);
      menuList.add(Name+":"+Price);
        Log.d("menulist",""+menuList);
       DisplayData.add(new Constructormenu(Name,Price,Category));
		   
	   }while(getdetails.moveToNext());
	  }

	 getdetails.close();
	
	 
	}

public class DataAdapter extends BaseAdapter{
	     
	     private Context context;
	        private List<Constructormenu> buttonList;
	     //private int rowResID;
	     public DataAdapter(Context context, int rowResID,
	       List<Constructormenu> buttonList ) { 
	       this.context = context;
	       //this.rowResID = rowResID;
	       this.buttonList = buttonList;
	     }
	        public int getCount() {                        
	            return buttonList.size();
	        }
	        public Object getItem(int position) {     
	            return buttonList.get(position);
	        }
	        public long getItemId(int position) {  
	            return position;
	        }
	        public View getView(final int position, View convertView, ViewGroup parent) { 
	         
	            final Constructormenu row = buttonList.get(position);
	            LayoutInflater inflater = LayoutInflater.from( context );
	            View v = inflater.inflate( R.layout.itemslist, parent, false );
	            
	            final TextView nametext = (TextView)v.findViewById( R.id.nametext);
	           
	            final TextView pricetext = (TextView)v.findViewById( R.id.pricetext);
	            final TextView categorytext = (TextView)v.findViewById( R.id.categorytext);
	        nametext.setText(row.Name());
	        pricetext .setText(row.Price());
	       categorytext .setText(row.Category());
	    
	      /*nametext.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					 i=menulist.getPositionForView(v);
					
					 Log.d("i",""+i);
					 // p=i+1;
					 Intent in=new Intent(getApplicationContext(),Order.class);    
				     startActivity(in);
				    Context x=v.getContext();
					 Log.d("x",""+x);
			 		d=new Dialog(x);
			 		d.setContentView(R.layout.modifiedcat);
			 		d.setTitle("ENTER MODIFIED DATA");
			 		d.show();
			 		final EditText t3=(EditText)d.findViewById(R.id.edit);
			 		t3.setText(row.Name());
			 		Button ok=(Button)d.findViewById(R.id.okbutton);
			 		Button del=(Button)d.findViewById(R.id.delete);
			 		ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
						Name=t3.getText().toString();
						
						System.out.println("name::::::"+Name);
						nametext.setText(Name);
						data.open();
						data.updateTitle(i, Name);
						data.close();
						d.cancel();
						}
					});
			 		del.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							data.open();
							data.deleteTitle(i);
							data.close();
							 menulist.setAdapter(menu);
							d.cancel();
							
						
						}
					});
				}
			});
	      
	*/
	        return v;
	        } 
	    }
 private Bitmap decodeFile(String f) {
		Bitmap b = null;
		try {
			// Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;

			FileInputStream fis = new FileInputStream(f);
			BitmapFactory.decodeStream(fis, null, o);
			fis.close();

			int scale = 1;
			if (o.outHeight > 100 || o.outWidth > 100) {
				scale = (int) Math.pow(2, (int) Math.round(Math
						.log(100 / (double) Math.max(o.outHeight, o.outWidth))
						/ Math.log(0.5)));
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			fis = new FileInputStream(f);
			b = BitmapFactory.decodeStream(fis, null, o2);
			fis.close();
		} catch (IOException e) {
		}
		return b;
	}
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 	
 	if (resultCode == Activity.RESULT_OK) { 
	    	switch (requestCode) {
			case IMAGE_PICK:	
				this.imageFromGallery(resultCode, data);
				break;
			case IMAGE_CAPTURE:
				this.imageFromCamera(resultCode, data);
				break;
			default:
				break;
			}
 	}
 }


 
 /**
  * Image result from camera
  * @param resultCode
  * @param data
  */
 private void imageFromCamera(int resultCode, Intent data) {
 	 Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
 	 load.setImageBitmap(thumbnail);
         //3
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
         //4
         SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         //Log.d("imagenum displaying....!",""+imageNum);
        imageNum =  settings.getInt("imageNum", 0);
         
        saveImage = "Editcamera_" + String.valueOf(imageNum) + ".jpg";
 		Log.d("imagenum displaying....!",""+imageNum);
 	   imageNum=++imageNum;
 	   Editor editor1 = settings.edit();
        editor1.putInt("imageNum",imageNum);
        editor1.commit();
       Log.d("imagenum displaying....!",""+imageNum);
         File file = new File(Environment.getExternalStorageDirectory()+File.separator +saveImage);
         Log.d("cameraimage",""+file);
         ImageList.clear();
         sevedImage="/mnt/sdcard/"+saveImage;
         
         ImageList.add(sevedImage);
     	Log.d("ImageList",""+ImageList);
         try {
             file.createNewFile();
             FileOutputStream fo = new FileOutputStream(file);
             //5
             fo.write(bytes.toByteArray());
             fo.close();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
 }
 
 private void imageFromGallery(int resultCode, Intent data) {
 	
 	try{
 	Runtime.getRuntime().gc();
 	Uri selectedImageUri = data.getData();
 
     selectedImagePath = getPath(selectedImageUri);
     System.out.println("Image Path : " + selectedImagePath);
     Bitmap yourSelectedImage = decodeFile(selectedImagePath);
     load.setImageBitmap(yourSelectedImage);
     Drawable d =new BitmapDrawable(yourSelectedImage);
     File path = Environment.getExternalStoragePublicDirectory(
		        Environment.DIRECTORY_PICTURES);
     SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
     //Log.d("imagenum displaying....!",""+imageNum);
    imageNum =  settings.getInt("imageNum", 0);
     
		String fileName = "Editimage_" + String.valueOf(imageNum) + ".jpg";
		Log.d("imagenum displaying....!",""+imageNum);
	   imageNum=++imageNum;
	   Editor editor1 = settings.edit();
    editor1.putInt("imageNum",imageNum);
    editor1.commit();
   Log.d("imagenum displaying....!",""+imageNum);
   //String value = settings.getString("imageNum", "");
   
	 
		
		File file = new File("/mnt/sdcard/MenuImages/"+fileName);
		
		

	    InputStream is =  new FileInputStream(selectedImagePath);
	
	    OutputStream os = new FileOutputStream(file);
	    
	    byte[] data1 = new byte[is.available()];
	    is.read(data1);
	    os.write(data1);
	    is.close();
	    os.close();	 
	    MediaScannerConnection.scanFile(this,
	            new String[] { file.toString() }, null,
	            new MediaScannerConnection.OnScanCompletedListener() {
	        public void onScanCompleted(String path, Uri uri) {
	        	
	        	ImageList.clear();
	        	sevedImage=path;
	        	
	        	Log.d("saveImage",""+sevedImage);
	        	ImageList.add(sevedImage);
	        	Log.d("ImageList",""+ImageList);
	            Log.i("ExternalStorage", "Scanned " + path + ":");
	            Log.i("ExternalStorage", "-> uri=" + uri);
	        }
	    });
 	}
 	catch(IOException e) {
 		
 	}
}



public String getPath(Uri uri) {
String[] projection = { MediaStore.Images.Media.DATA };
Cursor cursor = managedQuery(uri, projection, null, null, null);
int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
cursor.moveToFirst();

return cursor.getString(column_index);
}
}
