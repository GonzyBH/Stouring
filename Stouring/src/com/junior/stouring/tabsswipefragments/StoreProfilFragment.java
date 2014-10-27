package com.junior.stouring.tabsswipefragments;

import com.junior.stouring.R;
import com.junior.stouring.TouringPlace;
import com.junior.stouring.TouringPlaceDatabaseHelper;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StoreProfilFragment extends Fragment{
	
	// this is the action code we use in our intent, 
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;
    
 // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    
    private String selectedImagePath;
    
    TouringPlace tP;
	
	TouringPlaceDatabaseHelper mDatabaseHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_storeprofil, container, false);
		
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getActivity());
		
		tP = getArguments().getParcelable("TP");
		
		TextView displayPlaceInfo = (TextView) view.findViewById(R.id.tpname);
		displayPlaceInfo.setText("Les offres de " + tP.getName() + " seront ajoutées ici");
		
		Button bChangeImage = (Button) view.findViewById(R.id.btnSearchImage);
        bChangeImage.setText("Ajouter une image depuis la galerie");
        bChangeImage.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
				
					CaptureImageFromGallery();
			}
		});
        
        Button bTakeImage = (Button) view.findViewById(R.id.btnTakeImage);
        bTakeImage.setText("Ajouter une image depuis l'appareil");
        bTakeImage.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
				
					CaptureImageFromCamera();
			}
		});
        
        
		return view;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getActivity();
		if (resultCode == FragmentActivity.RESULT_OK) { 
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                
                Bitmap dynamicImage = decodeSampledBitmapFromResource(selectedImagePath, 100, 100);
                
                tP.setImage(dynamicImage);
				
				mDatabaseHelper.updateTP(tP);
            }
            else if(requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            	Bundle extras = data.getExtras();
                Bitmap takenImage = (Bitmap) extras.get("data");
                
                tP.setImage(takenImage);
				
				mDatabaseHelper.updateTP(tP);
            }
        }
    }
	
	public void CaptureImageFromGallery(){
		
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
	}
	
	public void CaptureImageFromCamera(){
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	    }

	}

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
            // just some safety built in 
            if( uri == null ) {
                // TODO perform some logging or show user feedback
                return null;
            }
            String res = null;
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(uri, proj, null, null, null);
            if(cursor.moveToFirst()){;
               int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
               res = cursor.getString(column_index);
            }
            cursor.close();
            return res;
    }
    
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }

	    return inSampleSize;
    }
    
    public static Bitmap decodeSampledBitmapFromResource(String path,
            int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

}