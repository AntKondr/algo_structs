import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyTree<T extends Comparable<T>>{

    private class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;

        // конструктор
        public Node(T value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;

    // конструктор
    public MyTree(){
        root = null;
    }

    public boolean add(T value){
        if (root == null) {
            root = new Node<>(value);
            return true;
        }
        return addNode(root, value);
    }

    private boolean addNode(Node<T> current, T value){
        if (value.compareTo(current.value) == 0){
            return false;
        }
        else if (value.compareTo(current.value) < 0){
            // Вставялем в левое поддерево
            if (current.left == null){
                current.left = new Node<>(value);
                return true;
            }
            else{
                return addNode(current.left, value);
            }
        }
        else if (value.compareTo(current.value) > 0){
            // Вставляем в правое поддерево
            if (current.right == null){
                current.right = new Node<>(value);
                return true;
            }
            else{
                return addNode(current.right, value);
            }
        }
        // тут return потому что в 45 строчке else if, вместо else
        // (для себя так сделал, чтобы самого себя не запутать, что там проверяется :) )
        return true;
    }

    public boolean contains(T value){
        return findNode(root, value) != null;
    }

    private Node<T> findNode(Node<T> currentNode, T value){
        if (currentNode == null){
            return null;
        }
        // найти узел currentNode в дереве, значение которого равно value
        else if (value.compareTo(currentNode.value) == 0){
            return currentNode;
        }
        else if (value.compareTo(currentNode.value) < 0){
            return findNode(currentNode.left, value);
        }
        else if (value.compareTo(currentNode.value) > 0){
            return findNode(currentNode.right, value);
        }
        // тут return потому что в 75 строчке else if, вместо else
        // (для себя так сделал, чтобы самого себя не запутать, что там проверяется :) )
        return currentNode;
    }

    public void remove(T value){
        root = removeNode(root, value);
    }

    private Node<T> removeNode(Node<T> current, T value){
        if (current == null){
            return null;
        }
        if (value.compareTo(current.value) < 0){
            // нужно удалить в левом поддереве
            current.left = removeNode(current.left, value);
            return current;
        }
        else if (value.compareTo(current.value) > 0){
            // нужно удалить в правом поддереве
            current.right = removeNode(current.right, value);
            return current;
        }
        // Нужно удалить узел current
        // Есть 3 случая:
        // 1. Дочерних узлов нет
        if (current.left == null && current.right == null){
            return null;
        }
        // 2. Есть только один дочерний узел
        if (current.left == null && current.right != null){
            return current.right;
        }
        if (current.left != null && current.right == null){
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент справа
        Node<T> smallestNodeOnTheRight = findFirst(current.right);
        T smallestValueOnTheRight = smallestNodeOnTheRight.value;
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public T findFirst(){
        if (root == null){
            throw new IllegalStateException("tree is emty");
        }
        return findFirst(root).value;
    }

    private Node<T> findFirst(Node<T> current){
        if (current.left == null){
            return current;
        }
        return findFirst(current.left);
    }

    public List<T> dfs(){
        if (root == null){
            return List.of();
        }
        List<T> list = new ArrayList<T>();
        dfs(root, list);
        return list;
    }

    private void dfs(Node<T> current, List<T> result){
        // In-order
        if (current.left != null){
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null){
            dfs(current.right, result);
        }
    }

    public List<T> bfs(){
        if (root == null){
            return List.of();
        }

        List<T> result = new ArrayList<T>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node<T> next = queue.poll();
            result.add(next.value);

            if (next.left != null){
                queue.add(next.left);
            }
            if (next.right != null){
                queue.add(next.right);
            }
        }
        return result;
    }
}