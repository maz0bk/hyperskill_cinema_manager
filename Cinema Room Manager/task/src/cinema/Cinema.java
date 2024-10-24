package cinema;

import java.util.Scanner;

public class Cinema {
    static CinemaDto cinemaDto;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        createCinema(sc);
        boolean flag = true;
        while (flag) {
            showMenu();
            if (!sc.hasNext()) {
                continue;
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    printCinema();
                    break;
                }
                case 2: {
                    byeTicket(sc);
                    break;
                }
                case 3: {
                    showStatistics();
                    break;
                }
                case 0: {
                    flag = false;
                    break;
                }
                default: {
                    flag = false;
                }
            }
        }
        sc.close();

    }

    private static void showStatistics() {
        cinemaDto.getStat();

    }

    private static void byeTicket(Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter a row number:");
            int seatRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatCollumn = sc.nextInt();
            if (cinemaDto.bookingInputWrong(seatRow, seatCollumn)) {
                System.out.println("Wrong input!");
                continue;
            }
            if (cinemaDto.placeNotAvailable(seatRow, seatCollumn)) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            cinemaDto.bookSeat(seatRow, seatCollumn);
            break;
        }
    }

    private static void printCinema() {
        System.out.println("Cinema:");
        for (int i = 1; i <= cinemaDto.getSeats(); i++) {
            if (i == 1) {
                System.out.print("  ");
            }
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinemaDto.getRows(); i++) {
            for (int j = 0; j < cinemaDto.getSeats(); j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " ");
                }
                System.out.print(cinemaDto.getCinemaSchema()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void createCinema(Scanner sc) {
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        cinemaDto = new CinemaDto(rows, seats);
    }

    private static void showMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }


}