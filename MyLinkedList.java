public class MyLinkedList<T>{

    private class Node<T>{
        T value;
        Node<T> next;

        public Node(T value){
            this.value = value;
            this.next = null;
        }
    }

    // Реализовать в классе MyLinkedList следующие методы
    // 1. public int size() - получение размера списка, проитерировався по всей структуре              !done!
    // 1.1 * Можно завести переменную size, поддерживать ее и использовать ее.                         !done!
    // 2. public boolean contains(int value) - проверка наличия элемента по значению                   !done!
    // 3. public int popLast() - удаление последнего элемента. Если список пустой - то ошибка          !done!
    // 4. * Переделать все int значения на дженерик T, чтобы можно было хранить значения любых типов   !done!
    // 5. * public MyLinkedList reversed() - создать НОВЫЙ список, порядок в котором обратный текущему !done!

    private Node<T> head;
    public int size;

    // конструктор
    public MyLinkedList(){
        head = null;
        size = 0;
    }

    // метод возвращает длину списка (проход циклом)
    public int cycleSize(){
        if (head == null){
            return 0;
        }
        Node<T> cursor = head;
        int sizeOfList = 1;
        while (cursor.next != null){
            cursor = cursor.next;
            sizeOfList++;
        }
        return sizeOfList;
    }

    // метод возвращает длину списка
    public int getSize(){
        return size;
    }

    // метод возвращает последнюю ноду (не удаляет из списка), null - если список пуст
    private Node<T> findLastNode(){
        if (size == 0){
            return null;
        }
        Node<T> cursor = head;
        while (cursor.next != null){
            cursor = cursor.next;
        }
        return cursor;
    }

    // метод добавляет value в начало списка
    public void addBegin(T value){
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // метод добавляет value в конец списка
    public void addEnd(T value){
        if (size == 0){
            head = new Node<>(value);
        }
        else{
            Node<T> last = findLastNode();
            last.next = new Node<>(value);
        }
        size++;
    }

    // метод возвращает значение по индексу
    public T get(int index){
        if (size == 0){
            throw new IllegalStateException("list is emty");
        }
        else if (index < 0){
            throw new IndexOutOfBoundsException(index);
        }
        else if (index > size - 1){
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> cursor = head;
        int indexCursor = 0;
        while (cursor != null){
            if (indexCursor == index){
                return cursor.value;
            }
            cursor = cursor.next;
            indexCursor++;
        }
        return cursor.value;
    }

    // метод возвращает значение по индексу 0
    public T getFirst(){
        return get(0);
    }

    // метод удаляет ноду из списка по индексу и возвращает её value
    public T pop(int index){
        if (size == 0){
            throw new IllegalStateException("list is empty");
        }
        else if (index < 0){
            throw new IndexOutOfBoundsException(index);
        }
        else if (index > size - 1){
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0){
            T pop = head.value;
            head = head.next;
            size--;
            return pop;
        }
        Node<T> cursor = head;
        int indexCursor = 1;
        while (cursor.next != null){
            if (indexCursor == index){
                T pop = cursor.next.value;
                cursor.next = cursor.next.next;
                size--;
                return pop;
            }
            cursor = cursor.next;
            indexCursor++;
        }
        return cursor.value;
    }

    // метод удаляет первую ноду из списка и возвращает её value
    public T popFirst(){
        return pop(0);
    }

    public T popLast(){
        return pop(size - 1);
    }

    public boolean contains(T value){
        if (size == 0){
            throw new IllegalStateException("list is empty");
        }
        Node<T> cursor = head;
        T currValue = cursor.value;
        while (cursor != null){
            currValue = cursor.value;
            if (currValue == value){
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }

    // метод создаёт реверснутый список и возвращает его
    public MyLinkedList<T> reversed(){
        MyLinkedList<T> newList = new MyLinkedList<>();
        Node<T> cursor = head;
        while (cursor != null){
            T value = cursor.value;
            newList.addBegin(value);
            cursor = cursor.next;
        }
        return newList;
    }

    @Override
    public String toString(){
        // [1 -> 2 -> 3 -> 4 -> 5 -> ]
        StringBuilder result = new StringBuilder("[");
        Node<T> cursor = head;
        while (cursor != null){
            result.append(cursor.value).append(" -> ");
            cursor = cursor.next;
        }
        int length = result.length();
        if (length > 4){
            result.delete(length - 4, length);
        }
        result.append("]\n");
        return result.toString();
    }
}