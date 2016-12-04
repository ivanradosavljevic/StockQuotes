package com.example.ivanradosavljevic.stockquotes.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class Symbol implements Serializable {
    String name;
    String id;
    String quoteChangePercent;
    String quoteLast;
    String quoteChange;
    Date dateTime;
    String quoteOpen;
    String quoteHigh;
    String quoteLow;
    String quoteVolume;
    String quoteBid;
    String quoteAsk;

    public Symbol() {
    }

    public String getQuoteBid() {
        return quoteBid;
    }

    public void setQuoteBid(String quoteBid) {
        this.quoteBid = quoteBid;
    }

    public String getQuoteAsk() {
        return quoteAsk;
    }

    public void setQuoteAsk(String quoteAsk) {
        this.quoteAsk = quoteAsk;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getName() {
        return name;
    }

    public String getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(String quoteChange) {
        this.quoteChange = quoteChange;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(String text) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            dateTime = dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getQuoteOpen() {
        return quoteOpen;
    }

    public void setQuoteOpen(String quoteOpen) {
        this.quoteOpen = quoteOpen;
    }

    public String getQuoteHigh() {
        return quoteHigh;
    }

    public void setQuoteHigh(String quoteHigh) {
        this.quoteHigh = quoteHigh;
    }

    public String getQuoteLow() {
        return quoteLow;
    }

    public void setQuoteLow(String quoteLow) {
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

    public String getQuoteChangePercent() {
        return quoteChangePercent;
    }

    public void setQuoteChangePercent(String quoteChangePercent) {
        this.quoteChangePercent = quoteChangePercent;
    }

    public String getQuoteLast() {
        return quoteLast;
    }

    public void setQuoteLast(String quoteLast) {
        this.quoteLast = quoteLast;
    }
}
