package app.pentastagiu.ro.ultrashopmobile.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import app.pentastagiu.ro.ultrashopmobile.R;

/**
 * Created by Razvan on 30/06/2015.
 * Used for downloading images in background.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Bitmap temp = BitmapFactory.decodeResource(null, R.mipmap.ic_launcher);
        bmImage.setImageBitmap(temp);
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap productImage = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            productImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("imgError", "Error downloading image: ", e);
        }
        return productImage;
    }

    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        bmImage.setImageBitmap(result);
    }
}
