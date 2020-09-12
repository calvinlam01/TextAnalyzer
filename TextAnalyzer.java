/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the TextAnalyzer class which takes the directory of a folder as an input, and compares all the passages inside.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class TextAnalyzer {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the directory of a folder of text files: ");
        String directoryPath = scan.nextLine();
        File[] directoryOfFiles = new File(directoryPath).listFiles();
        int n = 0;
        ArrayList<Passage> pList = new ArrayList<>();

        for (File i : directoryOfFiles) {
            Passage temp = new Passage(i.getName(), i);
            pList.add(temp);
        }
        for (int i = 0; i < pList.size(); i++) {
            System.out.println("\nComparing to passage " + pList.get(i).getTitle());
            System.out.println("-----------------------------------------------------------------------------");
            for (int j = 0; j < pList.size(); j++)
                if (i != j) {
                    double test = Passage.cosineSimilarity(pList.get(i), pList.get(j));
                    System.out.println("Similiarity to passage " + pList.get(j).getTitle() + " : " + test);
                }

        }
        for (int i = 0; i < pList.size()-1; i++) {
            for (int j = 0; j < pList.size(); j++)
                if (i != j) {
                    double test = Passage.cosineSimilarity(pList.get(i), pList.get(j));
                    if (test > 0.55 && i<j)
                        System.out.println("Passage " + pList.get(i).getTitle() + " and Passage " + pList.get(j).getTitle() + " may share the same author");
                }

        }
    }
}
