import java.util.Random;
import java.util.Scanner;

public class Game {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;

    private enum Move {
        ROCK, SCISSORS, PAPER, LIZARD, SPOCK ;

        public int compareMoves(Move otherMove) {
            // Ничья
            if (this == otherMove)
                return 0;

            switch (this) {
                case ROCK:
                    return ((otherMove == SCISSORS || otherMove == LIZARD) ? 1 : -1);
                case SCISSORS:
                    return ((otherMove == PAPER || otherMove == LIZARD) ? 1 : -1);
                case PAPER:
                    return ((otherMove == ROCK || otherMove == SPOCK) ? 1 : -1);
                case LIZARD:
                    return ((otherMove == SPOCK || otherMove == PAPER) ? 1 : -1);
                case SPOCK:
                    return ((otherMove == SCISSORS || otherMove == ROCK) ? 1 : -1);
            }

            // Этот код не должен выполняться никогда

            return 0;
        }
        public String getTranslate() {
            switch (this) {
                case ROCK:
                    return "Камень";
                case SCISSORS:
                    return "Ножницы";
                case PAPER:
                    return "Бумага";
                case LIZARD:
                    return "Ящерица";
                case SPOCK:
                    return "Спок";
            }
            return "Кроказябра";
        }
        public String getWinWord(Move otherMove) {
            switch (this) {
                case ROCK:
                    return "давит";
                case SCISSORS:
                    return (otherMove == PAPER ? "режут" : "отрезают голову");
                case PAPER:
                    return (otherMove == ROCK ? "покрывает" : "опровергает");
                case LIZARD:
                    return (otherMove == PAPER ? "ест" : "травит");
                case SPOCK:
                    return (otherMove == SCISSORS ? "ломает" : "испаряет");
            }
            return "бьет";
        }
    }

    private class User {
        private Scanner inputScanner;

        public User() {
            inputScanner = new Scanner(System.in);
        }

        public static boolean isStringValidNumber(String input) {
            int[] inputNumbers = {0,1,2,3,4};
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                int inputNumber =  Integer.parseInt(input);
                return ArrayHelpers.includes(inputNumbers, inputNumber);
            } else {
                return false;
            }
        }

        public Move getMove() {
            // Выведем запрос на ввод
            System.out.print("Камень(0), ножницы(1), бумага(2), ящерица(3) или спок(4)? ");

            // Прочитаем ввод пользователя
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            //Для ввода возможны слова или цифры
            //для слов проверяем совпадение хотя бы первой буквы
            boolean isInputValidNumber = isStringValidNumber(userInput);

            if (firstLetter == 'К' || firstLetter == 'Н'
                    || firstLetter == 'Б' || firstLetter == 'Я'
                    || firstLetter == 'С' || isInputValidNumber ) {
                // Ввод корректный
                switch (firstLetter) {
                    case 'К':
                        return Move.ROCK;
                    case 'Б':
                        return Move.SCISSORS;
                    case 'Н':
                        return Move.PAPER;
                    case 'Я':
                        return Move.LIZARD;
                    case 'С':
                        return Move.SPOCK;
                    case '0':
                        return Move.ROCK;
                    case '1':
                        return Move.SCISSORS;
                    case '2':
                        return Move.PAPER;
                    case '3':
                        return Move.LIZARD;
                    case '4':
                        return Move.SPOCK;
                }
            }

            // Ввод некорректный. Выведем запрос на ввод снова.
            return getMove();
        }

        public boolean playAgain() {
            System.out.print("Хотите сыграть еще раз?(Y/N)");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }

    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }

    public Game() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public void startGame() {
        System.out.println("КАМЕНЬ, НОЖНИЦЫ, БУМАГА, ЯЩЕРИЦА, СПОК!");

        // Получение ходов
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nВаш ход " + userMove.getTranslate() + ".");
        System.out.println("Ход компьютера " + computerMove.getTranslate() + ".\n");

        // Сравнение ходов и определение победителя
        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0: // Ничья
                System.out.println("НИЧЬЯ!");
                break;
            case 1: // Победил игрок
                System.out.println(userMove.getTranslate() + " " + userMove.getWinWord(computerMove) + " " + computerMove.getTranslate() + ". Вы победили!");
                userScore++;
                break;
            case -1: // Победил компьютер
                System.out.println(computerMove.getTranslate() + " " + computerMove.getWinWord(userMove) + " " + userMove.getTranslate() + ". Вы проиграли.");
                computerScore++;
                break;
        }
        numberOfGames++;

        // Предложим пользователю сыграть еще раз
        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

    /**
     * Вывод статистики. Ничьи учитываются как полпобеды
     * при подсчете процента побед.
     */
    private void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;

        // Вывод линии
        System.out.print("+");
        printDashes(68);
        System.out.println("+");

        // Вывод заголовков таблицы
        System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
                "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");

        // Вывод линии
        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");

        // Вывод значений
        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, numberOfGames, percentageWon * 100);

        // Вывод линии
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

}