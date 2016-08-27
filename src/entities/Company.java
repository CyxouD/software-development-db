package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Company extends Entity {
    private Integer id;
    private String name;
    private String fullAddress;
    private Integer staff;
    private Double annualProfit;
    private String password;
    private String privilege_level;

    public Company(List<Object> list) {
        super(list);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public Integer getStaff() {
        return staff;
    }

    public Double getAnnualProfit() {
        return annualProfit;
    }

    public String getPassword() {
        return password;
    }

    public String getPrivilege_level() {
        return privilege_level;
    }

    public void setPrivilege_level(String privilege_level) {
        this.privilege_level = privilege_level;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        id = list.get(0).equals("")? null : Integer.parseInt((String) list.get(0));
        name = list.get(1).equals("")? null : (String) list.get(1);
        fullAddress = list.get(2).equals("")? null : (String) list.get(2);
        staff = list.get(3).equals("")? null : Integer.parseInt((String) list.get(3));
        annualProfit = list.get(4).equals("")? null : Double.parseDouble((String) list.get(4));
        password = list.get(5).equals("") ? null : (String) list.get(5);
        if (list.size() == 7) {
            privilege_level = list.get(6).equals("") ? null : (String) list.get(6);
        }
    }
}
