package cinema;

import java.text.DecimalFormat;

public class CinemaDto {
    private String[][] cinemaSchema;
    private int rows;
    private int seats;
    private int bookedSeats;
    private int income;


    public CinemaDto(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        init();
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String[][] getCinemaSchema() {
        return cinemaSchema;
    }

    public void setCinemaSchema(String[][] cinemaSchema) {
        this.cinemaSchema = cinemaSchema;
    }

    public void init() {
        bookedSeats = 0;
        income = 0;
        cinemaSchema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaSchema[i][j] = "S";
            }
        }
    }


    public void bookSeat(int seatRow, int seatCollumn) {
        cinemaSchema[seatRow - 1][seatCollumn - 1] = "B";
        int price = getTicketPrice(seatRow);
        System.out.println("Ticket price: $" + price);
        System.out.println();
        bookedSeats++;
        income+=price;
    }

    private int getTicketPrice(int seatRow) {
        if (rows * seats < 60 || seatRow <= rows / 2) return 10;
        else return 8;
    }

    public boolean bookingInputWrong(int seatRow, int seatCollumn) {
        return seatRow > rows || seatRow < 0 || seatCollumn > seats || seatCollumn < 0;
    }

    public boolean placeNotAvailable(int seatRow, int seatCollumn) {
        return cinemaSchema[seatRow - 1][seatCollumn-1].equals("B");
    }

    public void getStat() {
        System.out.println("Number of purchased tickets: "+ bookedSeats);
        System.out.println(String.format("Percentage: %.2f%%", calcPercent()));
        System.out.println("Current income: $"+ income);
        System.out.println("Total income: $"+getTotalIncome());
        System.out.println();

    }

    private double calcPercent() {

        double perc = ((double) bookedSeats / (rows * seats)) * 100;

        return Double.valueOf(new DecimalFormat("##.##").format(perc).replace(",","."));
    }

    private int getTotalIncome() {
        if (rows * seats < 60) return rows * seats * 10;
        else return  (10 * seats * (rows / 2) + 8 * seats * (rows - rows / 2));
    }
}
