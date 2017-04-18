package model;

import java.util.Date;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class CalculationsFilter {
    private String fromYear;
    private Date fromDate = new Date();
    private String toYear;
    private Date toDate = new Date();

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
