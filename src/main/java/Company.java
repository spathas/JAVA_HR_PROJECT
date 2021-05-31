import java.util.HashMap;

public class Company {

    private int id;
    private String name;
    private String email;
    private String country;
    private String phone;
    private String address;

    public Company() {
        this.name = "Unknown company";
        this.email = "Unknown email";
        this.country = "Unknown country";
        this.phone = "Unknown phone";
        this.phone = "Unknown phone";
    }

    public Company(String name, String email, String country, String phone, String address) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.phone = phone;
        this.address = address;
    }

    public Company(int id, String name, String email, String country, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.phone = phone;
        this.address = address;
    }

    public Company(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("ID"));
        this.name = map.get("NAME");
        this.email = map.get("EMAIL");
        this.country = map.get("COUNTRY");
        this.phone = map.get("PHONE");
        this.address = map.get("ADDRESS");
    }

    ///////SETTERS/////////
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public HashMap<String,String> getMap() {
        HashMap<String,String> companyMap = new HashMap<>();

        if(getId() > 0) companyMap.put("ID", Integer.toString(getId()));
        companyMap.put("NAME", getName());
        companyMap.put("COUNTRY", getCountry());
        companyMap.put("EMAIL", getEmail());
        companyMap.put("PHONE", getPhone());
        companyMap.put("ADDRESS", getAddress());

        return companyMap;
    }

    @Override
    public String toString() {
        return this.getName() + " | " + this.getEmail() + " | " + this.getCountry() + " | " + this.getPhone() + " | " + this.getAddress();
    }
}
