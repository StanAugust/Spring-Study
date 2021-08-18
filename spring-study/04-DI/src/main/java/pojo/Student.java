package pojo;

import java.util.*;

public class Student {

    private String name;
    private Address address;
    private String[] majors;
    private List<String> hobbies;
    private Map<String, String> cards;
    private Set<String> games;
    private String partner;
    private Properties info;

    /*************getter and setter***************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getMajors() {
        return majors;
    }

    public void setMajors(String[] majors) {
        this.majors = majors;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getCards() {
        return cards;
    }

    public void setCards(Map<String, String> cards) {
        this.cards = cards;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "pojo.Student\n{" +
                "name='" + name + '\'' + '\n' +
                "address=" + address + '\n' +
                "majors=" + Arrays.toString(majors) + '\n' +
                "hobbies=" + hobbies + '\n' +
                "cards=" + cards + '\n' +
                "games=" + games + '\n' +
                "partner='" + partner + '\'' + '\n' +
                "info=" + info + '\n' +
                '}';
    }
}
