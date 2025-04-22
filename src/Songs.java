import java.sql.Array;
import java.util.ArrayList;

public class Songs {
    private ArrayList<double[]> song;


    public Songs(String song) {
        if (song == "trapQueen") {
            trapQueen();
        }
    }

    public void trapQueen() {
        this.song = new ArrayList<double[]>();

        double startOffset = -0.4;
        double noteOffset = -0.065;

        double BPMstep = 60.0 / 148;
        double startingDing = 0.555 + noteOffset;
        double startingDing2 = 0.755 + noteOffset;


        song.add(new double[]{3.6 + startOffset, 0});
        song.add(new double[]{0, 3});
        song.add(new double[]{startingDing, 2});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{startingDing2, 3});
        song.add(new double[]{startingDing, 3});
        song.add(new double[]{startingDing,1});
        song.add(new double[]{startingDing,2});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{0, 3});
        song.add(new double[]{startingDing2,2});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{startingDing,1});
        song.add(new double[]{startingDing,1});
        song.add(new double[]{startingDing, 1});
        song.add(new double[]{0, 2});
        song.add(new double[]{startingDing2,3});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{startingDing,2});
        song.add(new double[]{startingDing,1});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{0, 2});
        song.add(new double[]{startingDing2,3});
        song.add(new double[]{startingDing, 2});
        song.add(new double[]{startingDing,1});
        song.add(new double[]{startingDing,2});
        song.add(new double[]{startingDing, 0});
        song.add(new double[]{startingDing2,3});
    }

    public double[] get(int index) {
        return song.get(index);
    }

    public int getSize() {
        return song.size();
    }
}
