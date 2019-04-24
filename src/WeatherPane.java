
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.*;
import javax.xml.bind.Element;

public class WeatherPane extends Pane {

    private String imgPath;
    private WeatherApp myApp;
    public WeatherPane(WeatherApp a, String i){
        imgPath = i;
        myApp = a;
    }
    String weekly[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public void drawGraphics(WeatherRecord[] wInfo, int index) {
        
        //needs to pass the weather in and specify index

        getChildren().clear(); // clear the pane for next graphic
        
        // add code to display the input name of the weekday contained in  wInfo parameter
       Text Text12 = new Text (225,90, (wInfo[index].getdayName()));
        getChildren().add(Text12);
        
        // add code to display the low  temperature contained in  wInfo parameter
       Text Text11 = new Text (100,275, Integer.toString(wInfo[index].getLowTemp()));
        getChildren().add(Text11);
        Text text5 = new Text(100 , 250 , "Low");
        text5.setFill(Color.BLACK);
        getChildren().add(text5);
       
        
        // add code to display the high temperature contained in  wInfo parameter
        
       Text Text10 = new Text (385,275, Integer.toString(wInfo[index].getHighTemp()));
        getChildren().add(Text10);
         Text text8 = new Text(375 , 250 , "High");
         text8.setFill(Color.BLACK);
          getChildren().add(text8);
       
       
       
       
        switch (wInfo[index].getForecast()) // use forecast from wInfo parameter to draw appropriate graphics
        {
            case "Sun":         // Code to draw the graphics for Sun

                Text text0 = new Text(225, 225, "Sunshine");
                text0.setFill(Color.BLACK);
                

                Image image0 = new Image( imgPath + "Images/Sun.png", 100.0,100.0,true,true, false);
                ImageView imageview0 = new ImageView(image0);
                imageview0.setX(200);
                imageview0.setY(100.0);
              
                getChildren().add(imageview0);
                getChildren().add(text0);
       
                break;
                
            case "Clouds":   // Code to draw the graphics for Clouds

                Text text1 = new Text(225, 225, "Clouds");
                text1.setFill(Color.BLACK);

                Image image1 = new Image( imgPath + "Images/Cloud.png", 100.0,100.0,true,true, false);
                ImageView imageview1 = new ImageView(image1);
                imageview1.setX(200);
                imageview1.setY(100.0);
                

                //TODO: add x and y coordinates for size/placement
                getChildren().add(imageview1);
                getChildren().add(text1);
              

                // Change the wording of the text above and add code here to display graphics for cloudy weather

                break;
                
            case "Snow":   // Code to draw the graphics for Snow
                Text text2 = new Text(225, 225, "Snow");
                text2.setFill(Color.BLACK);  // Change the pen color
                Image image2 = new Image( imgPath + "Images/Snow.png", 100.0,100.0,true,true, false);
                ImageView imageview2 = new ImageView(image2);
                imageview2.setX(200);
                imageview2.setY(100.0);
            
                getChildren().add(imageview2);
                getChildren().add(text2);
               
                
                // Change the wording of the text above and add code here to display graphics for snowy weather

                break;
                
            case "Rain":  // Code to draw the graphics for rain
                Text text3 = new Text(225, 225, "Rain");
                text3.setFill(Color.BLACK);  // Change the pen color
                Image image3 = new Image( imgPath + "Images/Rain.png", 100.0,100.0,true,true, false);
                ImageView imageview3 = new ImageView(image3);
                imageview3.setX(200);
                imageview3.setY(100.0);
               
                
                 getChildren().add(imageview3);
                getChildren().add(text3);
                // Change the wording of the text above and add code here to display graphics for  rainy weather

                break;
                
            case "Wind":   // Code to draw the graphics for wind
                Text text4 = new Text(225, 225, "Wind");
                Image image4 = new Image( imgPath + "Images/Wind.png", 150.0,150.0,true,true, false);
                ImageView imageview4 = new ImageView(image4);
                imageview4.setX(200);
                imageview4.setY(100.0);
                text4.setFill(Color.BLACK);  // Change the pen color
               
                
                 getChildren().add(imageview4);
                getChildren().add(text4);
                // Change the wording of the text above and add code here to display graphics for windy weather

                break;
                
            default:
        
        } // end switch
     

    }
     public void drawWeekly(WeatherRecord[] wInfo) {
        int index;
        //needs to pass the weather in and specifiy index
        int x = 10;


        getChildren().clear(); // clear the pane for next graphic
        
        for (int i=0; i<7; i++)
        {
                 index = myApp.findIndexOfDay(weekly[i]);


            // add code to display the input name of the weekday contained in  wInfo parameter
           Text Text12 = new Text (x,90, (wInfo[index].getdayName()));
            getChildren().add(Text12);

            // add code to display the low  temperature contained in  wInfo parameter
           Text Text11 = new Text (x,300, Integer.toString(wInfo[index].getLowTemp()));
            getChildren().add(Text11);

            // add code to display the high temperature contained in  wInfo parameter

           Text Text10 = new Text (x,275, Integer.toString(wInfo[index].getHighTemp()));
            getChildren().add(Text10);

            switch (wInfo[index].getForecast()) // use forecast from wInfo parameter to draw appropriate graphics
            {
                case "Sun":         // Code to draw the graphics for Sun

                    Text text0 = new Text(x, 225, "Sun");
                    text0.setFill(Color.BLACK);


                    Image image0 = new Image( imgPath + "Images/Sun.png", 50.0,50.0,true,true, false);
                    ImageView imageview0 = new ImageView(image0);
                    imageview0.setX(x);
                    imageview0.setY(100.0);

                    getChildren().add(imageview0);
                    getChildren().add(text0);

                    break;

                case "Clouds":   // Code to draw the graphics for Clouds

                    Text text1 = new Text(x, 225, "Clouds");
                    text1.setFill(Color.BLACK);

                    Image image1 = new Image( imgPath + "Images/Cloud.png", 50.0,50.0,true,true, false);
                    ImageView imageview1 = new ImageView(image1);
                    imageview1.setX(x);
                    imageview1.setY(100.0);


                    //TODO: add x and y coordinates for size/placement
                    getChildren().add(imageview1);
                    getChildren().add(text1);


                    // Change the wording of the text above and add code here to display graphics for cloudy weather

                    break;

                case "Snow":   // Code to draw the graphics for Snow
                    Text text2 = new Text(x, 225, "Snow");
                    text2.setFill(Color.BLACK);  // Change the pen color
                    Image image2 = new Image( imgPath + "Images/Snow.png", 50.0,50.0,true,true, false);
                    ImageView imageview2 = new ImageView(image2);
                    imageview2.setX(x);
                    imageview2.setY(100.0);

                     getChildren().add(imageview2);
                    getChildren().add(text2);


                    // Change the wording of the text above and add code here to display graphics for snowy weather

                    break;

                case "Rain":  // Code to draw the graphics for rain
                    Text text3 = new Text(x, 225, "Rain");
                    text3.setFill(Color.BLACK);  // Change the pen color
                    Image image3 = new Image( imgPath + "Images/Rain.png", 50.0,50.0,true,true, false);
                    ImageView imageview3 = new ImageView(image3);
                    imageview3.setX(x);
                    imageview3.setY(100.0);


                     getChildren().add(imageview3);
                    getChildren().add(text3);
                    // Change the wording of the text above and add code here to display graphics for  rainy weather

                    break;

                case "Wind":   // Code to draw the graphics for wind
                    Text text4 = new Text(x, 225, "Wind");
                    Image image4 = new Image( imgPath + "Images/Wind.png", 50.0,50.0,true,true, false);
                    ImageView imageview4 = new ImageView(image4);
                    imageview4.setX(x);
                    imageview4.setY(100.0);
                    text4.setFill(Color.BLACK);  // Change the pen color


                     getChildren().add(imageview4);
                    getChildren().add(text4);
                    // Change the wording of the text above and add code here to display graphics for windy weather

                    break;

                default:

             } // end switch
          if (wInfo[index].getdayName().equals ("Wednesday"))
              x= x+85;
          else
                  x= x+65;
        }

    }
//    public void drawStats(WeatherRecord[] WStats,){
//
//        getChildren().clear(); // clear the pane for next graphic
//
//
//        // TODO: legit numbers for data.
//        // TODO: averages for highTemp and lowTemp.
//
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//                        //new PieChart.Data(WStats[1].getForecast(), Integer.valueOf(WStats[1].getLowTemp())),
//                        new PieChart.Data("Sun", 30),
//                        new PieChart.Data("Cloud", 25),
//                        new PieChart.Data("Snow", 10),
//                        new PieChart.Data("Rain", 22),
//                        new PieChart.Data("Wind", 30));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Weather Stats");
//
//        getChildren().add(chart);
//    }
    public void drawStats() {
        int[] highTempArray = new int[7];
        int[] lowTempArray = new int[7];
        String[] weatherForecast = new String[7];
        WeatherRecord weatherStats[] = new WeatherRecord[7];

        for (int i = 0; i < 7; i++) {
            highTempArray[i] = weatherStats[i].getHighTemp();
            System.out.println(highTempArray[i]);

        };


        getChildren().clear(); // clear the pane for next graphic


        // TODO: legit numbers for data.
        // TODO: averages for highTemp and lowTemp.

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        //new PieChart.Data(WStats[1].getForecast(), Integer.valueOf(WStats[1].getLowTemp())),
                        new PieChart.Data("Sun", 30),
                        new PieChart.Data("Cloud", 25),
                        new PieChart.Data("Snow", 10),
                        new PieChart.Data("Rain", 22),
                        new PieChart.Data("Wind", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Weather Stats");

        getChildren().add(chart);
    }
}
