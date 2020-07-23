# IBAN number validation checker

Software created to check the validity of any IBAN (International Bank Account Number) number.

## Technology

The project was developed with IntelliJ IDEA
Project SDK: Java JDK 1.8

### External libraries/modules
* Jersey
* JUnit
* Tomcat
* Gson

## Instructions

The project already has external libraries built in. User only needs to open the project and follow these steps:

### Local
To check the number locally, user needs to run Main class which will open a GUI with two buttons:
* **Single line input** - Opens GUI where user is requested to enter a single IBAN number. When the 'Submit' button is pressed, a label with answer will appear at the bottom of the window.
* **Text file input** - Opens a new window where user is asked to specify text file's location by entering the name of the file (with .txt ending) and its full path (E.g. C:/Users/PC/data). When both fields are filled, user needs to press the 'Submit' button and a label will appear at the bottom of the window which will tell whether the file was successfully located and checked. The output file will be created in main project folder.

### Web
To check IBAN number on local web server, user must run the server by starting TomcatServer class. When the server is up an running, user must run HelloClient class. The program will output a link where IBAN number can be checked on the web (URL is http://localhost:8085/webApp/). There will be two options:
* **Single line** - User can enter a single IBAN number to the text field and when the 'Submit' button is pressed, the program will redirect user to the new page which will show whether the number is valid or not.
* **Text file** - User will have to specify the location of a text file and it's name. When 'Submit' button is pressed, the program will redirect user to the new page which will show a list of IBAN numbers and whether they are valid or not.

## Testing
IBAN checker algorithm can be tested in ValidationTest class. The class is populated with tests for almost every method from Validation class. Tests can be run by Control+Shift+F10.

Furthermore, in 'data' folder you can find a text file which has valid and non-valid IBAN codes to be tested (either by single line input or whole text file).
