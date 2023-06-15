import java.util.ArrayList;
import java.util.Collection;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> qe = parser.read("data/nov20quakedata.atom");

        EarthQuakeClient client = new EarthQuakeClient();
        EarthQuakeClient2 client2 = new EarthQuakeClient2();
        ClosestQuakes closeQuakes = new ClosestQuakes();
        LargestQuakes largeQuakes = new LargestQuakes();
        
        //client.quakesByPhrase();
        //client.quakesOfDepth();
       //closeQuakes.findClosestQuakes();
       largeQuakes.getLargest(qe, 5);
        System.out.println("**********");
                
        System.out.println("*******************************");
        //client2.quakesWithFilter();

        System.out.println("**********");
        

        
    }
}
