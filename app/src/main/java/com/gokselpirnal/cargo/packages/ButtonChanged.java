package com.gokselpirnal.cargo.packages;

/**
 * Created by gokselpirnal on 14/05/2017.
 */

public class ButtonChanged {
    // add some fields
    private String title;

    public ButtonChanged(String title){
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
