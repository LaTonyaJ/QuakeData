
public class LocationFilter implements Filter {
    
    private Location location;
    private Integer maxDistance;
    
    public LocationFilter(Location loc, Integer max){
        location = loc;
        maxDistance = max;
    }

    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(location) < maxDistance);
    }
}
