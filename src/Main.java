import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String inputFile = "input.txt";
    private static final String outputFile = "output.txt";

    public static void main(String[] args) throws IOException {

        StringBuilder itog = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String [] nq = reader.readLine().split(" ");
        List<Integer> points = Arrays.stream(reader.readLine().split(" ")).map(l->Integer.parseInt(l)).collect(Collectors.toList());
        Integer n = Integer.parseInt(nq[0]);
        //Integer q = Integer.parseInt(nq[1]);

        Point work = new Point(1,n, "k", 0);

        for (Integer point:points){

            /*Point v = work.getNum(point);
            Point p = work.getNum(v.parent);
            Point p_buff;

            if (v.role.equals("r")){
                p_buff = p.left;
                p.left = v.left;
                v.left = p_buff;
                v.num = p.num;
                v.parent = point;
                if( v.right != null ) v.right.parent = v.num;
                p.num = point;
            }
            else if (v.role.equals("l")){
                p_buff = p.right;
                p.right = v.right;
                v.right = p_buff;
                v.num = p.num;
                v.parent = point;
                if( v.left != null )v.left.parent = v.num;
                p.num = point;
            }*/
            work.change(point);
            System.out.println(work);
        }

        FileWriter file = new FileWriter(outputFile);
        file.write(work.toString());
        file.close();
    }
}

class Point {
    String role;
    Integer parent;
    Integer num;
    Point left;
    Point right;
    Point (Integer num, Integer max, String role, Integer parent){
        this.role = role;
        this.parent = parent;
        if (2*num < max){
            this.num = num;
            this.left = new Point(2*num, max, "l", num);
            this.right = new Point (2*num+1, max,"r", num);
        }
        else if(2*num == max) {
            this.num = num;
            this.left = new Point(2*num, max,"l", num);
            this.right = null;
        }
        else if (num <= max) {
            this.num = num;
            this.left = null;
            this.right = null;
        }
    }

    Point getNum (Integer i){
        if (num == i) return this;
        else if (left!=null && left.getNum(i)!=null) return left.getNum(i);
        else if (right!=null && right.getNum(i)!=null) return right.getNum(i);
        else return null;
    }

    @Override
    public String toString() {
        String reslt = "";
        if (left!=null){
            reslt+=left.toString()+" ";
        }
        if (num!=null){
            reslt+=num;
        }
        if (right!=null){
            reslt+=" " + right.toString();
        }
        return reslt;
    }

    void change (Integer point){
        Point v = this.getNum(point);
        Point p = this.getNum(v.parent);
        Point p_buff;

        if (v.role.equals("r")){
            p_buff = p.left;
            p.left = v.left;
            v.left = p_buff;
            v.num = p.num;
            v.parent = point;
            if( v.right != null ) v.right.parent = v.num;
            p.num = point;
        }
        else if (v.role.equals("l")){
            p_buff = p.right;
            p.right = v.right;
            v.right = p_buff;
            v.num = p.num;
            v.parent = point;
            if( v.left != null )v.left.parent = v.num;
            p.num = point;
        }
    }
}