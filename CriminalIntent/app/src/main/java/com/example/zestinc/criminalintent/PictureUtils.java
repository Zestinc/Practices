package com.example.zestinc.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by zestinc on 2017/1/4.
 */

public class PictureUtils {
    private static final String TAG = "PictureUtils";
    /**
     * Get a BitmapDrawable from a local file that is scaled down
     * to fit the current Window size
     */
    @SuppressWarnings("deprecation")
    public static BitmapDrawable getScaledDrawable(Activity a, String path) {
        Display display = a.getWindowManager().getDefaultDisplay();
        float destWidth = display.getWidth();
        float destHeight = display.getHeight();

        // Read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            Log.e(TAG, "decodeFile Fail!", e);
        }

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            return new BitmapDrawable(a.getResources(), bitmap);
        } catch (Exception ex){
            Log.e(TAG, "Fail to decode File", ex);
        }
        return null;
    }

    public static void cleanImageView(ImageView imageView) {
        if (!(imageView.getDrawable() instanceof BitmapDrawable))
            return ;

        // Clean up the view's image for the sake of memory
        BitmapDrawable b = (BitmapDrawable) imageView.getDrawable();
        /**
         * Although Google doc imply recycle is not explicitly needed as Finalizer will recycle
         * the memory it occupied. However, memory may run out before Finalizer can run. Therefore,
         * we manager to recycle by its own recycle mechanism as soon as it finishes.
         */
        b.getBitmap().recycle();
        imageView.setImageDrawable(null);
    }
}
