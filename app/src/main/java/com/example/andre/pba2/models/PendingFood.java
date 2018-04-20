package com.example.andre.pba2.models;

import com.orm.SugarRecord;

public class PendingFood extends SugarRecord {

    private String name, descripcion;
    private boolean done;

    public PendingFood() {
    }

    public PendingFood(String name, String descripcion, boolean done) {
        this.name = name;
        this.descripcion = descripcion;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
