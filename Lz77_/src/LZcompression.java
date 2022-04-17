
/*
Mohamed Ahmed : 20190415
Mohamed Atef  : 20190454
*/
import java.util.ArrayList;
import java.util.Scanner;
class tag {
    public int position;
    public int length;
    public char symbol;
    public tag(int position, int length, char symbol) {
        this.position = position;
        this.length = length;
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return "<" + this.position + ", " + this.length + ", " + this.symbol + ">";
    }
}
public class LZcompression {
    public static void main(String[] args) {
        ArrayList<tag> tags = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Word to compress: ");//ABAABABAABBBBBBBBBBBBA
        String word = input.nextLine();
        tag t1;
        String tmp1 = "";
        String searchChar = "";
        int pos;
        int counter;
        int len = 0;
        char symbol;
        for (int i = 0; i < word.length(); i++) {
            tmp1 = word.substring(0, i);
            searchChar = Character.toString(word.charAt(i));
            pos = 0;
            counter = 0;
            for (int j = i; j < word.length(); j++) {
                if (tmp1.contains(searchChar) && j != word.length() - 1) {
                    pos = i - tmp1.lastIndexOf(searchChar);
                    searchChar += Character.toString(word.charAt(j + 1));
                    counter++;
                } else {
                    len = counter;
                    symbol = word.charAt(j);
                    t1 = new tag(pos, len, symbol);
                    tags.add(t1);
                    i = j;
                    break;
                }
            }
        }
        for (int i = 0; i < tags.size(); i++) {
            System.out.print(tags.get(i)+" ");
        }
        String decompress = "";
        for (int i = 0; i < tags.size(); ++i) {
            if (tags.get(i).position == 0) {
                decompress = decompress + tags.get(i).symbol;
            } else {
                int z = decompress.length() - tags.get(i).position;
                for (int j = 0; j < tags.get(i).length; j++) {
                    decompress = decompress + decompress.charAt(z);
                    z = z + 1;
                }
                decompress = decompress + tags.get(i).symbol;
            }
        }
        System.out.println("\nDecompression result: "+decompress);
    }
}