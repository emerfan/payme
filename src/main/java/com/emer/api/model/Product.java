package com.emer.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
    @Id
    private Long id;

    private String price;

    private String status;

    private String name;

    public Long getId () {
    	return id;
    }

    public void setId (Long id) {
    	this.id = id;
    }

    public String getPrice () {
    	return price;
    }

    public void setPrice (String price) {
    	this.price = price;
    }

	public String getStatus () {
		return status;
	}

	public void setStatus (String status) {
		this.status = status;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
			this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id = "+id+", price = "+price+", status = "+status+", name = "+name+"]";
	}
}
