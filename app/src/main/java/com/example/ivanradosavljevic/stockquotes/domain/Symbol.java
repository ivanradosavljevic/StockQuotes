package com.example.ivanradosavljevic.stockquotes.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class Symbol implements Serializable {
    String name;
    String id;
    double quoteChangePercent;
    double quoteLast;
    double quoteChange;
    Date dateTime;
    double quoteOpen;
    double quoteHigh;
    double quoteLow;

    public Symbol() {
    }

    public Symbol(String name, String id, double quoteChangePercent, double quoteLast, double quoteChange, Date dateTime, double quoteOpen, double quoteHigh, double quoteLow) {
        this.name = name;
        this.id = id;
        this.quoteChangePercent = quoteChangePercent;
        this.quoteLast = quoteLast;
        this.quoteChange = quoteChange;
        this.dateTime = dateTime;
        this.quoteOpen = quoteOpen;
        this.quoteHigh = quoteHigh;
        this.quoteLow = quoteLow;
    }

    public String getName() {
        return name;
    }

    public double getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(double quoteChange) {
        this.quoteChange = quoteChange;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getQuoteOpen() {
        return quoteOpen;
    }

    public void setQuoteOpen(double quoteOpen) {
        this.quoteOpen = quoteOpen;
    }

    public double getQuoteHigh() {
        return quoteHigh;
    }

    public void setQuoteHigh(double quoteHigh) {
        this.quoteHigh = quoteHigh;
    }

    public double getQuoteLow() {
        return quoteLow;
    }

    public void setQuoteLow(double quoteLow) {
        this.quoteLow = quoteLow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getQuoteChangePercent() {
        return quoteChangePercent;
    }

    public void setQuoteChangePercent(double quoteChangePercent) {
        this.quoteChangePercent = quoteChangePercent;
    }

    public double getQuoteLast() {
        return quoteLast;
    }

    public void setQuoteLast(double quoteLast) {
        this.quoteLast = quoteLast;
    }
}
