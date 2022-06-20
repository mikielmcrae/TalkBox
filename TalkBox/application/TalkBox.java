package application;

/*Mikiel McRae*/

//Standard imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;

//Layout imports
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

//Component imports
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

//Styling and alignment
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

//File imports for photo uploading
import java.io.File;
import javafx.stage.FileChooser;

//Support exit program
import javafx.application.Platform;



public class TalkBox extends Application {

	//Variables with class scope:

	//	Top of BorderPane: MenuBar, Menus, and MenuItems
	MenuBar mbMain;
	Menu mnuTalkBox, mnuFile, mnuHelp;
	MenuItem mitemAbout, mitemContact, mitemLogout;

	//	left VBoxes: TextField to update status, Button, and Label
	Label lblUpdateStatus, lblUserStatus;
	TextField txtfStatus;
	Button btnStatus, btnClearStatus;

	//	right VBoxes: Image, edit Buttons, and Text
	Image imgProfile;
	ImageView imgView;
	Button btnChangePhoto, btnEditProfile;
	Label lblName, lblCity;



	//Constructor
	public TalkBox() {
		//MenuBar 
		mbMain = new MenuBar();	
		//MenuBar components:

		//1. TalkBox menu
		/* users can read about this version of TalkBox*/	
		mnuTalkBox = new Menu("TalkBox");
		mitemAbout = new MenuItem("About TalkBox");


		//2. File menu
		//Logout option
		mnuFile = new Menu("File");
		mitemLogout = new MenuItem("Log out");

		//3. Help menu
		//Contact Support: users can submit a complaint
		mnuHelp = new Menu("Help");
		mitemContact = new MenuItem("Contact Support");

		//end MenuBar components instantiation

		//Components for Status Update portion of UI: Label, TextField, Button
		lblUpdateStatus = new Label("What's new?");
		txtfStatus = new TextField();
		btnStatus = new Button("Update Status");
		btnClearStatus = new Button("Clear Status");
		lblUserStatus = new Label("");

		//		Default text for status
		txtfStatus.setText("Today I feel...");

		/*Components for Profile side of UI:  Image, 
		 * Buttons, and Labels */

		//Set default profile picture 
		try {
			imgProfile = new Image("default-pic.jpg");	
			imgView = new ImageView(imgProfile);
			imgView.setFitWidth(120);
			//Preserve ratio
			imgView.setPreserveRatio(true);
		}
		catch(Exception e) {
			System.out.println("No default profile picture selected.");
			System.out.println(e.toString());
		}


		btnChangePhoto = new Button("Update Profile Picture");

		//Profile information
		btnEditProfile = new Button("Edit Profile");
		lblName = new Label("Mikiel McRae");
	
		lblCity = new Label("Dublin, IE");





	}//End constructor

	//Event handling
	public void init() {
		//		Menu methods
		//Quit app
		mitemLogout.setOnAction(ae -> logOut());

		//Open app "About" window
		mitemAbout.setOnAction(ae -> showAbout());

		//Contact Support
		mitemContact.setOnAction(ae -> contactSupport());

		//		Main component methods
		//Update status
		btnStatus.setOnAction(ae -> updateStatus());

		//Clear status
		btnClearStatus.setOnAction(ae-> clearStatus());

		//Update profile Image (remove or choose new)
		btnChangePhoto.setOnAction(ae -> updatePicture());

		//Edit profile
		btnEditProfile.setOnAction(ae -> editProfile());
	}//end init()

	//Start event methods:

	//Quit app
	public void logOut() {
		//	Confirmation type alert since user is being prompted for confirmation
		Alert alertLogout = new Alert(AlertType.CONFIRMATION);
		alertLogout.setTitle("Log Out");
		alertLogout.setHeaderText("Leaving TalkBox for now?");
		alertLogout.setContentText("Are you sure?");


		//Using getDialogPane() and lookupButton() to customise button names - 
		//Methods found on StackOverflow to customise Alert apperance and behaviour
		//https://stackoverflow.com/questions/37267011/javafx-change-alert-dialog-buttons-text
		Button btnBack = (Button)alertLogout.getDialogPane().lookupButton(ButtonType.CANCEL);
		btnBack.setText("No, take me back to TalkBox");

		Button btnLogout = (Button)alertLogout.getDialogPane().lookupButton(ButtonType.OK);
		btnLogout.setText("Yes, log me out");

		//		Make log out button a bit smaller than Return so it fits its text
		btnLogout.setMaxWidth(100);

		

		// Methods found in JavaFX Documentation:
		//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
		//If OK Button clicked, log user out
		//These methods dictate the Alert's response based on the user's interaction with a given Button.
		//In this case its ButtonType.OK, and if this button and the event are present, the following behaviour can
		//be customised (in this case, Platform.exit().)
		alertLogout.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response ->Platform.exit());

	}//End logOut()

	//Display app info
	public void showAbout() {

		//Create information Alert
		Alert alertAbout = new Alert(AlertType.INFORMATION);

		//Boilerplate app info
		alertAbout.setTitle("TalkBox v1.0");
		alertAbout.setHeaderText("Thank you for choosing TalkBox v1.0!");
		alertAbout.setContentText("Double M Studios\nDublin 8\n2022 TalkBox, Inc.\nAll rights reserved");

		//The following methods I learned about through StackOverflow to change Button text on an Alert:
		/*Create a new Button out of the default Button that comes with the showAndWait() method.
		 *Select that default button by looking up buttons within the Alert that have its ButtonType, 
		  then customise the new Button's text */
		//https://stackoverflow.com/questions/37267011/javafx-change-alert-dialog-buttons-text
		Button btnOk = (Button)alertAbout.getDialogPane().lookupButton(ButtonType.OK);
		btnOk.setText("Return to TalkBox");
		alertAbout.showAndWait();


	}//end showAbout()

	//Write to support team
	public void contactSupport() {

		//New Stage for the form GridPane
		Stage contactStage = new Stage();

		//Size to fit a TextArea for user query
		contactStage.setWidth(680);
		contactStage.setHeight(360);
		contactStage.setTitle("Contact Support");

		//Users cannot resize anything but the homepage
		contactStage.setResizable(false);

		//Create the GridPane for the contact form
		GridPane gpContact = new GridPane();
		//Padding and spacing
		gpContact.setPadding(new Insets(15));
		gpContact.setHgap(15);
		gpContact.setVgap(15);


		//Components to be added to contact form GridPane: Text, Labels, TextField, TextArea, Buttons
		Text txtContact = new Text("Experiencing an issue with TalkBox? "
				+ "Our Support Team will be happy to assist you.");
		Label lblName = new Label("*Your name:");
		TextField txtfName = new TextField();
		Label lblQuery = new Label("*Your query:");
		TextArea txtQuery = new TextArea();
		Button btnCancel = new Button("Cancel");
		Button btnSubmit = new Button("Submit");
		Label lblRequired = new Label("(*Required)");


		//Sizing and alignment

		//		Minimum width for full label text to show
		lblQuery.setMinWidth(70);
		lblName.setMinWidth(70);
		//		Wrap text in TextArea to make use of space - no side scrolling
		txtQuery.setWrapText(true);

		//Size and align buttons
		btnCancel.setMinWidth(60);
		btnSubmit.setMinWidth(80);

		//Create HBox to align Cancel and Submit Buttons
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(15);
		//Buttons on bottom right will be intuitive to users
		hbButtons.setAlignment(Pos.BOTTOM_RIGHT);

		hbButtons.getChildren().addAll(btnCancel, btnSubmit);


		//Add components to GridPane:

		//Text indicating form purpose
		gpContact.add(txtContact, 1, 0);

		//Name Label and corresponding TextField horizontally aligned on top row
		gpContact.add(lblName, 0, 1);
		gpContact.add(txtfName, 1, 1);

		//Query Label and corresponding TextArea aligned horizontally in middle row
		gpContact.add(lblQuery, 0, 2);
		gpContact.add(txtQuery, 1, 2);

		//Required field indicator and HBox containing Buttons on third row
		gpContact.add(lblRequired, 0, 3);
		gpContact.add(hbButtons, 1, 3);
		
		

		//Set background colour manually to override stylesheet
		gpContact.setStyle("-fx-background-color: #F5FAFF");

		//Events within the window
		//Close GridPane if user cancels
		btnCancel.setOnAction(ae -> contactStage.close());
		btnSubmit.setOnAction(ae -> {

			//Tried some validation to protect against possible SQL injections
			//TalkBox has no database but should be prepared

			if (isValidQuery(txtQuery.getText()) && isValidString(txtfName.getText())) {
				//Print query as confirmation
				//Give query random ID
				System.out.println("Support Requested\nTicket ID: " + (int)(Math.random() * 10000000) +  "\nName: " 
						+ txtfName.getText() + "\nQuery: " + txtQuery.getText() + "\n");
				contactStage.close();

				//Alert to indicate submission success
				Alert alertContact = new Alert(AlertType.CONFIRMATION);
				alertContact.setTitle("Query Submitted");
				alertContact.setHeaderText("Thank you for submitting a query.");
				alertContact.setContentText("A member of our Support Team will contact you within 24 hours.");

				//No windows resizable except homepage 
				alertContact.setResizable(false);

				//Reusing methods to customise Alert Button text and behaviour
				Button btnLogout = (Button)alertContact.getDialogPane().lookupButton(ButtonType.CANCEL);
				btnLogout.setText("Log out");

				//	Make log out button a bit smaller than return button (would rather users return!)
				btnLogout.setMaxWidth(40);
				Button btnOk = (Button)alertContact.getDialogPane().lookupButton(ButtonType.OK);
				btnOk.setText("Return to TalkBox");

				/*BELOW: A method I found on StackOverflow while searching for help on how to change the 
				 * behaviour of a window's X/Close button. This method creates a new Window object called
				 *  contactWindow out of the alertContact Alert. On close request, AKA when clicking the X 
				 *  in one of the top corners, it simply hides the window as expected instead of following 
				 *  the behaviour set out for CANCEL type Buttons (logout). */

				//https://stackoverflow.com/questions/32048348/javafx-scene-control-dialogr-wont-close-on-pressing-xq
				Window contactWindow = alertContact.getDialogPane().getScene().getWindow();
				contactWindow.setOnCloseRequest(click -> contactWindow.hide());

				//Using same methods from JavaFX Docs for customising Alert Button behaviour

				alertContact.showAndWait().filter(response -> response == ButtonType.CANCEL).
				ifPresent(response ->Platform.exit());

			}
			//Indicate to user if they left any required fields blank
			//Could be improved to account for names/queries that are only spaces
			else if (txtQuery.getText().length() == 0 || txtfName.getText().length() == 0) {
				Alert alertEmptyField = new Alert(AlertType.ERROR);
				alertEmptyField.setHeaderText("Error: Required Field(s) Left Blank");
				alertEmptyField.setContentText("Both Name and Query required for submission.");
				alertEmptyField.showAndWait();
				
			}
			else {
				//Don't let user proceed if they've entered possible injection
			
				Alert alertInjection = new Alert(AlertType.ERROR);
				alertInjection.setHeaderText("Error: Forbidden Value");
				alertInjection.setContentText("Unable to process your query at this time. Forbidden value in TextField.");
				alertInjection.showAndWait();
			}




		});



		//Set Scene for GridPane
		Scene s = new Scene(gpContact);
		//Applying stylesheet to this Scene 
		s.getStylesheets().add("application.css");
		contactStage.setScene(s);
		contactStage.show();
	}//End contactSupport()

	//Make a new status
	public void updateStatus() {

		lblUserStatus.setText(txtfStatus.getText());

		//Empties status TextField back to its default after user updates status
		txtfStatus.setText("Today I feel...");


	}

	//Remove current status
	public void clearStatus() {
		//Remove status Label content when user clears


		lblUserStatus.setText("");

	}

	//Change profile Image - remove current or choose new
	public void updatePicture() {

		//CONFIRMATION Alert: users must choose how to proceed
		Alert alertChangePicture = new Alert(AlertType.CONFIRMATION);

		//Proceeding will allow them to change picture, but they must choose between removing or updating
		alertChangePicture.setTitle("Update your Profile Picture");
		alertChangePicture.setHeaderText("Choosing a new picture?");
		alertChangePicture.setContentText("Would you like to remove the current picture or choose a new one?");


		//Create new Button from CONFIRMATION Alert's default CANCEL Button: Remove Picture
		Button btnRemove = (Button)alertChangePicture.getDialogPane().lookupButton(ButtonType.CANCEL);
		btnRemove.setText("Remove current picture");


		//		Make log out button a bit smaller than return button (would rather users return)
		//		btnLogout.setMaxWidth(40);
		Button btnNew = (Button)alertChangePicture.getDialogPane().lookupButton(ButtonType.OK);
		btnNew.setText("Choose picture");

		//		Picture changing methods written separately - see below
		btnNew.setOnAction(click -> choosePicture());
		btnRemove.setOnAction(click -> removePicture());

		alertChangePicture.showAndWait();
	}


	//Remove current profile picture - called from updatePicture()
	public void removePicture() {
		//Try-catch for image upload in case image isn't found
		try {
			//For removing the picture, simply return picture to default
			imgProfile = new Image("default-pic.jpg");
			imgView.setImage(imgProfile);
		}
		catch(Exception e) {
			System.out.println("Unable to remove photo at this time.");
			System.out.println(e.toString());
		}

	}//end removePicture()

	//Choose new profile picture - called from updatePicture()
	public void choosePicture() {


		//User will navigate files
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose a new Profile Picture:");

		//Create Extension Filter - jpg or png
		FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
		//Add Extension Filter to File Chooser
		fc.getExtensionFilters().add(imgFilter);



		//Create File object for user's choice
		File picture;
		//Check if user selects a new picture
		if ((picture = fc.showOpenDialog(null)) !=null) {
			//Error here would be that user picks a picture but it cannot upload for whatever reason
			//So the try-catch goes in the if block, since if statement is presumed to be true if error occurs
			try {
				imgProfile = new Image(picture.toURI().toString());

				imgView.setImage(imgProfile);
			}
			catch(Exception e) {
				System.out.println("Unable to upload a photo at this time.");
				System.out.println(e.toString());
			}

		}

		//If no photo selected, no event
		else;


	}//end choosePicture()


	//Edit profile information
	public void editProfile() {
		//New Stage for editing profile
		Stage editProfile = new Stage();
		editProfile.setTitle("Edit Profile");
		editProfile.setMinWidth(380);
		editProfile.setMinHeight(200);
		//No windows resizable except homepage
		editProfile.setResizable(false);

		//Form format with multiple fields, so GridPane used
		GridPane gpEditProfile = new GridPane();

		//Sizing and alignment for GridPane
		gpEditProfile.setMaxWidth(380);
		gpEditProfile.setPadding(new Insets(10));
		gpEditProfile.setHgap(20);
		gpEditProfile.setVgap(10);

		//Align contents center
		gpEditProfile.setAlignment(Pos.CENTER);



		//Create Labels and Textfields for edit profile dialog
		Label lblEditName = new Label("Full name:");
		TextField txtfName = new TextField();


//		Label lblEditNumber = new Label("Student number:");
//		TextField txtfNumber = new TextField();


		Label lblEditCity = new Label("Current city:");
		TextField txtfCity = new TextField();
		//Set TextField width so user doesn't have to scroll 	
		txtfCity.setMinWidth(200);

		/*Each TextField should show the currently saved values by default */
		txtfName.setText(lblName.getText());
		txtfCity.setText(lblCity.getText());


		//Save or Cancel changes Buttons
		Button btnCancel = new Button("Cancel");
		Button btnSave = new Button("Save");
		//Button styling: Cancel should be smaller than Save
		btnCancel.setMinWidth(60);

		//HBox to simplify Button positioning
		HBox hbButtons = new HBox();
		//HBox size and styling
		btnSave.setMinWidth(80);
		hbButtons.setSpacing(10);
		//Align Buttons to right of HBox
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);

		//Add Buttons to HBox
		hbButtons.getChildren().addAll(btnCancel, btnSave);

		//Add all components to GridPane
		//First row: Label and TextField for user's name horizontally aligned
		gpEditProfile.add(lblEditName, 0, 0);
		gpEditProfile.add(txtfName, 1, 0);

		//Second Row: Label and TextField for user's ID number
//		gpEditProfile.add(lblEditNumber, 0, 1);
//		gpEditProfile.add(txtfNumber, 1, 1);

		//Third Row: Label and TextField for user's course
		gpEditProfile.add(lblEditCity, 0, 2);
		gpEditProfile.add(txtfCity, 1, 2);

		//Final Row: HBox buttons
		gpEditProfile.add(hbButtons, 1, 3);

		//Button events:
		//Cancel Button closes Stage
		btnCancel.setOnAction(ae -> editProfile.close());

		//Save Button updates profile
		btnSave.setOnAction(ae -> {
			//I think there is probably a simpler way to validate text, 
			//but for now best I can come up with using String methods I know
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setHeaderText("Invalid Input");
			alertError.setContentText("* Your first and last name must only contain numeric characters and must be separated by a space"
			
					+ "\n* You may enter your city, country, or both in the form of City, Country");

			if (isValidString(txtfName.getText()) && isValidString(txtfCity.getText())) {
				
				lblName.setText(txtfName.getText().trim());
				lblCity.setText(txtfCity.getText().trim());
				
				//Close Stage when updated
				editProfile.close();
			}

			else {

				alertError.showAndWait();
			}






		});

		//Create Scene, apply stylesheets, set the Scene and show Stage
		Scene s = new Scene(gpEditProfile);
		s.getStylesheets().add("application.css");
		//Override default background colour
		gpEditProfile.setStyle("-fx-background-color: #F5FAFF");
		editProfile.setScene(s);
		editProfile.show();


	}//end editProfile()


	//Validate user input

	public boolean isValidString(String userString) {
		//Testing string input for name and Programme
		//The string of possible valid characters includes all uppercase and lowercase letters
		//Included space because that is a valid (and necessary) character for these fields
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ, ";
		//Create an array of one-character strings to test against alphabet
		String[] txtArray = userString.trim().split("");

		for (int i = 0; i<txtArray.length; i++) {
			//If the alphabet string doesn't contain any one letter from the user's input, input = invalid
			//The string also must contain a space, to prevent user from inputting something like FirstnameLastname as their name

			if (!(alphabet.contains(txtArray[i]))  || (!userString.contains(" "))) {
				//Communicate this to the user as them needing to input both names and entire course title
				System.out.println("** Error **\nForbidden value in TextField\nError Code: 1126\n");
				return false;
			}

		}
		return true;

	}//End isValidString()

	public boolean isValidNumber(String userNumber) {
		//Testing string input for user ID number
		String digits = "0123456789";
		//Just trimming the string on the off chance a user somehow entered a number with spaces... want to cover all bases
		String[] numArray = userNumber.split("");


		for (int i=0; i<numArray.length; i++) {
			if (!(digits.contains(numArray[i])) || userNumber.length() != 7) {
				System.out.println("** Error **\nID Number may only contain digits 0-9 and must be 7 digits.\nError Code: 1127\n");
				return false;
			}
		}
		return true;
	}//End isValidNumber()

	public boolean isValidQuery(String userQuery) {
		//Not the most secure or effective method against SQL injections, but for now I am simply banning
		//users from entering SELECT and FROM in all caps in their queries, since I think these would be two 
		//necessary keywords for an injection. This method could be a big problem, though, if a user types 
		//a query in all caps saying something like "I can't select buttons from the profile pane" ... 
		//so this method will need some updating in future versions
		String injection1 = "SELECT";
		String injection2 = "FROM";
		//Same general form as previous two validation methods, see if their text contains forbidden values
		if (userQuery.contains(injection1) && userQuery.contains(injection2)) {

			System.out.println("** Error **\nForbidden value in TextField.\nPossible injection attempt detected.\nError code: 1128\n");
			return false;
		}
		return true;
	}//End isValidQuery()

	//End event methods



	@Override
	public void start(Stage primaryStage) throws Exception{
		//Set title:
		primaryStage.setTitle("TalkBox");


		//Stage default height and width
		primaryStage.setHeight(550);
		primaryStage.setWidth(700);

		//Main container: BorderPane
		BorderPane bpMain = new BorderPane();

		//Sub containers
		//Left VBox: user profile
		VBox vbProfile = new VBox();
		//Sub containers within vbProfile
		//Top: vbPicture
		VBox vbPicture = new VBox();
		//Bottom: vbEditProfile
		VBox vbEditProfile = new VBox();
		//end sub containers

		//Right VBox: status updates
		VBox vbUpdateStatus = new VBox();

		//Bottom VBox: status
		VBox vbStatus = new VBox();


		//Start container spacing, alignment, and padding:

		//Left VBox: vbProfile
		//Override root background colour
		vbProfile.setStyle("-fx-background-color: #DDEDFD");
		//Narrower than vbUpdateStatus
		vbEditProfile.minWidthProperty().bind(primaryStage.widthProperty().divide(3.5));

		//Should take up full height of Stage
		vbProfile.setMaxHeight(bpMain.getHeight());
		//Move components away from Stage edges
		vbProfile.setPadding(new Insets(15));



		//Sub container Styling:
		//Top VBox within vbProfile
		vbPicture.setSpacing(10);
		//Align contents to center
		vbPicture.setAlignment(Pos.CENTER);
		//Set sizing of Image within vbPicture
		//Around half its parent container, so around 1/5 of primaryStage


		//Bottom VBox within vbProfile
		vbEditProfile.setSpacing(15);
	
		//Space profile information away from VBox above it (profile Image and Button)
		vbEditProfile.setMargin(vbPicture, new Insets(18));
		//End sub container styling

		//Right VBox: vbUpdateStatus
		//Space components out and move away from Stage edges
		vbUpdateStatus.setSpacing(10);
		vbUpdateStatus.setPadding(new Insets(15));
		//Override the main background colour (the background colour of the profile pane)
		vbUpdateStatus.setStyle("-fx-background-color: #F5FAFF");
		//Status section wider than profile
		vbUpdateStatus.minWidthProperty().bind(primaryStage.widthProperty().divide(1.5));

		//Bottom VBox: vbStatus
		//Spacing and padding consistent with other containers
		vbStatus.setSpacing(10);
		vbStatus.setPadding(new Insets(15));


		//Override stylesheet

		vbStatus.setStyle("-fx-background-color: #F5FAFF");
		//This VBox will contain all components on the right side of the UI
		VBox vbRight = new VBox();
		vbRight.setSpacing(300);
		//Right side of UI should stay the same height as the BorderPane itself, its parent container

		//Override the main background colour (the background colour of the profile pane)
		vbRight.setStyle("-fx-background-color: #F5FAFF");

		//Having trouble making the containers perfectly responsive; the bottom/right componenets will disappear
		//if the window is resized to be too small
		vbRight.minHeightProperty().bind(primaryStage.heightProperty());
		//End container spacing, alignment, and padding


		//Start component alignment, padding, specifications

		//Left side of UI: profile
		//Labels should wrap
		/* I wanted to allow the labels to wrap for easier reading, and very luckily saw the setMnemonicParsing 
		 * method in the Label dropdown menu of methods. I assumed based on its name it would allow the text to be 
		 * wrapped without breaking mid-word and I have applied it to all the labels in the app.*/

		lblName.setMaxWidth(200);
		lblName.setMnemonicParsing(true);
		lblName.setWrapText(true);

//		lblNumber.setMaxWidth(200);
//		lblNumber.setMnemonicParsing(true);
//		lblNumber.setWrapText(true);

		lblCity.setMaxWidth(200);
		lblCity.setMnemonicParsing(true);
		lblCity.setWrapText(true);


		//Alignment for right side of U: Status
		//Wrapping text for status
		lblUserStatus.setMaxWidth(400);
		lblUserStatus.setMnemonicParsing(true);
		lblUserStatus.setWrapText(true);

		//TextField for the status should always be a bit smaller than the window itself
		txtfStatus.minWidthProperty().bind(vbUpdateStatus.widthProperty().divide(1.1));

		//Add components to containers
		//Top: MenuBar
		//Add Menus to MenuBar 
		mbMain.getMenus().addAll(mnuTalkBox, mnuFile, mnuHelp);
		//Add MenuItems to Menus:
		mnuFile.getItems().addAll(mitemLogout);	
		mnuTalkBox.getItems().addAll(mitemAbout);
		mnuHelp.getItems().addAll(mitemContact);



		//Left side of UI: Profile
		//Try to add profile picture if available
		try {
			vbPicture.getChildren().add(imgView);
		}
		catch(Exception e) {
			System.out.println("Unable to load profile picture.");
			System.out.println(e.toString());
		}
		//Add button to this VBox as well, underneath image (if there)
		vbPicture.getChildren().addAll(btnChangePhoto);

		//Add profile labels and edit Button
		vbEditProfile.getChildren().addAll(lblName, lblCity, btnEditProfile);

		//Add profile sub VBoxes to main profile VBox
		vbProfile.getChildren().addAll(vbPicture, vbEditProfile);

		//Right side of UI: Profile
		//Bottom-right VBox contains the Label for the status an the Button to clear the status
		vbStatus.getChildren().addAll(lblUserStatus, btnClearStatus);
		vbUpdateStatus.getChildren().addAll(lblUpdateStatus, txtfStatus, btnStatus);
		vbRight.getChildren().addAll(vbUpdateStatus, vbStatus);

		//Fill our BorderPane
		bpMain.setTop(mbMain);
		bpMain.setLeft(vbProfile);
		bpMain.setRight(vbRight);


		//Try to add app icon if available
		try {
			Image appIcon = new Image("talkbox-logo.png");
			primaryStage.getIcons().add(appIcon);
		}
		catch(Exception e) {
			System.out.println("Unable to locate app logo.");
			System.out.println(e.toString());

		}

		//Create Scene, set Scene, apply stylesheet
		Scene s = new Scene(bpMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("application.css");

		//Show the stage
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
