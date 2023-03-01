package com.cit.hadis.gallery2202;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<Gallery> galleryList;
    GalleryAdapetr galleryAdapetr;

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        viewPager = findViewById(R.id.viewPager);
        galleryList = new ArrayList<>();

        permissionChecked();

    }

    private void permissionChecked() {

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();

                        getData();
                    }


                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        if(response.isPermanentlyDenied()){
                            Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void getData() {

        String [] row_cul = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_MODIFIED,
                MediaStore.Images.Media.DISPLAY_NAME
        };


        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri,row_cul,null,null,null);

        if (cursor != null){

            cursor.moveToPosition(0);

            while (true){

                long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));

                Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id);

                Log.i("TAG", "getData: "+ imageUri);


                Gallery gallery = new Gallery(id, name, imageUri);

                galleryList.add(gallery);

                if (!cursor.isLast()){
                    cursor.moveToNext();
                }else {
                    break;
                }

                galleryAdapetr=  new GalleryAdapetr(MainActivity.this, galleryList);

                recycler.setAdapter(galleryAdapetr);

                viewPager.setAdapter(galleryAdapetr);

            }



        }



    }
}