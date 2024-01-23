package ba.sum.fpmoz.estore;

import com.google.gson.annotations.SerializedName;

public class OlxResponse {
    @SerializedName("most_expensive")
    private String mostExpensive;

    // Other fields in the response, if any
    @SerializedName("least_expensive")
    private String leastExpensive;

    @SerializedName("average")
    private String average;

    // Constructors, getters, setters...

    public String getMostExpensive() {
        return mostExpensive;
    }

    public void setMostExpensive(String mostExpensive) {
        this.mostExpensive = mostExpensive;
    }

    public String getLeastExpensive() {
        return leastExpensive;
    }

    public void setLeastExpensive(String leastExpensive) {
        this.leastExpensive = leastExpensive;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
