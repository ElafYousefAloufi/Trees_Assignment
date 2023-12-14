/*
 * Name: Elaf Yousef Aloufi
 * ID: 1911265
 * Email: Ealoufi0015@stu.kau.edu.sa
 * Course name: CPCS204
 * Section: BBR
 * Dr.Huda Aljaloud
 * Date: November 19th, 2020
 * Assignment#03: Trees
 */
package bbr_1911265_p3;

import java.util.Scanner;

public class BBR_1911265_P3 {

    static Scanner In;
    static BST Tree_word;

    // reads a text and creates a binary search tree from the inputted text
    public static void CreateBST(String text) {
        Tree_word = new BST();

        String[] words = text.trim().replaceAll("\\p{Punct}", "").split(" ");
        int Wlength = 0;
        while (Wlength < words.length) {

            if (!words[Wlength].equals(null)) {

                AddWord(words[Wlength].trim().toLowerCase());
            }
            Wlength++;
        }
    }

    // add a word to the binary search tree
    public static void AddWord(String word) {
        if (!IsValidWord(word)) {
            return;
        }

        if (!IsWordAStopgapWord(word)) {
            return;
        }

        BSTnode node = Tree_word.searchWord(word);
        if (node == null) {
            Tree_word.addWord(word);
            return;
        }

        node.incCount();
    }

    // checks to see if the word is a valid word or not
    public static boolean IsValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }

    //  checks to see if a given word is in the stopgap list
    private static boolean IsWordAStopgapWord(String word) {
        String[] stopWords = "a, an, and, are, as, at, be, by, for, from, has, he, in, is, it, its, of, on, that, the, to, she, was, where, will, with, so".split(", ");

        int Wlength = 0;
        while (Wlength < stopWords.length) {

            if (stopWords[Wlength].equals(word)) {
                return false;
            }
            Wlength++;
        }
        return true;
    }

    // returns the word with the highest count in the binary search tree
    public static void MostCommonWord() {
        BSTnode node = Tree_word.commonWord();
        System.out.println("The Most common word: " + node.getWord() + " with a count: " + node.getCount());
    }

    //  returns the number of words (nodes) in the binary search tree
    public static void WordCount() {

        int number = Tree_word.getCountWord();
        System.out.println("The Number of nodes in the tree: " + number);
    }

    // returns the value associated with this word divided by the total word count
    public static void GetFrequencyOf() {

        System.out.print("\nPlease Enter a word to count its Frequency: ");

        String words = In.nextLine().trim();
        double fr = Tree_word.frequency(words);

        System.out.print("The frequency of the word " + words + " is " + fr + "\n");

    }

    // removes the node that contains the given word
    public static void RemoveWord() {

        System.out.print("\nPlease Enter a word to remove: ");
        String DeleteWord = In.nextLine().trim();

        Tree_word.remove(DeleteWord);
        System.out.print("     Done The word is deleted\n");
    }

    // prints all words in order
    public static void Worder() {
        System.out.println("The Words in alphabetical order are:");
        System.out.print(Tree_word.printWords());
    }

    // prints all words in the BST in an alphabetical order along with printing the count of each of these words
    public static void PrintText() {
        
        WordCount();
        MostCommonWord();
        Worder();
    }

    // main class 
    public static void main(String[] args) {
        // create a scanner objects
        In = new Scanner(System.in);
        System.out.print("Please Enter Text: ");
        String str = In.nextLine().trim();

        // invoke the methods
        CreateBST(str);
        PrintText();
        GetFrequencyOf();
        RemoveWord();
        PrintText();

    }

}
