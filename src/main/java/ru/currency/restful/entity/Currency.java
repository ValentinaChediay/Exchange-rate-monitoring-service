package ru.currency.restful.entity;

public class Currency {
    private String charCode;
    private Double value;
    private Double previous;


    public Currency(String charCode, Double value, Double previous) {
        this.charCode = charCode;
        this.value = value;
        this.previous = previous;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getPrevious() {
        return previous;
    }

    public void setPrevious(Double previous) {
        this.previous = previous;
    }
}
