package com.example.recyclerviewdivisas;

import android.graphics.drawable.Drawable;

public class divisasEventModel {
    public String eventName;
    public String eventPrecio;
    public Drawable eventIc;

    public divisasEventModel(String eventName, String eventPrecio, Drawable eventIc) {
        this.eventName = eventName;
        this.eventPrecio = eventPrecio;
        this.eventIc = eventIc;
    }

    public String getDivisaName() {
        return eventName;
    }

    public String getDivisaPrecio() {
        return eventPrecio;
    }
    public Drawable getDivisaIC() {
        return eventIc;
    }



}
