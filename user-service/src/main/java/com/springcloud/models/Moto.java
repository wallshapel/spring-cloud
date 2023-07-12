package com.springcloud.models;

public class Moto {
    
	private String trademark;
    
	private String model;    

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public Moto() {
        super();
    }
}
