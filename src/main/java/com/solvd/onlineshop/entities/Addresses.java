package com.solvd.onlineshop.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Addresses")
public class Addresses {
    @XmlElement(name = "Address", type = Address.class)
    private List<Address> addressList = new ArrayList<Address>();

    public Addresses() {}

    public Addresses(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddresses(List<Address> addressList) {
        this.addressList = addressList;
    }
}

