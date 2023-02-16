package com.numberONE.maryfarm.ui.chat;

/**
 * Created by Naik on 24.02.17.
 */
public class EchoModel {

    private int id;
    private String echo;

    public EchoModel() {
    }
    public long getId() {
        return id;
    }
    public String getEcho() {
        return echo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEcho(String echo) {
        this.echo = echo;
    }
}
