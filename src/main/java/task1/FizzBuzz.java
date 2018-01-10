package task1;

public class FizzBuzz {
    public static void main(String[] args) {
        for (int i=1; i <=100; i++) {

            boolean isThreeMultiplicity = i % 3 == 0;
            boolean isFiveMultiplicity = i % 5 == 0;

            if (!isThreeMultiplicity && !isFiveMultiplicity){
                Write(i);
            }
            else{
                if (isThreeMultiplicity) {
                    Write("Fizz");
                }
                if (isFiveMultiplicity) {
                    Write("Buzz");
                }
            }

            WriteNewLine();
        }
    }

    /**
     * Write input string message to console
     * @param message - Message to write
     */
    private static void Write(String message) {
        System.out.print(message);
    }

    /**
     * Write input integer message to console
     * @param message - Message to write
     */
    private static void Write(int message) {
        Write(Integer.toString(message));
    }

    /**
     * Carriage return
     */
    private static void WriteNewLine() {
        System.out.print(System.getProperty("line.separator"));
    }
}