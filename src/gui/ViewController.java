package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import entity.status;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewController implements Initializable{
	@FXML
	private ComboBox<status> woperation;
	@FXML
	private ObservableList<status> oplist;
	@FXML
	private TextField txtNumberone;
	@FXML
	private TextField txtNumbertwo;
	@FXML
	private Label txtResult;
	@FXML
	private Button bt;
	@FXML
	public void on_bt_action(){
		try {
			status Status = woperation.getSelectionModel().getSelectedItem();
			double num1, num2 ;
			num1 = Double.parseDouble(txtNumberone.getText()); 
			num2 =	Double.parseDouble(txtNumbertwo.getText());
			Double result= 0.0;
			if (Status == status.valueOf("Sum")) {
				result = num1 + num2;
			}else if (Status == status.valueOf("Multiplication")){
				result = num1 * num2;
			}else if (Status == status.valueOf("Subctract")){
				result = num1 - num2;
			}else if (Status == status.valueOf("Division")){
				result = num1 / num2;
			};
			
			txtResult.setText(String.format("%.2f",result));
			
		} catch(NumberFormatException e) {
			Locale.setDefault(Locale.US);
			Alerts.showAlert("Erro", "Erro de conversão", "Não foi possível converter o valor para número", Alert.AlertType.ERROR);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//cria uma regra para o usuário digitar apenas números válidos
		Constraints.setTextFieldDouble(txtNumberone);
		Constraints.setTextFieldDouble(txtNumbertwo);
		
		//cria lista de valores para combobox
		List<status> list = new ArrayList<>(); 
		
		list.add(status.valueOf("Multiplication"));
		list.add(status.valueOf("Division"));
		list.add(status.valueOf("Subctract"));
		list.add(status.valueOf("Sum"));
		
		oplist = FXCollections.observableArrayList(list);
		woperation.setItems(oplist);
	};

}
