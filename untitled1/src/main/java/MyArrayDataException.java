public class MyArrayDataException extends RuntimeException {
    MyArrayDataException(String msg) {

        super("Ошибка преобразования элемента массива в целое число.\n" + " " + msg);
    }

    @Override
    public String toString() {
        return "Невозможно найти сумму элементов массива.";
    }
}
