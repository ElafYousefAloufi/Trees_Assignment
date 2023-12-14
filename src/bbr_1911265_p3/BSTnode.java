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

public class BSTnode {

    private String word;
    private int count;
    private BSTnode left;
    private BSTnode right;

    public BSTnode(String word) {
        this.word = word;
        this.count = 1;
        left = right = null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public BSTnode getLeft() {
        return left;
    }

    public void setLeft(BSTnode left) {
        this.left = left;
    }

    public BSTnode getRight() {
        return right;
    }

    public void setRight(BSTnode right) {
        this.right = right;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        this.count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
