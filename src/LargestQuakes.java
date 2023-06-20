import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./data/nov20quakedatasmall.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        for(QuakeEntry qe: list){
            //System.out.println(qe);
        }
        System.out.println("# of quakes: " + list.size());

    }

    public Integer indexOfLargest(ArrayList<QuakeEntry> data){

        QuakeEntry max = data.get(0);

        for(int i = 0; i < data.size(); i++){

            if(max.getMagnitude() < data.get(i).getMagnitude()){
                max = data.get(i);
            }
        }
        int idx = data.indexOf(max);
        //System.out.println(idx + " " + max);
        return idx;
    }

    public void getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        
        for(int i = 0; i < howMany; i++){
            int largest = indexOfLargest(copy);
            answer.add(copy.get(largest));
            copy.remove(largest);
        }
        for(QuakeEntry qe: answer){
            System.out.println(qe + "\n");
        }
        System.out.println("# of quakes: " + answer.size());
    }
}
