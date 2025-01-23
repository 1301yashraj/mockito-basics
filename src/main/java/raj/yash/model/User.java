package raj.yash.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "CustomerID")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
    @Column(name = "ContactName")
    private String fullName;
    @Column(name = "CompanyName")
    private String company;
    private String city;
    @Column(name = "ContactTitle")
    private String title;
    private String postalCode;


    // public static  int count = 0;
    // after loading from DB RUN THIS
    @PostLoad
    public void afterLoad() {
//        System.out.println("?>>>>>>after loading from DB RUN THIS  "+(++count));
        if (this.fullName != null) {
            String[] parts = this.fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = (parts.length > 1) ? parts[1] : "";
//            System.out.println("FirstName >>> " + this.getFirstName());
//            System.out.println("LastName >>> " + this.getLastName());
        }
    }

    // Custom getter for firstName
    // public String getFirstName() {
    // if (firstName == null && fullName != null) {
    // String[] parts = fullName.split(" ", 2);
    // firstName = parts[0];
    // }
    // return firstName;
    // }

    // Custom getter for lastName
    // public String getLastName() {
    // if (lastName == null && fullName != null) {
    // String[] parts = fullName.split(" ", 2);
    // lastName = parts.length > 1 ? parts[1] : "";
    // }
    // return lastName;
    // }
}
