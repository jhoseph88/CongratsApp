package edu.umich.engin.congrats;

import android.os.Parcel;
import android.os.Parcelable;

public class Entry implements Parcelable {
    String id;
    private String title;
    private String excerpt;
    private String thumbnailUrl;
    private String pageUrl;

    Entry(String id, String title, String excerpt, String thumbnailUrl, String pageUrl) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.thumbnailUrl = thumbnailUrl;
        this.pageUrl = pageUrl;
    }

    protected Entry(Parcel in) {
        id = in.readString();
        title = in.readString();
        excerpt = in.readString();
        thumbnailUrl = in.readString();
        pageUrl = in.readString();
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    // getter for title
    String getTitle() {
        return title;
    }

    // getter for excerpt
    String getExcerpt() {
        return excerpt;
    }

    // getter for thumbNailUrl
    String getThumbnailUrl() {
        return thumbnailUrl;
    }

    // getter for pageUrl
    String getPageUrl() {
        return pageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.id, this.title, this.excerpt, this.thumbnailUrl,
                                            this.pageUrl});
    }
}
