import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s18982 {
    public static void main(String[] args) {

        File file = new File(args[0]);
        int k=0;
        MyList list = new MyList();
        try {
            Scanner scanner =  new Scanner(file);
            k = scanner.nextInt();
            while (scanner.hasNextInt()){
                MyList.insert(list, scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int p = 0;
        while(k != 0){
            if(MyList.getElement(list,p)%2==1){
                MyList.insertInto(list,p+1,MyList.getElement(list,p)-1);
                p+=MyList.getElement(list,p);
                if(p>MyList.getSize(list)-1){
                    if (MyList.getSize(list)!=0) {
                        p = p % (MyList.getSize(list));
                    }else
                        return;
                }

            }else{
                int x;
                if(p!=MyList.getSize(list)-1) {

                    x = MyList.getElement(list, p + 1);
                    MyList.delete(list,p+1);
                }else {
                    x = MyList.getElement(list,0);
                    MyList.delete(list,0);
                    x--;
                }

                p+=x;

                if(p>MyList.getSize(list)-1) {
                    if (MyList.getSize(list)!=0) {
                        p = p % (MyList.getSize(list));
                    }else
                        return;
                }

            }
            k--;
        }

        for (int i = 0; i<MyList.getSize(list); i++){
            System.out.print(MyList.getElement(list,p)+" ");
            p++;
            p = p % (MyList.getSize(list));
        }

    }
}
class MyList{
    Node head;
    static int size=0;

    static class Node{
        int val;
        Node next;

        Node(int x){
            val = x;
            next = null;
        }

        public Node() {
        }
    }

    public static MyList insert(MyList myList, int x){
        Node node = new Node(x);
        node.next = null;

        if(myList.head == null){
            myList.head = node;
        }else {
            Node last = myList.head;
            while (last.next != null){
                last = last.next;
            }

            last.next = node;
        }
        size++;
        return myList;
    }

    public static void print(MyList myList){
        Node node = myList.head;
        while (node != null){
            System.out.print(node.val +" ");

            node = node.next;
        }
    }

    public static MyList delete(MyList myList, int index) throws IndexOutOfBoundsException {
        Node node = myList.head;
        Node pre = null;

        if (index == 0 && node != null) {
            myList.head = node.next;
            size--;
            return myList;
        }
        int counter = 0;
        while (node != null) {
            if (counter == index) {
                pre.next = node.next;
                size--;
                break;
            } else {
                pre = node;
                node = node.next;
                counter++;
            }

            if(node == null)
                throw new IndexOutOfBoundsException();

        }
        return myList;
    }

    public static int getSize(MyList myList){
        return MyList.size;
    }

    public static int getElement(MyList myList, int index){
        Node node = myList.head;
        int counter = 0;
        while(counter != index){
            node = node.next;
            counter++;
        }
        return node.val;
    }

    public static MyList insertInto(MyList myList,int index, int value){

        Node node = myList.head;
        Node pre = null;
        int size = MyList.getSize(myList);
        if(index == 0){
            Node newNode = new Node(value);
            pre = newNode;
            pre.next=node;
            myList.head=pre;
            return myList;
        }
        if(index<=size)
        for(int i = 0; i<=index; i++){
            if(i != 0) {
                if (i == index && i !=size) {
                    Node node1 = new Node(value);
                    node1.next = node;
                    pre.next = node1;

                }
                if (i == index && i == size) {
                   MyList.insert(myList,value);
                    MyList.size--;
                }
            }
            if(node.next != null) {
                pre = node;
                node = node.next;
            }

        }

        MyList.size++;
        return myList;
    }


}

