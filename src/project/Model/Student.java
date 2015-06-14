package project.Model;

/**
 * Created by Сергій on 28.03.2015.
 */
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name= "STUDENTS")
public class Student {
    @Id
    @Column(name="ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name= "FIRSTNAME")
    private String firstName;

    @Column(name= "LASTNAME")
    private String lastName;

    @Column(name= "GROUP")
    private String group;

    @Column(name= "DEPARTMENT")
    private String department;

    @Column(name= "IMAGE")
    private String imageLocation;



    public Student(){ }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public String getDepartment() {
        return department;
    }



    public String getImageLocation() {
        return imageLocation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

public void setId(int id){
    this.id=id;
}


    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Integer getId() {
        return id;
    }

    public Student( String firstName, String lastName, String group, String department,  String imageLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.department = department;

        this.imageLocation = imageLocation;

    }



    @Override
    public String toString(){
        return "Student{"+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNumber='" + group + '\'' +
                ", departmentName='" + department + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }




}
