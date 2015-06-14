package project.Controller;
//аиспаспаспа
//пасачавчавч
//спаспсп
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.Model.Student;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ����� on 28.03.2015.
 */
public class NewStudentController {
    @FXML
    private ImageView Photo;

    @FXML
    private  TextField TextFieldName;
    @FXML
    private  TextField TextFieldLastName;
    @FXML
    private TextField TextFieldGroup;
    @FXML
    private  TextField TextFieldDate;
    @FXML
    private ComboBox<String>   ComboBoxDepartment;
    private ObservableList<String> Depart;
    public Student student;
    final	static String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    final	static String photo="@../../resources/image/defoultPhoto.png" ;
    private boolean okClicked;

    private Stage dialogStage;
    private String imageLocation;
    static final String NAME = "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,29}+$";
  //  static final String GROUP= "^([0-9]{1,3}";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    @FXML
    private void initialize() {
        Depart= FXCollections.observableArrayList();
        Depart.addAll("IFTKN","foreign languages","economic");
        ComboBoxDepartment.setItems(Depart);


    }

    public void CancelClick()
    {
        dialogStage.close();
    }


    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }


    public void Okclick() throws ParseException {
        if (Valid())
        {
            student.setFirstName(TextFieldName.getText());
            student.setLastName(TextFieldLastName.getText());
            student.setGroup(TextFieldGroup.getText());
            student.setDepartment(ComboBoxDepartment.getValue());

            student.setImageLocation(photo);
            okClicked = true;
            dialogStage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Field empty");
            alert.showAndWait();
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        TextFieldName.setText(student.getFirstName());
        TextFieldLastName.setText(student.getLastName());
        TextFieldGroup.setText(student.getGroup());
        ComboBoxDepartment.setValue(student.getDepartment());
            TextFieldDate.setText(DATE_FORMAT.format(new Date()));

        imageLocation = student.getImageLocation();

    }


    public boolean isOkClicked() {
        return okClicked;
    }


    private boolean Valid() {

        if (TextFieldName.getText() == "" || !TextFieldName.getText().trim().matches(NAME)) return false;
        if ( TextFieldLastName.getText() == "" || ! TextFieldLastName.getText().trim().matches(NAME)) return false;
//        if (TextFieldGroup.getText() == "" || !TextFieldGroup.getText().trim().matches(GROUP)) return false;

        return true;

    }
@FXML
    void ImageClick(){
        FileChooser fileChooser = new FileChooser();
    File studentImageFile = fileChooser.showOpenDialog(dialogStage);
    if (studentImageFile != null) {
        imageLocation = studentImageFile.toURI().toString();
        Image studentImage = new Image(imageLocation);
        Photo.setImage(studentImage);
        student.setImageLocation(imageLocation);
    }

    }

}


