public class MyArraySizeException extends RuntimeException{
    MyArraySizeException(String msg) {

        super("Ошибка размерности массива.\n" + " " + msg);
    }

    @Override
    public String toString() {
        return "Ошибка размерности массива.";
    }
}
