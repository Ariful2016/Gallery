package com.cit.hadis.gallery2202;

import android.net.Uri;

public class Gallery {

    long id;
    String name;
    Uri uri;


    public Gallery(long id, String name, Uri uri) {
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
