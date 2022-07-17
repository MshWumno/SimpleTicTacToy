
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[][] ticTacArray = new char[3][3];
        for (int i = 0; i < ticTacArray.length; i++) {
            for (int j = 0; j < ticTacArray[i].length; j++) {
                ticTacArray[i][j] = ' ';
            }
        }

        char player = 'X';
        char player2 = 'O';
        char space = ' ';
        boolean button = false;

        drawBattleField(ticTacArray);

        while(!button) {
            drawBattleField(shot(player, input(ticTacArray), ticTacArray));
            button = winner(player, space, ticTacArray);
            if (button) {
                break;
            }

            drawBattleField(shot(player2, input(ticTacArray), ticTacArray));
            button = winner(player2, space, ticTacArray);
            if (button) {
                break;
            }

        }


    }


    static void drawBattleField(char[][] ticTacArray) {
        /** рисует поле боя*/
        System.out.println("---------");

        for (int i = 0; i < ticTacArray.length; i++) {
            for (int j = 0; j < ticTacArray[i].length; j += 3) {
                System.out.print("| " + ticTacArray[i][j] + " " + ticTacArray[i][j + 1] + " " + ticTacArray[i][j + 2]
                        + " |\n");
            }
        }

        System.out.println("---------");
    }

    static char[][] shot(char ch, String[] shotTarget, char[][] ticTacArray) {
        /** делает выстрел */
        int vertical = Integer.parseInt(shotTarget[0]);
        int horizontal = Integer.parseInt(shotTarget[1]) - 1;

        switch (vertical) {
            case 1:
                ticTacArray[0][horizontal] = ch;
                break;
            case 2:
                ticTacArray[1][horizontal] = ch;
                break;
            case 3:
                ticTacArray[2][horizontal] = ch;
                break;
        }
        return ticTacArray;
    }

    static String[] input(char[][] ticTacArray) {
        /** принимает координаты выстрела, проверяет их на валидность данных */

        boolean button = false;
        String[] shotTarget = new String[2];
        do {
            try {
                System.out.print("Enter the coordinates: ");
                Scanner scanner = new Scanner(System.in);

                shotTarget = scanner.nextLine().split(" ");

                int vertical = Integer.parseInt(shotTarget[0]) - 1;
                int horizontal = Integer.parseInt(shotTarget[1]) - 1;

                if (Integer.parseInt(shotTarget[0]) > 3 || Integer.parseInt(shotTarget[0]) < 1
                        || Integer.parseInt(shotTarget[1]) > 3 || Integer.parseInt(shotTarget[1]) < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    button = false;
                } else if (ticTacArray[vertical][horizontal] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    button = false;
                } else {
                    button = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }

        } while (!button);

        return shotTarget;
    }

    static boolean winner(char ch, char space, char[][] ticTacArray) {
        /** проверяет выигрыш */
        space = ' ';
        boolean button = false;
        // горизонтальная проверка
        for (int i = 0; i < ticTacArray.length; i++) {
            for (int j= 0; j < ticTacArray[i].length; j += 3) {
                if (ticTacArray[i][j] == ch && ticTacArray[i][j] == ticTacArray[i][j + 1]
                        && ticTacArray[i][j] == ticTacArray[i][j + 2]) {
                    button = true;
                    break;
                }
                break;
            }
        }
        // вертикальная проверка
        for (int i = 0; i < ticTacArray.length; i += 3) {
            for (int j= 0; j < ticTacArray[i].length; j++) {
                if (ticTacArray[i][j] == ch && ticTacArray[i][j] == ticTacArray[i + 1][j]
                        && ticTacArray[i][j] == ticTacArray[i + 2][j]) {
                    button = true;
                    break;
                }
                break;
            }
        }
        // проверка наискосок - слева на право
        for (int i = 0; i < ticTacArray.length; i += 3) {
            for (int j= 0; j < ticTacArray[i].length; j += 3) {
                if (ticTacArray[i][j] == ch && ticTacArray[i][j] == ticTacArray[i + 1][j + 1]
                        && ticTacArray[i][j] == ticTacArray[i + 2][j + 2]) {
                    button = true;
                    break;
                }
                break;
            }
        }
        // проверка наискосок - справа на лево
        for (int i = 0; i < ticTacArray.length; i += 3) {
            for (int j= 0; j < ticTacArray[i].length; j += 3) {
                if (ticTacArray[i][j + 2] == ch && ticTacArray[i][j + 2] == ticTacArray[i + 1][j + 1]
                        && ticTacArray[i][j + 2] == ticTacArray[i + 2][j]) {
                    button = true;
                    break;
                }
                break;
            }
        }
        if (button) {
            System.out.println(ch + " wins");
        }
        if(!button && countSymbol(space, ticTacArray) == 0) {
            System.out.println("Draw");
            button = true;
        }

        return button;
    }

    static int countSymbol(char space, char[][] ticTacArray) {
        /** считает символы в массиве */
        int count = 0;
        for (int i = 0; i < ticTacArray.length; i++) {
            for (int j = 0; j < ticTacArray[i].length; j++) {
                if (ticTacArray[i][j] == space) {
                    count++;
                }
            }
        }
        return count;
    }


//    static void endGame(char player, char player2, char space, char[][] ticTacArray) {
//        /** объявляет победителя */
//
//        if (countSymbol(space, ticTacArray) == 0) {
//            System.out.println("Draw");
//        } else if (winner(player2, ticTacArray)) {
//            System.out.println("O wins");
//        } else if (winner(player, ticTacArray)) {
//            System.out.println("X wins");
//        }
//    }


}
