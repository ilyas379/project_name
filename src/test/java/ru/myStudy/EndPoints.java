package ru.myStudy;

public final class EndPoints {
    public final static String MANUFACTURES = "/car/manufacturers";
    public final static String MANUFACTURE = "/car/manufacturers/{id}";
    public final static String SECURED = "/secured/app";

    private EndPoints(){
        throw new IllegalStateException();
    }
}
