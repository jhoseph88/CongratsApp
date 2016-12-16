package edu.umich.engin.congrats;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class CustomEntryAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> thumbnailUrls;
    private ArrayList<String> titles;
    private ArrayList<String> excerpts;
    private ArrayList<String> pageUrls;
    private static LayoutInflater inflater = null;
    private LoadImages imgLoader;
    // Custom constructor for CustomEntryAdapter
    public CustomEntryAdapter(Activity a, ArrayList<String> thumbnailUrls, ArrayList<String> titles,
                              ArrayList<String> excerpts, ArrayList<String> pageUrls) {
        this.activity = a;
        this.thumbnailUrls = thumbnailUrls;
        this.titles = titles;
        this.excerpts = excerpts;
        this.pageUrls = pageUrls;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imgLoader = new LoadImages(a);
    }

    @Override
    public int getCount() {
        return thumbnailUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // inflate for each for in entry list
            view = inflater.inflate(edu.umich.engin.congrats.R.layout.list_item, null);
        }
        TextView title = (TextView) view.findViewById(edu.umich.engin.congrats.R.id.title);
        String unencodedTitle = null;
        try {
            unencodedTitle = URLDecoder.decode( (titles.get(position) ), "ISO646-US" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } // Set attributes for entry
        // Title text will be all caps
        title.setText(unencodedTitle.toUpperCase() );
        TextView excerpt = (TextView) view.findViewById(edu.umich.engin.congrats.R.id.excerpt);
        excerpt.setText(URLDecoder.decode(excerpts.get(position) ) );
        ImageView img = (ImageView) view.findViewById(edu.umich.engin.congrats.R.id.imageView);
        //new LoadImages().new LoadImage(thumbnailUrls.get(position), img).execute();
        imgLoader.DisplayImage(thumbnailUrls.get(position), img);
        return view;
    }
}
