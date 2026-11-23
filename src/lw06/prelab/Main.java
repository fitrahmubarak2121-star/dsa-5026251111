package lw06.prelab;

import java.util.Scanner;

public class Main {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    static boolean search(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }
        if (target < root.data) {
            return search(root.left, target);
        }
        return search(root.right, target);
    }

    static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        Node root = null;
        String[] values = sc.nextLine().split(" ");
        for (int i = 0; i < values.length; i++) {
            if (!values[i].equals("")) {
                root = insert(root, Integer.parseInt(values[i]));
            }
        }

        int target = sc.nextInt();
        sc.close();

        System.out.print("Preorder : ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder : ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root);
        System.out.println();

        System.out.println("Search " + target + " : " + search(root, target));
    }
}