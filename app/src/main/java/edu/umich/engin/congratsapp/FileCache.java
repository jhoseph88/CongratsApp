package edu.umich.engin.congratsapp;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileCache {

    private File cacheDirectory;
    private static final String CACHE_DIRECTORY_TITLE = "image_cache";

    // Allocate the directory where the cached images will go
    public FileCache(Context context) {
        if (android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
            cacheDirectory = new File(context.getExternalCacheDir().toString(),
                                      CACHE_DIRECTORY_TITLE);
        } else {
            cacheDirectory = context.getCacheDir();
        }
        if (!cacheDirectory.exists() ) {
            cacheDirectory.mkdirs();
        }
    }

    // Returns a file with the photo's hashed url as its name
    public File getFile(String imgUrl) {
        String fileName = String.valueOf(imgUrl.hashCode() );
        File f = new File(cacheDirectory, fileName);
        return f;
    }

    // Clear the directory
    public void clear() {
        File[] files = cacheDirectory.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

}
