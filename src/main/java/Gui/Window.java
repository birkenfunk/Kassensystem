package Gui;

import User.Rechte;
import User.UserManager;

/*import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.security.ssl.Debug;
*/
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @author Vitus Henkel
 */
public class Window {

	static UserManager userManager;

/*	public static void startGUI(Stage primaryStage) throws Exception {
		//Erstellen des Fensters
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//Erstellen einer leeren Scene
		Scene Scene = new Scene(grid, grid.getWidth(), grid.getHeight());
		Scene.getStylesheets().add("Styles.css");

		//Die primaryStage wird für die erste Scene angepasst
		primaryStage.setTitle("Hauptmenü");
		primaryStage.setScene(SceneLogin(primaryStage, grid, Scene));
		primaryStage.setFullScreen(true);
		primaryStage.show();

		userManager = UserManager.getUserManager();
	}

	/**
	 * Erstellt die Scene, für eine Stage
	 *
	 * @param primaryStage wird benötigt zum beenden des Fensters
	 * @return Gibt die ertellte Scene zurück
	 */
/*	public static Scene SceneLogin(Stage primaryStage, GridPane grid, Scene Scene) {
		Text sceneTitle = new Text("Wilkommen im Login");
		GridPane.setConstraints(sceneTitle, 0, 0);

		Label userName = new Label("Benutzter:");
		GridPane.setConstraints(userName, 0, 1);

		TextField userTextfield = new TextField();
		userTextfield.setPromptText("Benutzername");
		GridPane.setConstraints(userTextfield, 1, 1);

		Label pw = new Label("Passwort:");
		GridPane.setConstraints(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		pwBox.setPromptText("Password");
		GridPane.setConstraints(pwBox, 1, 2);

		Button btn_Login = new Button("Login");
		btn_Login.getStyleClass().add("button-green");
		GridPane.setConstraints(btn_Login, 1, 4);

		Button btn_exit = new Button("Exit");
		btn_exit.getStyleClass().add("button-red");
		GridPane.setConstraints(btn_exit, 0, 4);

		Label falschespsw = new Label("Benutzername oder Passwort falsch");
		falschespsw.setVisible(false);
		falschespsw.getStyleClass().add("label-red");
		GridPane.setConstraints(falschespsw, 0, 5);

		btn_exit.setOnAction(event -> {
			primaryStage.close();
		});

		btn_Login.setOnAction(e -> {
			UserManager.getUserManager();                                                //init des UserManagers

			if (userManager.Login(userTextfield.getText(), pwBox.getText()) == Rechte.SUPER_ADMIN) {
				grid.getChildren().clear();
				primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
				primaryStage.setTitle("Accountverwaltung");
			} else {
				falschespsw.setVisible(true);
			}
		});

		grid.getChildren().addAll(
				sceneTitle,
				userName, userTextfield,
				pw, pwBox,
				btn_Login, btn_exit,
				falschespsw
		);

		return Scene;
	}
	/**
	 * Das Hauptmenü
	 *
	 * @param primaryStage
	 * @return Gibt die erstellte Scene zurück
	 */
/*	private static Scene SceneMainMenue(Stage primaryStage, GridPane grid, Scene Scene) {

		Text scenetitle = new Text("Hauptmenü");
		GridPane.setConstraints(scenetitle, 0, 0);

		Button btn_sell = new Button("Verkauf");
		GridPane.setConstraints(btn_sell, 0, 1);

		Button btn_receipt = new Button("Rechnungen");
		GridPane.setConstraints(btn_receipt, 0, 2);

		Button btn_warehouse = new Button("Lagerverwaltung");
		GridPane.setConstraints(btn_warehouse, 0, 3);

		Button btn_orders = new Button("Bestellungen");
		GridPane.setConstraints(btn_orders, 0, 4);

		Button btn_accounts = new Button("Accountverwaltung");
		GridPane.setConstraints(btn_accounts, 0, 5);

		Button btn_logOut = new Button("Log Out");
		btn_logOut.getStyleClass().add("button-red");
		GridPane.setConstraints(btn_logOut, 0, 6);

		btn_sell.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneSell(primaryStage, grid, Scene));
			primaryStage.setTitle("Accountverwaltung");
		});

		btn_receipt.setOnAction(e -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneBill(primaryStage, grid, Scene));
			primaryStage.setTitle("Rechnungen");
		});

		btn_accounts.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneAccountManagment(primaryStage, grid, Scene));
			primaryStage.setTitle("Accountverwaltung");
		});

		btn_logOut.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneLogin(primaryStage, grid, Scene));
			primaryStage.setTitle("Log In");
		});

		grid.getChildren().addAll(
				scenetitle,
				btn_sell,
				btn_receipt,
				btn_warehouse,
				btn_orders,
				btn_accounts,
				btn_logOut
		);

		return Scene;
	}

	private static Scene SceneSell(Stage primaryStage, GridPane grid, Scene Scene) {
		Text scenetitle = new Text("Verkauf");
		GridPane.setConstraints(scenetitle, 0, 0);

		Button btn_Kachel1 = new Button("K1");
		btn_Kachel1.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel1, 0, 1);

		Button btn_Kachel2 = new Button("K2");
		btn_Kachel2.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel2, 1, 1);

		Button btn_Kachel3 = new Button("K3");
		btn_Kachel3.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel3, 2, 1);

		Button btn_Kachel4 = new Button("K4");
		btn_Kachel4.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel4, 0, 2);

		Button btn_Kachel5 = new Button("K5");
		btn_Kachel5.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel5, 1, 2);

		Button btn_Kachel6 = new Button("K6");
		btn_Kachel6.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel6, 2, 2);

		Button btn_Kachel7 = new Button("K7");
		btn_Kachel7.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel7, 0, 3);

		Button btn_Kachel8 = new Button("K8");
		btn_Kachel8.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel8, 1, 3);

		Button btn_Kachel9 = new Button("K9");
		btn_Kachel9.getStyleClass().add("kachel-blue");
		GridPane.setConstraints(btn_Kachel9, 2, 3);

		Button btn_back = new Button("Zurück");
		btn_back.getStyleClass().add("button-red-470");
		GridPane.setConstraints(btn_back, 0, 4, 3, 1);

		btn_back.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
			primaryStage.setTitle("Hauptmenü");
		});

		grid.getChildren().addAll(
				scenetitle,
				btn_Kachel1, btn_Kachel2, btn_Kachel3,
				btn_Kachel4, btn_Kachel5, btn_Kachel6,
				btn_Kachel7, btn_Kachel8, btn_Kachel9,
				btn_back
		);

		return Scene;
	}

	private static Scene SceneBill(Stage primaryStage, GridPane grid, Scene Scene) {
		Text scenetitle = new Text("Rechnung");
		GridPane.setConstraints(scenetitle, 0, 0);

		//btn_Kachel1.getStyleClass().add("kachel-blue");

		Button btn_back = new Button("Zurück");
		btn_back.getStyleClass().add("button-red-470");
		GridPane.setConstraints(btn_back, 0, 4, 3, 1);

		btn_back.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
			primaryStage.setTitle("Hauptmenü");
		});

		grid.getChildren().addAll(
				scenetitle,
				btn_back
		);

		return Scene;
	}

	private static Scene SceneAccountManagment(Stage primaryStage, GridPane grid, Scene Scene) {
		Text scenetitle = new Text("Accountverwaltung");
		GridPane.setConstraints(scenetitle, 0, 0);

		Button btn_createAccount = new Button("Account anlegen");
		GridPane.setConstraints(btn_createAccount, 0, 1);

		Button btn_changeAccount = new Button("Account ändern");
		GridPane.setConstraints(btn_changeAccount, 0, 2);

		Button btn_back = new Button("Zurück");
		btn_back.getStyleClass().add("button-red");
		GridPane.setConstraints(btn_back, 0, 3);

		btn_createAccount.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneAccountCreate(primaryStage, grid, Scene));
			primaryStage.setTitle("Account-Verwaltung");
		});

		btn_changeAccount.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneAccountChange(primaryStage, grid, Scene));
			primaryStage.setTitle("Account-Verwaltung");
		});

		btn_back.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
			primaryStage.setTitle("Hauptmenü");
		});

		grid.getChildren().addAll(
				scenetitle,
				btn_createAccount,
				btn_changeAccount,
				btn_back
		);

		return Scene;
	}

	private static Scene SceneAccountCreate(Stage primaryStage, GridPane grid, Scene Scene) {
		Text scenetitle = new Text("Account anlegen");
		GridPane.setConstraints(scenetitle, 0, 0);

		Label userName = new Label("Benutzter:");
		GridPane.setConstraints(userName, 0, 1);

		TextField userTextfield = new TextField();
		GridPane.setConstraints(userTextfield, 1, 1);

		Label pw = new Label("Passwort:");
		GridPane.setConstraints(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		GridPane.setConstraints(pwBox, 1, 2);

		Button btn_back = new Button("Zurück");
		btn_back.getStyleClass().add("button-red");
		GridPane.setConstraints(btn_back, 0, 5);

		Button btn_AccAnlegen = new Button("Account anlegen");
		btn_AccAnlegen.getStyleClass().add("button-green");
		GridPane.setConstraints(btn_AccAnlegen, 1, 5);

		btn_back.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneAccountManagment(primaryStage, grid, Scene));
			primaryStage.setTitle("Accountverwaltung");
		});

		btn_AccAnlegen.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
			primaryStage.setTitle("Hauptmenü");
		});

		grid.getChildren().addAll(
				scenetitle,
				userName, userTextfield,
				pw, pwBox,
				btn_back, btn_AccAnlegen
		);

		return Scene;
	}

	private static Scene SceneAccountChange(Stage primaryStage, GridPane grid, Scene Scene) {
		Text scenetitle = new Text("Account ändern");
		GridPane.setConstraints(scenetitle, 0, 0);

		Label userName = new Label("Benutzter:");
		GridPane.setConstraints(userName, 0, 1);

		TextField userTextfield = new TextField();
		GridPane.setConstraints(userTextfield, 1, 1);

		Label pw = new Label("Passwort:");
		GridPane.setConstraints(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		GridPane.setConstraints(pwBox, 1, 2);

		Button btn_AccSearch = new Button("Suchen");
		btn_AccSearch.getStyleClass().add("button-search");
		GridPane.setConstraints(btn_AccSearch, 2, 1);

		CheckBox cb_Verkaeufer = new CheckBox("Verkäufer");
		GridPane.setConstraints(cb_Verkaeufer, 0, 3);

		CheckBox cb_Einkaeufer = new CheckBox("Einkäufer");
		GridPane.setConstraints(cb_Einkaeufer, 0, 4);

		CheckBox cb_Administrator = new CheckBox("Administrator");
		GridPane.setConstraints(cb_Administrator, 0, 5);

		Button btn_back = new Button("Zurück");
		btn_back.getStyleClass().add("button-red");
		GridPane.setConstraints(btn_back, 0, 6);

		Button btn_AccChange = new Button("Account ändern");
		btn_AccChange.getStyleClass().add("button-green");
		GridPane.setConstraints(btn_AccChange, 1, 6);

		btn_back.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneAccountManagment(primaryStage, grid, Scene));
			primaryStage.setTitle("Accountverwaltung");
		});

		btn_AccChange.setOnAction(event -> {
			grid.getChildren().clear();
			primaryStage.setScene(SceneMainMenue(primaryStage, grid, Scene));
			primaryStage.setTitle("Hauptmenü");
		});

		grid.getChildren().addAll(
				scenetitle,
				userName, userTextfield, btn_AccSearch,
				pw, pwBox,
				cb_Verkaeufer,
				cb_Einkaeufer,
				cb_Administrator,
				btn_back, btn_AccChange
		);

		return Scene;
	}
*/
}