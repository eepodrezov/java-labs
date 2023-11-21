import java.util.Scanner;

public class Main {


    public static int getLabNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите лабу, которую хотите проверить(1,2,3): ");
        String input = scanner.nextLine();
        try {
            int lab = Integer.parseInt(input);
            int[] validLabs = {1,2,3};
            if (ArrayHelpers.includes(validLabs, lab)) {
                System.out.println("Вы выбрали лабу №: " + lab);
                int[] notReadyLabs = {};
                if (!ArrayHelpers.includes(notReadyLabs, lab)) {
                    return lab;
                }
                System.out.println("Эта лаба еще не готова)");
                restart();
            } else {
                System.out.println("Вы выбрали не существующую лабу(1,2,3): ");
                restart();
            }

        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число(1,2,3)");
            restart();
        }
        return 0;
    }
    public static void start() {
        int selectedLab = getLabNumber();
        if (selectedLab > 0) {
            startLab(selectedLab);
        }
    }
    public static void restart() {
        int selectedLab = getLabNumber();
        if (selectedLab > 0) {
            startLab(selectedLab);
        }
    }
    public static void startLab(int labNumber) {
        switch (labNumber) {
            case 1:
                startLab1();
                break;
            case 2:
                startLab2();
                break;
            case 3:
                startLab3();
                break;
        }
    }
    public static void startLab1() {
        Game game = new Game();
        game.startGame();
    }
    public static void startLab2() {
        ATM.startATM();
    }
    public static void startLab3() {
        System.out.println("Запускается лаба номер " + 3 + "...");
        System.out.println("А 3 лабораторная сделана в отдельной проекте)");
    }

    public static void main(String[] args) {
        start();
    }
}