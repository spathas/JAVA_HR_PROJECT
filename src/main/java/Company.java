public class Company implements CompanyController {

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

//    public static HashMap<Integer, Company> fetchCompanies(Server server) throws SQLException, ClassNotFoundException {
//
//        HashMap<Integer, Company> companies = new HashMap<Integer, Company>();
//
//        server.connectionOpen();
//        ResultSet resultSet = server.executeQuery("SELECT id, name, email, country, phone, address FROM company");
//
//        String id = null;
//        String name = null;
//        String email = null;
//        String country = null;
//        String phone = null;
//        String address = null;
//
//        while (resultSet.next()) {
//            id = resultSet.getString("ID");
//            name = resultSet.getString("name");
//            email = resultSet.getString("email");
//            country = resultSet.getString("country");
//            phone = resultSet.getString("phone");
//            address = resultSet.getString("address");
//            System.out.println(id + " | " + name + " | " + email + " | " + country + " | " + phone + " | " + address + "\n");
//            companies.put(Integer.parseInt(id), new Company(name, email, country, phone, address));
//        }
//
//        server.connectionClose();
//        return companies;
//    }

    ///////GETTERS/////////
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

    @Override
    public String toString() {
        return " || " + this.getName() + " | " + this.getEmail() + " | " + this.getCountry() + " | " + this.getPhone() + " | " + this.getAddress();
    }
}
