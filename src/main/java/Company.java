import java.util.HashMap;

public class Company {

    int id;
    String name;
    String email;
    String country;
    String phone;
    String address;

    public Company(String name, String email, String country, String phone, String address) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.phone = phone;
        this.address = address;
    }

    public Company(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("id"));
        this.name = map.get("name");
        this.email = map.get("email");
        this.country = map.get("country");
        this.phone = map.get("phone");
        this.address = map.get("address");
    }

    ///////GETTERS/////////
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String,String> getCompanyMap() {
        HashMap<String,String> companyMap = new HashMap<>();

        companyMap.put("id", Integer.toString(getId()));
        companyMap.put("name", getName());
        companyMap.put("country", getCountry());
        companyMap.put("email", getEmail());
        companyMap.put("phone", getPhone());
        companyMap.put("address", getAddress());

        return companyMap;
    }

    @Override
    public String toString() {
        return " || " + this.getName() + " | " + this.getEmail() + " | " + this.getCountry() + " | " + this.getPhone() + " | " + this.getAddress();
    }
}