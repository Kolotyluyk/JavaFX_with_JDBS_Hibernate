package project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ����� on 28.03.2015.
 */
public class NewStudentController {
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
    static final String GROUP= "^([0-9]|[0-9A-z])+$";

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
            student.setName(TextFieldName.getText());
            student.setSecondName(TextFieldLastName.getText());
            student.setGroup(TextFieldGroup.getText());
            student.setDepartment(ComboBoxDepartment.getValue());
            student.setData(DATE_FORMAT.parse(TextFieldDate.getText()));
            student.setImageLocation(photo);
            okClicked = true;
            dialogStage.close();
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        TextFieldName.setText(student.getName().get());
        TextFieldLastName.setText(student.getSecondName().get());
        TextFieldGroup.setText(student.getGroup().get());
        ComboBoxDepartment.setValue(student.getDepartment().get());
        if (student.getDate() != null) {
            TextFieldDate.setText(DATE_FORMAT.format(student.getDate()));
        }
        imageLocation = student.getImageLocation().get();
        /*  if (imageLocation == null) {
            imageLocation = Images.STUDENT_DEFAULT_IMAGE.getLocation();
        }
        studentImageView.setImage(new Image(imageLocation));*/
    }


    public boolean isOkClicked() {
        return okClicked;
    }


    private boolean Valid() {

        if (TextFieldName.getText() == "" || !TextFieldName.getText().trim().matches(NAME)) return false;
        if ( TextFieldLastName.getText() == "" || ! TextFieldLastName.getText().trim().matches(NAME)) return false;
        if (TextFieldGroup.getText() == "" || !TextFieldGroup.getText().trim().matches(GROUP)) return false;

        return true;

    }
}
