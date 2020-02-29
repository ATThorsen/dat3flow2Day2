/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class PersonDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private long id;

    public long getId() {
        return id;
    }

//    public PersonDTO(String firstName, String lastName, String phone) {
//        this.firstName = firstName;
    public void setId(long id) {
        this.id = id;
    }

//        this.lastName = lastName;
//        this.phone = phone;
//        
//    }
    public PersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public PersonDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
