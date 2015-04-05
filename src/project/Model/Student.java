package project.Model;

/**
 * Created by Сергій on 28.03.2015.
 */
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;
import javafx.beans.property.StringProperty;



public class Student {
    private final  StringProperty Name;
    private final  StringProperty SecondName;
    final private StringProperty Group;
    final private StringProperty Department;
    private Date date;
    final private StringProperty ImageLocatoin;



    public Student (){
        this.Name=new SimpleStringProperty("");
        this.SecondName=new SimpleStringProperty("");
        this.Group=new SimpleStringProperty("");
        this.Department=new SimpleStringProperty("");
        this.date=new Date();
        this.ImageLocatoin=new SimpleStringProperty("");

    }

    public Student (String Name,String SecondName,String Group,String Department,Date date,String ImageLocation){
        this.Name=new SimpleStringProperty(Name);
        this.SecondName=new SimpleStringProperty(SecondName);
        this.Group=new SimpleStringProperty(Group);
        this.Department=new SimpleStringProperty(Department);
        this.date=date;
        this.ImageLocatoin=new SimpleStringProperty(ImageLocation);

    }


    public void setName(String a){
       Name.set(a);
    }
    public void setSecondName(String SecondName){
        this.SecondName.set(SecondName);
    }

    public void setGroup(String a){
        Group.set(a);
    }
    public void setDepartment(String a)
    {
        Department.set(a);
    }
    public void setData(Date a){
        date=a;
    }
    public void setImageLocation(String a){
        ImageLocatoin.set(a);
    }



    public StringProperty getName(){
        return Name;
    }
    public StringProperty getSecondName(){
        return SecondName;
    }
    public StringProperty getGroup(){
        return Group;
    }
    public StringProperty getDepartment(){return Department;}
    public Date getDate(){return date;   }
    public StringProperty getImageLocation(){return ImageLocatoin;}
    public StringProperty getImageLocatoin(){
        return ImageLocatoin;
    }

}

