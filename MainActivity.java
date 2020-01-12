/*
 *
 * Pablo Bautista © 2018
 * CSD 230 - Proof Sheet 2
 *
 *
 */

package com.example.pablobautista.proofsheet2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
//import android.widget.ShareActionProvider;
import android.support.v7.widget.ShareActionProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private  ShareActionProvider shareActionProvider;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView[] pics;
    int count;
    int pos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.delete:
                pics[pos].setImageBitmap(null);
                shiftImg(pos);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);

        pics = new ImageView[]{
                findViewById(R.id.img1),
                findViewById(R.id.img2),
                findViewById(R.id.img3),
                findViewById(R.id.img4),
                findViewById(R.id.img5),
                findViewById(R.id.img6),
                findViewById(R.id.img7),
                findViewById(R.id.img8),
                findViewById(R.id.img9),
                findViewById(R.id.img10),
                findViewById(R.id.img11),
                findViewById(R.id.img12),
                findViewById(R.id.img13),
                findViewById(R.id.img14),
                findViewById(R.id.img15),
                findViewById(R.id.img16),
                findViewById(R.id.img17),
                findViewById(R.id.img18),
        };

        //Rotation
        if(savedInstanceState != null) {
            for (int i = 0; i < pics.length; i++) {
                Bitmap bitmap = savedInstanceState.getParcelable("img"+i);
                pics[i].setImageBitmap(bitmap);
            }
            count = savedInstanceState.getInt("count");
        }

        //Listener for images
        for (int i = 0; i< pics.length; i++)
        {
            pics[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    switch (v.getId())
                    {
                        case R.id.img1:
                            pos = 0;
                            //setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img2:
                            pos = 1;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img3:
                            pos = 2;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img4:
                            pos = 3;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img5:
                            pos = 4;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img6:
                            pos = 5;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img7:
                            pos = 6;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img8:
                            pos = 7;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img9:
                            pos = 8;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img10:
                            pos = 9;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img11:
                            pos = 10;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img12:
                            pos = 11;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img13:
                            pos = 12;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img14:
                            pos = 13;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img15:
                            pos = 14;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img16:
                            pos = 15;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img17:
                            pos = 16;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                        case R.id.img18:
                            pos = 17;
                            setShareActionIntent("Want to see a picture # " + Integer.toString(pos)+"?");
                            break;
                    }
                }
            });
        }

    }

    //Share button handling function
    private void setShareActionIntent(String text)
    {
        Uri uri = getLocalBitmapUri(pics[pos]);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        shareActionProvider.setShareIntent(intent);
    }

    //Bitmap display in image view
    public Uri getLocalBitmapUri(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }

        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir
                    (Environment.DIRECTORY_PICTURES),
                    "share_image" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();


            bmpUri = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    //Shift the grid when a picture is deleted
    public void shiftImg(int gapPos)
    {
        for (int i = gapPos; i< count; i++){

            Drawable bm = pics[i+1].getDrawable();
            pics[i].setImageDrawable(bm);
            pics[i+1].setImageBitmap(null);
        }
        count--;
    }

    //Saving
    public void onSaveInstanceState(Bundle saveInstanceState) {
        for (int i = 0; i < pics.length; i++) {
            BitmapDrawable drawable = (BitmapDrawable)pics[i].getDrawable();
            if (drawable!= null) {
                Bitmap bitmap = drawable.getBitmap();
                saveInstanceState.putParcelable("img"+i,bitmap);
                saveInstanceState.putInt("count",count);
                super.onSaveInstanceState(saveInstanceState);
            }
        }
    }

    public void onTakePictureClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, 80, 80, false);

            if(count==18) count = 0;
            pics[count].setImageBitmap(scaledBitmap);
            count++;

        }


    }

}