package Controllers;
import Model.TableViewObjects;
import Model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminTeacherSettings implements Initializable {
    @FXML
    private TableView<TableViewObjects> teacherTableView,teacherRequestsTableView;
    @FXML
    private TableColumn<TableViewObjects,String> name,teacherName,teacherCompetitions,teacherContests;
    @FXML
    private Button approveButton,backButton;

    ObservableList<TableViewObjects> requests = FXCollections.observableArrayList();
    ObservableList<TableViewObjects> teachers = FXCollections.observableArrayList();



    public void approveRequest(MouseEvent mouseEvent) throws IOException {
//        teacherRequestsTableView.getSelectionModel().getSele
//            Teacher teacher = new Teacher(teachers.size() + 1,tea,username.getText(),hashedPassword);
//            teacher.serialize();
        Scanner scanner = new Scanner(new File("src/data/requests/teachers.txt"));
        Scanner idScanner = new Scanner(new File("src/data/users/teachers.txt"));
        int id=1;
        while(idScanner.hasNextLine()){
            id++;
            idScanner.nextLine();
        }
        int lineCount = 0;
        String lineToDelete;
        while (scanner.hasNextLine()){
            if (lineCount == teacherRequestsTableView.getSelectionModel().getSelectedCells().get(0).getRow()){
                String line = scanner.nextLine();
                lineToDelete=line;
                Teacher teacher = new Teacher(String.valueOf(id),line.split("\\s")[1],line.split("\\s")[2]+ " " +line.split("\\s")[3],line.split("\\s")[4]);
                teacher.serializeTeacher();
                deleteRequest(lineToDelete);

                break;

            }
            scanner.nextLine();
            lineCount++;
        }
        refresh();

    }

    public void toAdminMainMenu(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        name.setCellValueFactory(new PropertyValueFactory<>("teacherRequestName"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewName"));
        teacherCompetitions.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewCompetitionNumber"));
        teacherContests.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewContestNumber"));
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
        if (teacherRequestsTableView.getSelectionModel().isEmpty()){
            approveButton.setDisable(true);
        }
        teacherRequestsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                approveButton.setDisable(false);
            }
            else{
                approveButton.setDisable(true);
            }
        });
    }





    public void getData(){
        Scanner requestScanner=null;
        Scanner teacherScanner=null;
        Scanner resultsScanner = null;

        try {
            requestScanner = new Scanner(new File("src/data/requests/teachers.txt"));
            teacherScanner = new Scanner(new File("src/data/users/teachers.txt"));
            resultsScanner = new Scanner(new File("src/data/attributes/results.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(requestScanner.hasNextLine()){
            String line = requestScanner.nextLine();
            if (requests.isEmpty() || requests==null){
                requests.add(new TableViewObjects((line.split("\\s")[1]+" "+line.split("\\s")[2])));
            }
            else{
                String firstName = line.split("\\s")[1];
                String lastName = line.split("\\s")[2];
                String name = firstName+" "+lastName;
                for (int i=0;i<requests.size();i++){
                    if (requests.get(i).getTeacherRequestName().equals(name)){
                    }
                    else{
                        requests.add(new TableViewObjects((line.split("\\s")[1])+" "+line.split("\\s")[2]));
                        break;
                    }
                }
            }
        }
        while(teacherScanner.hasNextLine()){
            String line = teacherScanner.nextLine();
            if (teachers.isEmpty() || teachers!=null){
                teachers.add(new TableViewObjects(line.split("\\s")[0],(line.split("\\s")[1]+" "+line.split("\\s")[2]),
                        0,0));
            }
            else{
                for (int i=0;i<teachers.size();i++){
                    if (!teachers.get(i).getTeacherTableViewId().equals((line.split("\\s")[0]))){
                        teachers.add(new TableViewObjects(line.split("\\s")[0],(line.split("\\s")[1]+" "+line.split("\\s")[2]),0,0));
                    }
                    else{
                    }
                }
            }
        }
        while(resultsScanner.hasNextLine()){
            String line = resultsScanner.nextLine();
            for (int i=0;i<teachers.size();i++){
                String tempTeacherID = line.split("\\s")[4];
                if (teachers.get(i).getTeacherTableViewId().equals(tempTeacherID)){
                    String typeTemp = line.split("\\s")[5];
                    if (Integer.valueOf(typeTemp)==0){
                        teachers.get(i).incrementCompetition();
                    }
                    else if(Integer.valueOf(typeTemp)==1){
                        teachers.get(i).incrementContest();
                    }
                }
            }
        }

    }
    public void refresh(){
        teachers.clear();
        requests.clear();

        getData();
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
    }
    public void deleteRequest(String lineToDelete) throws IOException {
        File file = new File("src/data/requests/teachers.txt");
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        String line;
        while ((line = br.readLine()) != null){
            if (!line.equals(lineToDelete)){
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();

        if (!file.delete()){
            System.out.println("Could not delete file");
        }
        if(!tempFile.renameTo(file)){
            System.out.println("Could not rename file");
        }



    }
}
