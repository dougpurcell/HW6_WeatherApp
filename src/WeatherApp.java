
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.HostServices;

import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class WeatherApp extends Application {

      // Constants
    private static final int MAX_RECORDS = 7;
    // Variables and Instances of Classes
    private WeatherRecord weeklyDataArray[] = new WeatherRecord[MAX_RECORDS];        //store all student records
    private int nextDay = 0;         // location of next empty position in the array
    private int numDays = 0;         // number of input records

    private String currentDay = "";
    private String xmlDayName;    // temporary storage for name of weekday from xml
    private String xmlForecast;   //temporary storage for forecast from xml

    private int xmlHighTemp;   //temporary storage for high temperature from xml
    private int xmlLowTemp;    // temporary storage for low temperature from xml
    private int currentIndex;

    private WeatherPane weatherPane;

    private VBox mainPane;
    private HBox buttonPane;

    private ComboBox<String> daysOfWeek = new ComboBox<>();

    private Button week;
    private Button stat;


    private Database myDb = new Database();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        mainPane = new VBox();

        buttonPane = new HBox();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setCenter(daysOfWeek);
        daysOfWeek.setPrefWidth(200);
        daysOfWeek.setValue("Pick Day of Week");
        ObservableList<String> items = FXCollections.observableArrayList
                ("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        daysOfWeek.getItems().addAll(items);


        week = new Button("Weeks");
        stat = new Button("Stats");
        week.setOnAction(e -> weatherPane.drawWeekly(weeklyDataArray));
        buttonPane.getChildren().add (week);
        buttonPane.getChildren().add(stat);
        buttonPane.getChildren().add(daysOfWeek);
        HostServices host = getHostServices();

        weatherPane = new WeatherPane(this, host.getDocumentBase()); // Create a pane

        daysOfWeek.setValue(items.get(0));
        daysOfWeek.setOnAction(e -> {
            currentDay = daysOfWeek.getValue();
            currentIndex = findIndexOfDay(currentDay);
            weatherPane.drawGraphics(weeklyDataArray, currentIndex);
        });


        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setTitle("Weather App"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        stat.setOnAction(e -> {
            weatherPane.drawStats();
        });

        // add panes to main pane
        mainPane.getChildren().add(buttonPane);
        mainPane.getChildren().add(weatherPane);
        //mainPane.getChildren().add()
        // read XML data from file and store in weeklyDataArray 
        readXMLFile("Weather.xml");

        // Create a handler for weather
 


        storeData();

    }

    public void storeData ()
    {
        // create table in the database
        myDb.createTable();

        // store each Student Record in the table
        for (int i = 0; i< numDays; i++)
        {
            myDb.storeRecord (
                weeklyDataArray[i].getdayName(),
                weeklyDataArray[i].getForecast(),
                String.valueOf(weeklyDataArray[i].getHighTemp()),
                String.valueOf(weeklyDataArray[i].getLowTemp())
            );
        }

    }//end storeData

    public int findIndexOfDay(String currentDay) {
        for (int i = 0; i < 7; i++) {
            if (weeklyDataArray[i].getdayName().equals(currentDay)) {
                return i;
            }
        }
        return 0;
    };

//     public void drawWeatherGraphics() {
//          weatherPane.drawGraphics(weeklyDataArray, nextGraphic); // Draw a new graphic
//          nextGraphic = (nextGraphic + 1) % MAX_RECORDS; // loop through array values
//    };
         
         
    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    //the method reads info from the input XML file, and then stores it in the studentArray[] 
    public void readXMLFile(String filename) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setValidating(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            NodeList list = document.getElementsByTagName("dailyWeather");

            //This for loop gathers all the student attributes, puts them in a WeatherRecord object
            //then stores that student in the weekArray
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                 
                xmlDayName = element.getAttribute("name");
                xmlForecast = element.getAttribute("forecast");
                xmlHighTemp = getHighTemp(element);
                xmlLowTemp = getLowTemp(element);
                WeatherRecord myWeather = new WeatherRecord(xmlDayName, xmlForecast, xmlHighTemp, xmlLowTemp);

                // store student record in array
                weeklyDataArray[nextDay] = myWeather;

                // increment number of student records and move to next position in studentArray
                numDays++;
                nextDay++;
                
                System.out.println(myWeather.toString());

            }//end for loop loading the studentArray[] with full student records

        }//end try block
        catch (ParserConfigurationException parserException) {
            parserException.printStackTrace();
        }//end catch block
        catch (SAXException saxException) {
            saxException.printStackTrace();
        }//end catch block
        catch (IOException ioException) {
            ioException.printStackTrace();
        }//end catch block

    }//end readFile()

    //RETURNS THE HIGH TEMP OF DAILY WEATHER
    public int getHighTemp(Element parent) {
        NodeList child = parent.getElementsByTagName("highTemp");
        Node childTextNode = child.item(0).getFirstChild();
        return Integer.parseInt(childTextNode.getNodeValue());
    }

    //RETURNS THE LOW TEMP OF DAILY WEATHER   
    public int getLowTemp(Element parent) {
        NodeList child = parent.getElementsByTagName("lowTemp");
        Node childTextNode = child.item(0).getFirstChild();
        return Integer.parseInt(childTextNode.getNodeValue());
    }


}
