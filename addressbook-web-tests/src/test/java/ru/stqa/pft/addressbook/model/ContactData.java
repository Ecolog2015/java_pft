package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;
    private final String homephone;
    private final String mobilephone;
    private final String workphone;
    private final String faxphone;
    private String group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String address, String homephone, String mobilephone, String workphone, String faxphone, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.faxphone = faxphone;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getFaxphone() {
        return faxphone;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(firstname, that.firstname)) return false;
        return Objects.equals(middlename, that.middlename);
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        return result;
    }
}
