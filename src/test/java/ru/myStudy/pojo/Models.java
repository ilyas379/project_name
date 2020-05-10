package ru.myStudy.pojo;

import java.math.BigDecimal;

public class Models
{
    private String id;

    private BigDecimal averagePrice;

    private String title;

    public Models() {
    }

    public Models(String id, BigDecimal averagePrice, String title) {
        this.id = id;
        this.averagePrice = averagePrice;
        this.title = title;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public BigDecimal getAveragePrice ()
    {
        return averagePrice;
    }

    public void setAveragePrice (BigDecimal averagePrice)
    {
        this.averagePrice = averagePrice;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", averagePrice = "+averagePrice+", title = "+title+"]";
    }
}

