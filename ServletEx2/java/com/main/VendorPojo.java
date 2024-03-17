package com.main;


public class VendorPojo {
    private String vendorID;
    private String name;
    private String contact;
    private String serviceType;
    private String SLA;
    
    
	public VendorPojo(String vendorID, String name, String contact, String serviceType, String sLA) {
		super();
		this.vendorID = vendorID;
		this.name = name;
		this.contact = contact;
		this.serviceType = serviceType;
		SLA = sLA;
	}


	public String getVendorID() {
		return vendorID;
	}


	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public String getSLA() {
		return SLA;
	}


	public void setSLA(String sLA) {
		SLA = sLA;
	}
    
    
    
    

    
}



