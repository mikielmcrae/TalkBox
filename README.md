# TalkBox💭💬
JavaFX social media frontend - let the world know what's up! 

**Table of contents**
I. Installation instructions
II. Feature preview


Welcome to TalkBox, the (future) world's leading minimialist social media app!

I. INSTALLATION INSTRUCTIONS
If you don't have a JavaFX environment set up and would like to run TalkBox from your own machine, follow these steps.

(If you have a JavaFX environment set up already, you can skip to step _vi_).

i. If you do not have the Java JDK installed, you can install it for your device [here](https://www.oracle.com/java/technologies/downloads/).

ii. If you do not have Eclipse IDE installed, it can be found [here](https://www.eclipse.org/downloads/).

iii. Follow the instructions [here](https://openjfx.io/openjfx-docs/#IDE-Eclipse) under the **Non-Modular IDE** section.

iv. [Download the latest release of JavaFX](https://gluonhq.com/products/javafx/). **Ensure your download is of type SDK!**
this will download as a zip. Open the zip folder and take note of where you keep this folder.

v. Now, download the JavaFX Plugin for Eclipse by opening Eclipse Marketplace in Eclipse. Simply type 'FX' into the search bar, and download "e(fx)clipse
3.7.0" (or the latest version). After reading and agreeing to the terms, Eclipse may restart.

vi. In Eclipse, open a new **Project** (this is not a Java Project, but just a "Project"!)

<img width="199" alt="An image of a dropdown list of project types, with 'Java Project' highlighted" src="https://user-images.githubusercontent.com/86265908/174652319-c320637f-637c-4aa5-85e9-4d579ce00571.png">

From here, open a new JavaFX Project.

<img width="323" alt="A list of project types in Eclipse, with 'JavaFX Project' highlighted" src="https://user-images.githubusercontent.com/86265908/174652354-97250a0e-c614-4dff-9540-47e4cf04d519.png">

Upon clicking the "Next" Button at the bottom of the window, you will be able to name your project -- I recommend naming your project after whatever project you're downloading (In this case, TalkBox). This can be done later if desired.

Once this is done, click the **Next** button instead of the **Finish** button.

<img width="799" alt="A window in Eclipse allowing users to set a name for their project and continue" src="https://user-images.githubusercontent.com/86265908/174652620-c48252cf-e358-41fd-8f49-7ea0d3e55bd9.png">

vii. Once you have clicked the **Next** button, you will be brought to a window wherein you can further customise the setup for your Project:

<img width="796" alt="A window with further customisation options for the user's new Project, including the 'Libraries' Pane" src="https://user-images.githubusercontent.com/86265908/174652941-2878aeac-0596-4a81-82a5-a9735211acfd.png">

From here, click **📚Libraries**: 

<img width="775" alt="The 'Libraries' section of the Project setup process, where users can add libraries to the module path or class path" src="https://user-images.githubusercontent.com/86265908/174653123-072c1c66-9787-455a-89e5-2c05c342a3f2.png">

With **Classpath** selected, click "Add Library" from the list of buttons on the right. You will need to add two libraries: The JavaFX SDK and the JavaFX User Library.

To add the **JavaFX SDK**: Simply select the JavaFX SDK from the list of Libraries and click "Finish".

<img width="418" alt="The 'Add Libraries' window shown with the JavaFX SDK selected" src="https://user-images.githubusercontent.com/86265908/174653875-b6a0f04d-f0a1-4fe7-9f9d-5bfb36596b4a.png">

To add the **JavaFX User Library**: Reopen the "Add Library" window, select User Library and click **Next**. Check the JavaFx User Library box and click "Finish".
<img width="315" alt="The User Library section of the 'Add Libraries' window shown with the JavaFX User Library checkbox selected" src="https://user-images.githubusercontent.com/86265908/174653893-cf6aec9c-a01e-4df6-a519-30bb94ad6c47.png">

Remember to add **both** of these libraries to your project.

viii. Your project is set up!

If you see the following Red Xs, delete the module-info.java section by right clicking it and deleting.

<img width="257" alt="The user's project files shown with red error indicators from the module-info file" src="https://user-images.githubusercontent.com/86265908/174654089-be3715a7-26fc-471e-803a-b6836330dc76.png">


ix. Download the TalkBox code as a zip from the [TalkBox repository](https://github.com/mikielmcrae/TalkBox) as a ZIP.
Drag "TalkBox.java" from this folder into the applicaton folder in your Eclipse project. You may delete Main.java.

x. Now, navigate to TalkBox.java and run it. The console should display the following error:

"Error: JavaFX runtime components are missing, and are required to run this application"

To fix this, right click on TalkBox.java in your project. Navigate down to "Run as...", and select "Run configurations". 

<img width="488" alt="a dropdown menu including an option for user to set run configurations for their project" src="https://user-images.githubusercontent.com/86265908/174654550-db628cb9-713e-485b-be92-b40f41ed179a.png">

**Note** your Main file will not show up in this pane until you've attempted to run it at least once.

Once here, navigate to the "Arguments" tab. In the VM Arguments textarea, enter the following arguments:

**For Linux/Mac**: --module-path /path/to/javafx-sdk-17/lib --addmodules javafx.controls,javafx.fxml

**For Windows**:  --module-path "\path\to\javafx-sdk-17\lib" --addmodules javafx.controls,javafx.fxml 

where the "path to" the JavaFX SDK is the location in which you saved the opened ZIP in step IV.

**Uncheck** the checkbox under this textarea, "Use the -XstartOnFirstThread
argument when launching with SWT". 

Apply the changes. If successful, running TalkBox.java will now result in a window opening. It might look something like this. 

<img width="697" alt="A version of the TalkBox UI missing some components" src="https://user-images.githubusercontent.com/86265908/174656508-6214e505-26ac-4454-859a-cded3525ad05.png">

Delete "application.css" from the **src** folder.

xi. From the TalkBox folder you downloaded from this repository, select "application.css", "default-pic.png" and "talkbox-logo.png". Drag and drop or simply paste into the **src** folder under your TalkBox project in Eclipse. (If they do not drag directly in, you can drag them into the folder first and them move them into src).

<img width="239" alt="An image showing the css file, TalkBox logo, and default profile picture selected, ready to be moved to the application folder" src="https://user-images.githubusercontent.com/86265908/174657153-14893b8c-0677-4d42-bc3b-78a8a5bf0da2.png">

Your project should look something like this if everything is in the right location:

<img width="213" alt="A screenshot of the project setup in JavaFX, showing TalkBox.java in the application folder, while the assets for the app are in the src folder" src="https://user-images.githubusercontent.com/86265908/174657289-8253e4f2-9470-4051-b793-8a966725d5d4.png">


If the rest of TalkBox's assets have been moved successfully, re-running the file should result in the final TalkBox UI:

<img width="694" alt="An image of TalkBox's final UI" src="https://user-images.githubusercontent.com/86265908/174657025-6a5b08db-67fc-4fa9-8ef4-8b80bccb00bb.png">

From here, you can peruse all functionality of the UI! You may delete Main.java if you wish, but it will be inconsequential either way.

Thank you!!!
-Mikiel



______________________________________________________________________________________________________________________________________________________

TalkBox is just that - an area designed for talking, sharing thoughsts, and updating your friends on what's new!

The app is written in JavaFX and styled in CSS. The UI is designed to give users an experience they're already used to as soon as they log in.

<img width="695" alt="An image of TalkBox's main UI, featuring a profile panel on the left and a status update area on the right" src="https://user-images.githubusercontent.com/86265908/174599025-d9f40bec-528e-47c2-9485-823d9fdac9a6.png">

Here is the first glimpse users will have of TalkBox's simplistic UI. It is here where they are able to update and post their statuses:

<img width="451" alt="An image of the status update field, where a user has typed 'Today I feel...upbeat!'" src="https://user-images.githubusercontent.com/86265908/174600069-944885a1-aad4-4e61-8ddf-39d557a2c7e8.png">

<img width="302" alt="An image of the user's posted status at the bottom of the status pane, with the status sharing field now having been cleared of the user's input." src="https://user-images.githubusercontent.com/86265908/174600177-5c12f117-aafb-4410-b6a7-1b003d95da87.png">

Users are even able to update their profile information, including their name and city, and validation techniques have been implemented to ensure user input is appropriate.

<img width="375" alt="An image of the profile editing pane, where users can change their name and/or location." src="https://user-images.githubusercontent.com/86265908/174600386-1c1d2afe-1b5d-48f5-8895-d8f0511ff6b1.png">


<img width="355" alt="An image of the warning window a user will receive if their input is invalid." src="https://user-images.githubusercontent.com/86265908/174600400-8cbd6ac8-bc09-42bf-992e-95b7c25452f6.png">

A custom profile picture can be selected in the formats of PNG and JPEG.

<img width="357" alt="The option pane for selecting profile picture. Users can remove their current picture or upload a new one." src="https://user-images.githubusercontent.com/86265908/174601611-e2b8e1ec-045c-4fbe-8519-6577aaffeabe.png">


<img width="225" alt="The user's updated profile with a custom personal profile picture." src="https://user-images.githubusercontent.com/86265908/174601702-3702b78d-4986-41d3-8b2d-151a25965d96.png">


If curious, users can read more about the app from the MenuBar at the top of the UI.

<img width="356" alt="TalkBox's About pane, which thanks users for using TalkBox and provides dummy copyright information." src="https://user-images.githubusercontent.com/86265908/174601935-52254df2-886e-482d-bdb5-6b9ffefef45a.png">


Users can even submit queries to the developer, allowing for their voices to be heard on anything regarding bug reports, to questions, to comments.

<img width="673" alt="An image showing the query pane, where a user can type their name and the feedback or question they have for the developer" src="https://user-images.githubusercontent.com/86265908/174600578-5fb0671a-c3f6-415c-ad30-5982da49121f.png">

Validation techniques have been implemented on the query pane to ensure user input is appropriate.

User queries are sent to the console currently, with a generated ticket number and all information as included by the user.


<img width="528" alt="An image of the IDE console which includes the user's generated ticket number, name, and query." src="https://user-images.githubusercontent.com/86265908/174600730-27028c86-62f3-41c0-a496-9e969029f604.png">




