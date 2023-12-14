/*
 * Name: Ali Muaffag Abumansour
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

public class BST {

    private BSTnode root;
    private int countWord;

    public BST() {
        this.root = null;
        this.countWord = 0;
    }

    // adding each word to the binary search tree
    public void addWord(String word) {
        BSTnode node = new BSTnode(word);

        root = insertWord(root, node);
        countWord++;
    }

    // adding a word in a tree
    private BSTnode insertWord(BSTnode root, BSTnode newWord) {
        if (root == null) {
            return newWord;
        }

        if (newWord.getWord().compareTo(root.getWord()) <= 0) {
            root.setLeft(insertWord(root.getLeft(), newWord));
        } else {
            root.setRight(insertWord(root.getRight(), newWord));

        }

        return root;
    }

    // finding a specific word
    public BSTnode searchWord(String word) {
        return searchWord(root, word);
    }

    // finding a specific word
    private BSTnode searchWord(BSTnode root, String word) {
        if (root == null) {
            return null;
        }
        // if greater than 0, move left
        if (0 < word.compareTo(root.getWord())) {
            return searchWord(root.getRight(), word);

        }

        // if less than 0, move left
        if (0 > word.compareTo(root.getWord())) {
            return searchWord(root.getLeft(), word);
        }

        // otherwise move right
        return root;

    }

    // return the most commont word
    public BSTnode commonWord() {
        return commonWord(root, null);
    }

    // return the most commont word
    private BSTnode commonWord(BSTnode root, BSTnode temp) {
        if (root == null) {
            return temp;
        }

        if ((temp == null || root.getCount() > temp.getCount())) {
            temp = root;
        } else {
            temp = temp;
        }

        BSTnode leftMax = commonWord(root.getLeft(), temp);
        BSTnode rightMax = commonWord(root.getRight(), temp);

        if (leftMax.getCount() > temp.getCount()) {
            temp = leftMax;
        } else {
            temp = temp;
        }

        if (rightMax.getCount() > temp.getCount()) {
            temp = rightMax;
        } else {
            temp = temp;
        }

        return temp;
    }

    // return frequency of a specific word
    public double frequency(String word) {
        return frequency(root, word);
    }

    // return the frequency of a specific word 
    private double frequency(BSTnode root, String word) {
        BSTnode node = searchWord(root, word);

        if (node != null) {

            return (double) node.getCount() / numberWords(root);
        }

        return 0;
    }

    // removing a word from tree
    public void remove(String word) {
        root = remove(root, word);
    }

    // removing a word from tree
    private BSTnode remove(BSTnode root, String word) {
        BSTnode node2delete, newnode2delete, parent;
        int freqValue;
        String wordValue;

        // find the node we want to delete
        node2delete = searchWord(root, word);
        if (node2delete == null) {
            System.out.println("\tNode not found");
            return root;
        }

        // find the parent of the node we want to delete.
        parent = findParent(root, node2delete);

        // perform Deletion based on three possibilities
        if ((node2delete.getLeft() == null && node2delete.getRight() == null)) {

            if (parent == null) {
                return null;
            }

            // delete node if it is a left or right child
            if (word.compareTo(parent.getWord()) >= 0) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }

            // return the root of the tree (in case the root got updated)
            return root;
        }

        if ((node2delete.getLeft() != null && node2delete.getRight() == null)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // if node2delete is not the root,
            if (word.compareTo(parent.getWord()) >= 0) {
                parent.setRight(parent.getRight().getLeft());
            } else {
                parent.setLeft(parent.getLeft().getLeft());
            }

            // return the root of the tree (in case the root got updated)
            return root;
        }

        // node2delete has only a right child
        if ((node2delete.getLeft() == null && node2delete.getRight() != null)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // if node2delete is not the root         
            if (word.compareTo(parent.getWord()) >= 0) {
                parent.setRight(parent.getRight().getRight());
            } else {
                parent.setLeft(parent.getLeft().getRight());
            }

            // return the root of the tree (in case the root got updated)
            return root;
        }

        // if node2delete has TWO children.
        newnode2delete = minNode(node2delete.getRight());

        // now we need to temporarily save the data value(s) from this node
        freqValue = newnode2delete.getCount();
        wordValue = newnode2delete.getWord();

        root = remove(root, wordValue);

        node2delete.setWord(wordValue);
        node2delete.setCount(freqValue);

        return root;
    }

    // print the words 
    public String printWords() {
        return printWords(root);
    }

    // print the word 
    private String printWords(BSTnode root) {
        if (root == null) {
            return " ";
        }

        return printWords(root.getLeft()) + "     " + root.getWord() + " with a count: " + root.getCount() + "\n" + printWords(root.getRight());
    }

    // return the words count 
    public int getCountWord() {
        return countWord;
    }

    // find the parent 
    private BSTnode findParent(BSTnode root, BSTnode word) {

        // root is the parent of word
        if (root.getLeft() == word || root.getRight() == word) {
            return root;
        }

        if (root == null || root == word) {
            return null;
        }

        if (word.getWord().compareTo(root.getWord()) <= 0) {
            if (word.getWord().compareTo(root.getWord()) >= 0) {
            } else {
                return findParent(root.getLeft(), word);
            }
        } else {
            return findParent(root.getRight(), word);
        }

        return null;
    }

    // words number count
    private int numberWords(BSTnode root) {
        if (root == null) {
            return 0;
        }

        return numberWords(root.getRight()) + root.getCount() + numberWords(root.getLeft());
    }

    // return the minimum node
    public BSTnode minNode(BSTnode root) {
        if (root == null) {
            return null;
        } else if (root.getLeft() == null) {
            return root;
        } else {
            return minNode(root.getLeft());
        }
    }

}
