package Day12;

import Common.Day;

import java.util.*;

public abstract class GenericSolution extends Day {
    private List<Cave> caves = new ArrayList<>();
    private Map<Cave, Set<Cave>> connections;

    protected GenericSolution(){
        Cave[][] connectionsAsArray = this.parseAsStringStream()
                .map(s -> s.split("-"))
                .map(caveNames -> {
                    Cave[] connection = new Cave[2];
                    connection[0] = caveFactory(caveNames[0]);
                    connection[1] = caveFactory(caveNames[1]);
                    return connection;
                })
                .toArray(Cave[][]::new);

        this.connections = findCaveConnections(connectionsAsArray);
    }

    private static Map<Cave, Set<Cave>> findCaveConnections(Cave[][] connections){
        Map<Cave, Set<Cave>> caveConnections = new HashMap<>();

        for(Cave[] connection : connections){
            for(int i = 0; i < 2; i++){
                if(caveConnections.containsKey(connection[i])){
                    caveConnections.get(connection[i]).add(connection[-(i - 1)]);
                } else {
                    Set<Cave> connectionSet = new HashSet<>();
                    connectionSet.add(connection[-(i - 1)]);
                    caveConnections.put(connection[i], connectionSet);
                }
            }
        }

        return caveConnections;
    }

    @Override
    protected String getInputFile(){
        return "src/main/java/Day12/input.txt";
    }

    @Override
    public Number run(){
        return countNumberOfValidRoutes(new Route(caveFactory("end")));
    }

    private int countNumberOfValidRoutes(Route route){
        if(route.isComplete()){
            return 1;
        }

        int total = 0;

        for(Cave cave : this.connections.get(route.getHead())){
            Route candidateRoute = new Route(cave, route);

            if(routeIsValid(candidateRoute)){
                total += countNumberOfValidRoutes(candidateRoute);
            }
        }

        return total;
    }

    private Cave caveFactory(String caveName){
        for(Cave cave : this.caves){
            if(cave.getName().equals(caveName)){
                return cave;
            }
        }

        Cave newCave = new Cave(caveName);
        caves.add(newCave);
        return newCave;
    }

    protected static class Cave {
        private boolean isBig;
        private String name;

        public Cave(String name){
            this.name = name;
            this.isBig = !this.name.toLowerCase().equals(this.name);
        }

        public boolean isBig(){
            return isBig;
        }

        public String getName(){
            return name;
        }

        @Override
        public String toString(){
            return this.getName();
        }
    }

    protected static class Route implements Iterable<Cave> {
        private boolean isComplete;

        // backwards, first element should always be the end cave
        private List<Cave> route = new ArrayList<>();

        public Route(Cave cave){
            route.add(cave);
            this.isComplete = false;
        }

        // adds a cave to the start of the route
        public Route(Cave newCave, Route oldRoute) {
            this.route.addAll(oldRoute.route);
            this.route.add(newCave);
            this.isComplete = newCave.getName().equals("start");
        }

        @Override
        public int hashCode(){
            StringBuilder routeString = new StringBuilder();
            for(Cave cave : this.route){
                routeString.append(cave.toString()).append(",");
            }

            return Objects.hash(routeString.toString());
        }

        public boolean isComplete(){
            return isComplete;
        }

        public Cave getHead(){
            return this.route.get(this.route.size() - 1);
        }

        @Override
        public Iterator<Cave> iterator(){
            return this.route.iterator();
        }
    }

    protected abstract boolean routeIsValid(Route route);
}
