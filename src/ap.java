import java.util.*;

import javax.swing.JEditorPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.*;


public class ap {

    public static double[] getCoordinates(String city){
    double[] coordinate = new double[2];
       try {
        URL url = new URL(String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=67acb5770cc7748ef770975045fb3d0a", city)) ;
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        System.out.println(httpURLConnection.getResponseCode());
        Scanner sc = new Scanner(url.openStream());
        String input ="";
        while(sc.hasNext())
        {
            input= input + sc.nextLine();
        }
        // System.out.println(input);
        JSONParser jsp = new JSONParser();
     
        JSONArray arr=  (JSONArray)jsp.parse(input);
        JSONObject obj = (JSONObject)arr.get(0);
        coordinate[0]=(double)obj.get("lon");
        coordinate[1]=(double)obj.get("lat");
        //System.out.println(arr.get(0));
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return coordinate;
    

    }
    public static void getweather(double[] coordinate)
    {
        try {
            URL url = new URL(String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=67acb5770cc7748ef770975045fb3d0a", coordinate[1],coordinate[0])) ;
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            System.out.println(httpURLConnection.getResponseCode());
            Scanner sc = new Scanner(url.openStream());
            String input ="";
            while(sc.hasNext())
            {
                input= input + sc.nextLine();
            }
            // System.out.println(input);
            JSONParser js = new JSONParser();
            JSONObject obj = (JSONObject)js.parse(input);
            System.out.println(obj.get("main"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        System.out.println("enter city name");
        String city = sc.nextLine();
    double[] coordinate=getCoordinates(city);
    System.out.println(coordinate[0]+","+coordinate[1]);
    getweather(coordinate);
    }
}               
