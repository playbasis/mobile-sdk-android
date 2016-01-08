package com.playbasis.android.playbasissdk.model;

/**
 * Created by Pongsakorn on 1/8/2016.
 */
public class SaleReport {


    Integer amount;
    Integer previous_amount;
    double percent_changed;

    public SaleReport() {
        this.amount = 0;
        this.previous_amount = 0;
        this.percent_changed = 0;
    }


    public void setAmount(Integer Amount) {this.amount = Amount;  }
    public void setPreviousAmount(Integer Amount) {this.previous_amount = Amount;  }
    public void setpercent_changed(double Percent_changed) {this.percent_changed = Percent_changed;  }
}
