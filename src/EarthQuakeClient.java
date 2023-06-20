
import java.util.*;
//import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() >= magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    /**
     * @param quakeData
     * @param minDepth
     * @param maxDepth
     * @return
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe: quakeData){
            if(qe.getDepth() < maxDepth && qe.getDepth() > minDepth){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry qe: quakeData){
            if(where.equals("start")){
                if(qe.getInfo().startsWith(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("end")){
                if(qe.getInfo().endsWith(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("any")){
                if(qe.getInfo().contains(phrase)){
                    answer.add(qe);
                }
            }
            
        }
        return answer;
    }
     /* 
    public ArrayList<QuakeEntry> findClosestQuakes(ArrayList<QuakeEntry> quakeData, Location current, Integer howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        ArrayList<QuakeEntry> entries = filterByDistanceFrom(quakeData, 10000, current);
        for(int i = 0; i < howMany - 1; i++){
            for(QuakeEntry qe: entries){
                answer.add(qe);
            }
        }
        return answer;
    }*/
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%s\n",
			                //qe.getLocation().getLatitude(),
			                //qe.getLocation().getLongitude(),
			                //qe.getMagnitude(),
                            //qe.getDepth(),
			                qe.getInfo());
	    }
	}
	
	public void bigQuakes() {
	    EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "./data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.00);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
	}
	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# of quakes read: " + list.size());

        ArrayList<QuakeEntry> depthList = filterByDepth(list, -4000.00, -2000.00);
        for(QuakeEntry qe: depthList){
            System.out.println(qe);
        }
        System.out.println(depthList.size() + " quakes returned");
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# of quakes read: " + list.size());

        ArrayList<QuakeEntry> byPhrase = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe: byPhrase){
            System.out.println(qe);
        }
        System.out.println("Found " + byPhrase.size() + " quakes that match");
    }
    /* 
    public void findClosest(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedata.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# of quakes read: " + list.size());

        Location city = new Location(-6.211, 106.845);
        ArrayList<QuakeEntry> closest = findClosestQuakes(list, city, 3);
        for(QuakeEntry qe: closest){
            System.out.println(qe);
        }
    }*/
}
