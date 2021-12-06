package Day6;

public class Population {

    private long[] fishAtEachAge;

    public Population(long[] initialFishState){
        assert initialFishState.length == 9;

        this.fishAtEachAge = initialFishState;
    }

    public void simulate(int nDays){
        for(int i = 0; i < nDays; i++){
            simulate();
        }
    }

    public void simulate(){
        long[] newState = new long[9];

        System.arraycopy(this.fishAtEachAge, 1, newState, 0, 8);

        newState[6] += this.fishAtEachAge[0];
        newState[8] += this.fishAtEachAge[0];

        this.fishAtEachAge = newState;
    }

    public long getNumberOfFish(){
        long fish = 0;

        for(long n : this.fishAtEachAge){
            fish += n;
        }

        return fish;
    }
}
