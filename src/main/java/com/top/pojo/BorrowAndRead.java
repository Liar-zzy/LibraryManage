package com.top.pojo;

public class BorrowAndRead {

    private String borrowBookISBN;

    private String borrowBookName;

    private String borrowPersonId;

    private String lendTime;

    private String returnTime;

    public String getBorrowBookISBN() {
        return borrowBookISBN;
    }

    public void setBorrowBookISBN(String borrowBookISBN) {
        this.borrowBookISBN = borrowBookISBN;
    }

    public String getBorrowBookName() {
        return borrowBookName;
    }

    public void setBorrowBookName(String borrowBookName) {
        this.borrowBookName = borrowBookName;
    }

    public String getBorrowPersonId() {
        return borrowPersonId;
    }

    public void setBorrowPersonId(String borrowPersonId) {
        this.borrowPersonId = borrowPersonId;
    }

    public String getLendTime() {
        return lendTime;
    }

    public void setLendTime(String lendTime) {
        this.lendTime = lendTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
