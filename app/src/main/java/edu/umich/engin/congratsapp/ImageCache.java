package edu.umich.engin.congratsapp;

import android.graphics.Bitmap;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class ImageCache {
    private Map<String, Bitmap> cache = Collections.synchronizedMap(
            new LinkedHashMap<String, Bitmap>(10, 1.5f, true) );
    // running cache size in bytes
    private int size = 0;
    // maximum cache size in bytes
    private long capacity = 1000000;

    // Memory will initially be 1 / 8 of entire heap capacity
    public ImageCache() {
        setCapacity(Runtime.getRuntime().maxMemory() / 8);
    }

    public void setCapacity(long newCapacity) {
        capacity = newCapacity;
    }

    // Return size of bitmap in bytes
    long getSizeInBytes(Bitmap bitmap) {
        if(bitmap == null) {
            return 0;
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    // Get image by id
    public Bitmap get(String id) {
        try {
            if (!cache.containsKey(id) ) {
                return null;
            } else {
                return cache.get(id);
            }
        } catch(NullPointerException e) {
            e.printStackTrace();;
            return null;
        }
    }

    // Put image with specified id
    public void put(String id, Bitmap bitmap) {
        try {
            if (cache.containsKey(id)) {
                size -= getSizeInBytes(cache.get(id) );
            }
            cache.put(id, bitmap);
            size += getSizeInBytes(bitmap);
            checkSize();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // Remove images from cache until size is less than or equal to capacity.
    private void checkSize() {
        if (size > capacity) {
            Iterator<Entry<String, Bitmap>> i = cache.entrySet().iterator();
            while (i.hasNext() && size <= capacity) {
                Entry<String, Bitmap> entry = i.next();
                size -= getSizeInBytes(entry.getValue() );
                i.remove();
            }
        }
    }

    // Clear cache
    public void clear() {
       try {
           cache.clear();
           size = 0;
       } catch(NullPointerException e) {
           e.printStackTrace();
       }
    }

}
