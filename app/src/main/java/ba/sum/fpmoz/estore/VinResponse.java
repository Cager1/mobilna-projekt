package ba.sum.fpmoz.estore;

import com.google.gson.annotations.SerializedName;

public class VinResponse {
    @SerializedName("VIN")
    private String vin;

    @SerializedName("Make")
    private String make;

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("Manufactured in")
    private String manufacturedIn;

    @SerializedName("Model Year")
    private String modelYear;

    // Constructors, getters, setters...

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturedIn() {
        return manufacturedIn;
    }

    public void setManufacturedIn(String manufacturedIn) {
        this.manufacturedIn = manufacturedIn;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }


}
