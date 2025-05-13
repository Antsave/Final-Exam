public class Baby implements Comparable<Baby>{
    // Intialize Variables
    private String name;
    private int rank;
    private int number;
    // Constructor 
    public Baby(String name, int rank, int number){
        this.name = name;
        this.rank = rank;
        this.number = number;
    }
    public String getName(){
        return name;
    }
    public int getRank(){
        return rank;
    }
    public int getNumber(){
        return number;
    }
    @Override
    public int compareTo(Baby other) {
        return this.name.compareToIgnoreCase(other.name); // Sort alphabetically
    }
    @Override
    public String toString() {
        return name + ": " + rank + ": " + number;
    }


}