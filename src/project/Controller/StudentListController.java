package project.Controller;

/**
 * Created by ����� on 28.03.2015.
 */
import java.beans.Statement;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.sun.java.util.jar.pack.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.DAO.StudentDAO;
import project.Model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class StudentListController  {
    @FXML
    private	TableView <Student> tableview;
    @FXML
    private	TableColumn<Student,String> firstName;
    @FXML
    private	TableColumn<Student,String> lastName;
    @FXML
    private Label labelName;
    @FXML
    private	Label labelSecondName;
    @FXML
    private	Label LabelGroup;
    @FXML
    private	Label LabelData;
    @FXML
    private	Label LabelDepartment;
    @FXML
    private Button button;
    @FXML
    private ImageView studentImage;
Student s;


    /*
     CREATE TABLE TEST(ID INT PRIMARY KEY,
    FIRSTNAME VARCHAR(30),
    LATSNAME VARCHAR(30),
    "GROUP" VARCHAR(5),
    DEPARTMENT VARCHAR(30),
    IMAGE VARCHAR(50),
);
    * */

    private   ObservableList<Student> Stud;
    final	static String photo="@../../resources/image/defoultPhoto.png" ;
    final	static String DATE_FORMAT_PATTERN = "yyyy.MM.dd.";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    NewStudentController studentEditDialogController;
    int id=0;
    //github.com/romahomyshyn/JavaFxMVC


    private static StudentDAO studentDAO = null;

    public static StudentDAO getStudentDAO(){
        if (studentDAO == null){
            studentDAO = new StudentDAO();
        }
        return studentDAO;
    }

    @FXML
    private void initialize() {
System.out.println("Program start");
        s=new Student();
       // Connection connection;
      //  java.sql.Statement statement;
      //  ResultSet resultSet;

      StudentDAO dao=getStudentDAO();
  /*      Student student=new Student("petro","Petrovenko","111","IFTKN",photo);
        student.setId(0);

        try {
            dao.addStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        Stud=FXCollections.observableArrayList();

        ;
        try {
            Stud.add(dao.getStudentById((long) 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }


/*      try {
            System.out.println("Open H2 database");
            Class.forName("org.h2.Driver");
            connection=DriverManager.getConnection("jdbc:h2:file:/D:\\Java - копия - копия (6)\\src\\resources\\DB\\BDH2Hib", "sa", "");
            statement=connection.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM STUDENTS");
            while (resultSet.next()){
                Student student = new Student();
                student.setFirstName(resultSet.getString("FIRSTNAME"));
                student.setLastName(resultSet.getString("LASTNAME"));
                student.setGroup(resultSet.getString("GROUP"));
                student.setDepartment(resultSet.getString("DEPARTMENT"));
                student.setDate(resultSet.getDate("DATE"));
                student.setImageLocation(photo);
                Stud.add(student);
                id ++;


            }
            System.out.println("Close H2 database");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

    /*    try {
          prototipAdd();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

        tableview.setItems(Stud);


        firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        tableview.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentInformation(newValue));

        showStudentInformation(Stud.get(0));
    }


@FXML
 public void AddClick() throws IOException, ClassNotFoundException, SQLException {
    System.out.println("Press Button Add");

    Student student = new Student();
    if (showStudentDialog(student,"New student")) {
        Stud.add(student);
        studentDAO.addStudent(student);
    }
    student=studentEditDialogController.student;

  /*  Class.forName("org.h2.Driver");
    Connection connection;
    java.sql.Statement statement;

    connection=DriverManager.getConnection("jdbc:h2:file://c:\\StudentList", "Serhii", "");
    statement=connection.createStatement();
    System.out.println("Open H2 database");

    String sql = "INSERT INTO STUDENTS VALUES ("+id+",'" +
            student.getName().get()+"','"+student.getSecondName().get()+"','"+student.getGroup().get()+"','"+
            student.getDepartment().get()+"','"+DATE_FORMAT.format(student.getDate())+"')";
    statement.executeUpdate(sql);
    id++;
    connection.close();
    System.out.println("Close H2 database");
    */


    showStudentInformation(student);


}



    @FXML
    public void EditClick() throws IOException {
        System.out.println("Press Button Edit");
        int selectedItemIndex = tableview.getSelectionModel().getSelectedIndex();
        if (selectedItemIndex >= 0) {
            Student student =  tableview.getItems().get(selectedItemIndex);
            boolean okClicked = showStudentDialog(student, "Edit Student");
            if (okClicked) {
                showStudentInformation(student);
                s=student;
                Stud.add(student);
                try {
                    studentDAO.updateStudent(student);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    public void DeleteClick() throws IOException {
        System.out.println("Press Button Delete");
        int selectedItemIndex =tableview.getSelectionModel().getSelectedIndex();
        if (selectedItemIndex >= 0) {
            if (showAlert())
            tableview.getItems().remove(selectedItemIndex);
        }
        if(Stud.size()==0)
        {
            labelName.setText("");
            labelSecondName.setText("");
            LabelDepartment.setText("");
            LabelGroup.setText("");
            LabelData.setText("");
            studentImage.setImage(Image.impl_fromPlatformImage(""));
        }
    }

    private boolean showStudentDialog(Student student, String title) throws IOException {
        System.out.println("functoin showStudentDialog");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/View/NewStudent.fxml"));
        AnchorPane page = null;
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initOwner(tableview.getScene().getWindow());
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        studentEditDialogController = loader.getController();
        studentEditDialogController.setStudent(student);
        studentEditDialogController.setDialogStage(dialogStage);
        dialogStage.showAndWait();
        return studentEditDialogController.isOkClicked();

    }



    //@FXML
    public void showStudentInformation(Student student){
        System.out.println("functoin showStudentInformation");
        if (student!=null)
        {
            labelName.setText(student.getFirstName());
            labelSecondName.setText(student.getLastName());
            LabelDepartment.setText(student.getDepartment());
            LabelGroup.setText(student.getGroup());
            LabelData.setText(DATE_FORMAT.format(new Date()));
            Image n=new Image(student.getImageLocation());
            studentImage.setImage(n);

        }

    }


  /*  void prototipAdd() throws ClassNotFoundException, SQLException {
       Class.forName("org.h2.Driver");
        Connection connection;
        java.sql.Statement statement;

        connection=DriverManager.getConnection("jdbc:h2:file://c:\\StudentList", "Serhii", "");
        statement=connection.createStatement();


        Student student=new Student("petro","Petrovenko","111","IFTKN",new Date(),photo);
       Stud.add(student);
        String sql = "INSERT INTO STUDENTS VALUES ("+id+",'" +
                student.getName().get()+"','"+student.getSecondName().get()+"','"+student.getGroup().get()+"','"+
                student.getDepartment().get()+"','"+DATE_FORMAT.format(student.getDate())+"')";
        statement.executeUpdate(sql);
        id++;
      connection.close();
    }*/



    boolean showAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setContentText("Do you want to remove entry");
        ButtonType Yes = new ButtonType("Yes");
        alert.getButtonTypes().setAll(Yes);
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() ==Yes)
            return true;
        return false;
    }


    @FXML
    public void SentClick(){
        System.out.println("Press button Sent");

        int serverPort=1115;
        String address="127.0.0.1";


        TextInputDialog dialog = new TextInputDialog("192.168.1.100");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your ip:");


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            address= result.get();

        int selectedItemIndex =tableview.getSelectionModel().getSelectedIndex();
            if (selectedItemIndex >= 0) {
                Student student =  tableview.getItems().get(selectedItemIndex);
                try {
            InetAddress ipAddress=InetAddress.getByName(address);
            Socket socket=new Socket(ipAddress,serverPort);

                    InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();

            DataInputStream inputStream1= new DataInputStream(inputStream);
            DataOutputStream outputStream1=new DataOutputStream(outputStream);



            outputStream1.writeUTF(student.toString());


           outputStream1.flush();
          String serverSent= inputStream1.readUTF();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        }
    }

}
