package com.product.actual;

import com.product.base.*;

import java.util.Calendar;
import java.util.Date;

public class MobileScratchCard extends Product implements Expirable {

    private Date expiringDate;

    public MobileScratchCard(String name, double price, int quantity, Date expiringDate) throws ProductException {
        super(name, price, quantity);
        setExpiringDate(expiringDate);
    }

    public void setExpiringDate(Date expiringDate) throws ProductException {

        //assuming max date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date maxDate = calendar.getTime();

        DataChecker.check(expiringDate, maxDate);
        this.expiringDate = expiringDate;
    }

    @Override
    public Date getExpiringDate() {
        return expiringDate;
    }
}
