package project.Controller;

/**
 * Created by Сергій on 28.03.2015.
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Model.Student;

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




    private   ObservableList<Student> Stud;
    final	static String photo="@../../resources/image/defoultPhoto.png" ;
    final	static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    NewStudentController studentEditDialogController;
    //Label ; //github.com/romahomyshyn/JavaFxMVC

    @FXML
    private void initialize() {
        Stud=FXCollections.observableArrayList();
        prototipAdd();
        tableview.setItems(Stud);


        firstName.setCellValueFactory(
                cellData -> cellData.getValue().getName());


        lastName.setCellValueFactory(
                cellData -> cellData.getValue().getSecondName());

        tableview.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentInformation(newValue));

        showStudentInformation(Stud.get(0));
    }


@FXML
 public void AddClick() throws IOException {
    Student student = new Student();
    if (showStudentDialog(student,"New student"))
            Stud.add(student);
    student=studentEditDialogController.student;
    showStudentInformation(student);
}

    @FXML
    public void EditClick() throws IOException {
        int selectedItemIndex = tableview.getSelectionModel().getSelectedIndex();
        if (selectedItemIndex >= 0) {
            Student student =  tableview.getItems().get(selectedItemIndex);
            boolean okClicked = showStudentDialog(student, "Edit Student");
            if (okClicked) {
                showStudentInformation(student);
            }
        }
    }


    @FXML
    public void DeleteClick() throws IOException {
        int selectedItemIndex =tableview.getSelectionModel().getSelectedIndex();
        if (selectedItemIndex >= 0) {
            if (showAlert())
            tableview.getItems().remove(selectedItemIndex);
        }
    }

    private boolean showStudentDialog(Student student, String title) throws IOException {
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
        if (student!=null)
        {
            labelName.setText(student.getName().get());
            labelSecondName.setText(student.getSecondName().get());
            LabelDepartment.setText(student.getDepartment().get());
            LabelGroup.setText(student.getGroup().get());
            LabelData.setText(DATE_FORMAT.format(student.getDate()));
            studentImage.setImage(new Image(student.getImageLocatoin().get()));

        }

    }


    void prototipAdd(){
        Stud.add(new Student("petro","Petrovenko","111","IFTKN",new Date(),photo));
        Stud.add(new Student("Ivan","Ivanow","222","economic",new Date(),photo));
        Stud.add(new Student("Вася","Іванов","441","Хімічний",new Date(),photo));


    }


    boolean showAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Попереджувальне вікно");
        alert.setContentText("Do you want to remove entry");
        ButtonType Yes = new ButtonType("Yes");
        alert.getButtonTypes().setAll(Yes);
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() ==Yes)
            return true;
        return false;



    }

}
