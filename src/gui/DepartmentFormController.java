package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entity;

	private DepartmentService service;
	
	private List<DataChangeListener> dataChangeListener = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setDepartment(Department entity) {

		this.entity = entity;

	}

	public void setDepartmentService(DepartmentService service) {

		this.service = service;

	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		
		dataChangeListener.add(listener);
		
	}

	public void updateFormData() {

		if (entity == null) {

			throw new IllegalStateException("Entity was null");

		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

	@FXML
	private void onbtSaveAction(ActionEvent event) {
		if (entity == null) {

			throw new IllegalStateException("Entity was null");

		}
		
		if (service == null) {

			throw new IllegalStateException("Service was null");

		}
		try {
			
			entity = getFormDate();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
			
		} catch (DbException e) {
			Alerts.showAlert("Error Saving Project", null, e.getMessage(), AlertType.ERROR);
		}
		
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListener) {
			listener.onDataChanged();
			
		}
		
	}

	private Department getFormDate() {

		Department obj = new Department();

		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());

		return obj;
	}

	@FXML
	private void onbtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {

		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtId, 30);
		;

		// TODO Auto-generated method stub

	}

}
