package edu.umich.engin.congratsapp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;

public class LoadImages {

        ImageCache imgCache = new ImageCache();
        FileCache fileCache;

        private final static int CORRECT_IMAGE_SIZE = 256;

        private Map<ImageView, String> imageViews = Collections.synchronizedMap(
                new WeakHashMap<ImageView, String>());
        ExecutorService executorService;

        public LoadImages(Context context) {
            fileCache = new FileCache(context);
            executorService = Executors.newFixedThreadPool(5);
        }

        public void queuePhoto(String url, ImageView imageView) {
            PhotoToLoad photoToLoad = new PhotoToLoad(url, imageView);
            executorService.submit(new PhotoLoader(photoToLoad) );
        }

        private Bitmap getBitmap(String url) {
            File f = fileCache.getFile(url);

            Bitmap bitmap = decodeFile(f);
            if (bitmap != null) {
                return bitmap;
            }
            try {
                Bitmap b = null;
                URL imgUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(30000);
                conn.setInstanceFollowRedirects(true);
                InputStream inputStream = conn.getInputStream();
                OutputStream outputStream = new FileOutputStream(f);
                Utils.java.CopyStream(inputStream, outputStream);
                outputStream.close();
                b = decodeFile(f);
                return b;
            } catch (Throwable e) {
                e.printStackTrace();
                if (e instanceof  OutOfMemoryError) {
                    imgCache.clear();
                }
                return null;
            }
        }

        // Decode image, then scale it down for lessened memory load
        private Bitmap decodeFile(File f) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(new FileInputStream(f), null, options);

                int width = options.outWidth, height = options.outHeight, scale = 1;
                while (width >= CORRECT_IMAGE_SIZE || height > CORRECT_IMAGE_SIZE) {
                    width /= 2;
                    height /= 2;
                    scale *= 2;
                }
                BitmapFactory.Options options1 = new BitmapFactory.Options();
                options1.inSampleSize = scale;
                return BitmapFactory.decodeStream(new FileInputStream(f), null, options1);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        // Display image in the view provided if it exists
        public void DisplayImage(String url, ImageView imageView) {
            imageViews.put(imageView, url);
            Bitmap bitmap = imgCache.get(url);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                queuePhoto(url, imageView);
            }
        }

        // Photo for queue to load
        private class PhotoToLoad {
            public String url;
            public ImageView imageView;
            public PhotoToLoad(String u, ImageView img) {
                url = u;
                imageView = img;
            }
        }

        class PhotoLoader implements Runnable {
            PhotoToLoad photoToLoad;

            PhotoLoader(PhotoToLoad photoToLoad) {
                this.photoToLoad = photoToLoad;
            }

            @Override
            public void run() {
                if (imageViewReused(photoToLoad) ) {
                    return;
                } else {
                    Bitmap bitmap = getBitmap(photoToLoad.url);
                    imgCache.put(photoToLoad.url, bitmap);
                    if (imageViewReused(photoToLoad)) {
                        return;
                    } else {
                        BitmapDisplayer bitmapDisplayer = new BitmapDisplayer(bitmap, photoToLoad);
                        Activity activity = (Activity) photoToLoad.imageView.getContext();
                        activity.runOnUiThread(bitmapDisplayer);
                    }
                }
            }
        }

        boolean imageViewReused(PhotoToLoad photoToLoad) {
            String tag = imageViews.get(photoToLoad.imageView);
            if (tag == null || !tag.equals(photoToLoad.url) ) {
                return true;
            } else {
                return false;
            }
        }

        class BitmapDisplayer implements Runnable {

            Bitmap bitmap;
            PhotoToLoad photoToLoad;

            public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
                bitmap = b;
                photoToLoad = p;
            }

            public void run() {
                if (imageViewReused(photoToLoad) ) {
                    return;
                }
                if (bitmap != null) {
                    photoToLoad.imageView.setImageBitmap(bitmap);
                }
                else {
                    photoToLoad.imageView.setImageResource(R.mipmap.ic_launcher);
                }
            }
        }

}
