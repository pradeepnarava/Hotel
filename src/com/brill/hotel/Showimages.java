package com.brill.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class Showimages extends Activity { 

private Uri[] mUrls; 
String[] mFiles=null; 

public void onCreate(Bundle icicle) { 
super.onCreate(icicle); 
setContentView(R.layout.show); 
File dir = new File( "/mnt/sdcard/Rasturant/" );    
//File images = Environment.getDataDirectory(); 
File[] imagelist = dir.listFiles(new FilenameFilter(){ 
public boolean accept(File dir, String name) 
{ 
return ((name.endsWith(".jpg"))||(name.endsWith(".png"))); 
} 
}); 

mFiles = new String[imagelist.length]; 

for(int i= 0 ; i< imagelist.length; i++) 
{ 
mFiles[i] = imagelist[i].getAbsolutePath(); 
} 
mUrls = new Uri[mFiles.length]; 

for(int i=0; i < mFiles.length; i++) 
{ 
mUrls[i] = Uri.parse(mFiles[i]); 
} 

Gallery g = (Gallery) findViewById(R.id.gallery); 
g.setAdapter(new ImageAdapter(Showimages.this)); 
g.setFadingEdgeLength(40); 
g.setSpacing(60);

} 
public class ImageAdapter extends BaseAdapter{ 
	// private List<String> mUrls;
int mGalleryItemBackground; 
public ImageAdapter(Context c) { 
mContext = c; 
} 
public int getCount(){ 
return mUrls.length; 
} 
public Object getItem(int position){ 
return position; 
} 
public long getItemId(int position) { 
return position; 
} 
public View getView(int position, View convertView, ViewGroup parent){ 
ImageView i = new ImageView(mContext); 
//Bitmap bm = BitmapFactory.decodeFile(  
        //mUrls.get(position).toString());  
     // i.setImageBitmap(bm);
//Log.d("imageUrls displaying....!",""+mUrls[position]);

//i.setImageURI(mUrls[position]); 
i.setImageBitmap(decodeFile(mUrls[position].toString()));
i.setScaleType(ImageView.ScaleType.FIT_XY); 
i.setLayoutParams(new Gallery.LayoutParams(420, 480)); 

return i;
} 
private Context mContext; 

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
} 


//http://stackoverflow.com/questions/5766579/store-an-image-in-internal-storage-in-android
//http://stackoverflow.com/questions/4920535/most-efficient-way-to-read-all-files-into-a-list-in-android
//http://www.coderanch.com/t/443531/Android/Mobile/display-Images-stored-sdcard
//http://viralpatel.net/blogs/2008/11/files-directory-listing-in-java.html