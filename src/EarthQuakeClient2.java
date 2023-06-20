
import java.util.*;
//import edu.duke.*;


public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(quakeData != null && f != null){
            for(QuakeEntry qe : quakeData) { 
                if (f.satisfies(qe)) { 
                    answer.add(qe); 
                } 
            } 
        }
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        Filter d = new DepthFilter(-55000, -20000);
        Filter m = new MagnitudeFilter(3.5, 4.5);
        //Location denver = new Location(39.7392, -104.9903);
        
        //Filter dist = new LocationFilter(denver, 1000000);
        //Filter f = new PhraseFilter("end", "a");
        
        ArrayList<QuakeEntry> m7  = filter(list, d);
        ArrayList<QuakeEntry> m8 = filter(m7, m); 
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("# of quakes: " + m8.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Depth,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getDepth(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        String source = "./data/nov20quakedata.atom";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000, -30000));
        maf.addFilter(new PhraseFilter("any", "o"));

        answer = filter(list, maf);

        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
        System.out.println("# of quakes: " + answer.size());
    }

    public void testMatchAllFilter2(){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        String source = "./data/nov20quakedata.atom";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);

        Location denmark = new Location(55.7308, 9.1153);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new LocationFilter(denmark, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));

        answer = filter(list, maf);

        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
        System.out.println("# of quakes: " + answer.size());
    }

}
