package utils.xcommon.util.fastjson;

public class TestAddress {
	private String country;
	private String address;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "TestAddress [country=" + country + ", address=" + address + "]";
	}
}
