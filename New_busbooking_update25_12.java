package busbook26_12updated;


import java.util.*;
import java.util.UUID;
import java.util.function.Supplier;

public class New_busbooking_update25_12 {

    static Scanner sc = new Scanner(System.in);

    // -------- USER STORAGE ----------
    static HashMap<String, User> users = new HashMap<>();
    static String loggedInUserName;

    public static void main(String[] args) {

        // -------- LOGIN / SIGNUP ----------
        while (true) {
        	System.out.println("===============================================");
        	System.out.println("           WELCOME TO REDBUS 🚍                ");
        	System.out.println("   INDIA'S MOST POPULAR ONLINE BUS BOOKING     ");
        	System.out.println("              SYSTEM                           ");
        	System.out.println("===============================================");
        	System.out.println();
        	System.out.println();
            System.out.println("\n========= LOGIN MENU ========================");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1 && login()) break;
            else if (choice == 2 && signup()) break;
            else System.out.println("Try again!");
        }

        // -------- BUS BOOKING ----------
        HashSet<BusBooking> bookings = new HashSet<>();
        Set<Integer> selectedSeats = new HashSet<>();

        System.out.println("\n========================================");
        System.out.println("WELCOME " + loggedInUserName.toUpperCase());
        System.out.println("WELCOME TO BUS BOOKING SYSTEM");
        System.out.println("========================================");

        System.out.print("Enter number of seats to book (1-4): ");
        int seats = sc.nextInt();

        if (seats < 1 || seats > 4) {
            System.out.println("Invalid seat selection!");
            return;
        }

        // ---------- FROM  CITY ----------
        System.out.println("\nSelect city From =>");
        showcityFrom();
        
        int boarding = sc.nextInt();
        String fromCity = getCityName(boarding);
        showBoardingPoints(boarding);
        
        System.out.print("Select boarding point: ");
        int boardingPoint = sc.nextInt();
        String boardingPointName = getBoardingPointName(boarding, boardingPoint);

        // ---------- TO  CITY----------
        System.out.println("\nSelect city To =>");
        showcityTo();
        
        int dropping = sc.nextInt();
        String toCity = getCityName(dropping);
        showDroppingPoints(dropping);
        
        System.out.print("Select dropping point: ");
        int droppingPoint = sc.nextInt();
        String droppingPointName = getDroppingPointName(dropping, droppingPoint);

        showSeatLayout();

        for (int i = 1; i <= seats; i++) {

            System.out.println("\nPassenger " + i + " Details");
            int seat;
            while (true) {
                System.out.print("Enter seat number: ");
                seat = sc.nextInt();
                if (!selectedSeats.contains(seat)) {
                    selectedSeats.add(seat);
                    break;
                }
                System.out.println("Seat already selected!");
            }

            System.out.print("Enter Name: ");
            String name = sc.next();

            String gender;
            while (true) {
                System.out.print("Enter Gender (M/F): ");
                gender = sc.next().toUpperCase();
                if (gender.equals("M") || gender.equals("F")) break;
            }

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            String number;
            while (true) {
                System.out.print("Enter Mobile Number: ");
                number = sc.next();
                if (number.matches("\\d{10}")) break;
            }

            bookings.add(new BusBooking(
                    name, number, age, seat, gender,
                    fromCity, boardingPointName,
                    toCity, droppingPointName
            ));
        }

        System.out.println("\n=========== BOOKING DETAILS ===========");
        for (BusBooking b : bookings) {
            String bookingId = "BUS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            System.out.println(b + " | Booking ID: " + bookingId);
        }

        new Amount(seats);
    }
//============================================================================
    // ---------- LOGIN ----------
    
    static boolean login() {
        System.out.print("Enter mobile number: ");
        String mobile = sc.nextLine();
        if (users.containsKey(mobile)) {
            loggedInUserName = users.get(mobile).name;
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("User not found. Signup first.");
        return false;
    }

    // ---------- SIGNUP ----------
    
    static boolean signup() {

        System.out.print("Enter mobile number: ");
        String mobile = sc.nextLine();

       

        if (!mobile.matches("\\d{10}")) {
            System.out.println("Invalid mobile!");
            return false;
        }

        if (users.containsKey(mobile)) {
            System.out.println("Already registered!");
            return false;
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine();
 // add the some random code 
        String loginId = "LOGIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        users.put(mobile, new User(name, loginId));
        loggedInUserName = name;

        System.out.println("Signup successful!");
        System.out.println("Your Login ID: " + loginId);
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ---------- CITIES / POINTS / SEATS ----------
    static void showcityFrom() {
        System.out.println("1. Mumbai\n2. Pune\n3. Nashik\n4. Nagpur\n5. Aurangabad\n6. Kolhapur\n7. Satara\n8. Solapur");
    }
    static void showcityTo() { showcityFrom(); }
    static String getCityName(int c) {
        String[] cities = {"Mumbai","Pune","Nashik","Nagpur","Aurangabad","Kolhapur","Satara","Solapur"};
        return cities[c - 1];
    }

    static void showBoardingPoints(int city) {
        String[][] bp = {
                {"Dadar","Andheri","Borivali","Kurla"},
                {"Swargate","Katraj","Nigdi","Dange Chowk"},
                {"CBS","College Road","Panchavati","MIDC"},
                {"Sitabuldi","Dharampeth","Wardha Road","Sadar"},
                {"CIDCO","Kranti Chowk","Waluj","Station"},
                {"Bus Stand","Rankala","Tarabai Park","Udyamnagar"},
                {"Powai Naka","Karad Naka","MIDC","Bus Stand"},
                {"Central Stand","Railway Station","Akkalkot Rd","Hotgi Rd"}
        };
        for (int i = 0; i < 4; i++) System.out.println((i + 1) + ". " + bp[city - 1][i]);
    }

    static String getBoardingPointName(int city, int p) {
        return new String[][]{
                {"Dadar","Andheri","Borivali","Kurla"},
                {"Swargate","Katraj","Nigdi","Dange Chowk"},
                {"CBS","College Road","Panchavati","MIDC"},
                {"Sitabuldi","Dharampeth","Wardha Road","Sadar"},
                {"CIDCO","Kranti Chowk","Waluj","Station"},
                {"Bus Stand","Rankala","Tarabai Park","Udyamnagar"},
                {"Powai Naka","Karad Naka","MIDC","Bus Stand"},
                {"Central Stand","Railway Station","Akkalkot Rd","Hotgi Rd"}
        }[city - 1][p - 1];
    }

    static void showDroppingPoints(int city) {
        String[][] dp = {
                {"Bandra","Chembur","Thane","Navi Mumbai"},
                {"Wakad","Hinjewadi","Hadapsar","Kharadi"},
                {"Gangapur Rd","Dwarka","Adgaon","Ambad"},
                {"Manish Nagar","Mihan","Chatrapati Sq","Jaripatka"},
                {"Jalna Rd","Paithan Gate","Beed Bypass","Chikalthana"},
                {"Ujalaiwadi","Shahupuri","Gandhinagar","Ichalkaranji"},
                {"Karanje","Godoli","Degaon","MIDC"},
                {"Vijapur Rd","Barshi Rd","Pandharpur Rd","Akluj"}
        };
        for (int i = 0; i < 4; i++) System.out.println((i + 1) + ". " + dp[city - 1][i]);
    }

    static String getDroppingPointName(int city, int p) {
        return new String[][]{
                {"Bandra","Chembur","Thane","Navi Mumbai"},
                {"Wakad","Hinjewadi","Hadapsar","Kharadi"},
                {"Gangapur Rd","Dwarka","Adgaon","Ambad"},
                {"Manish Nagar","Mihan","Chatrapati Sq","Jaripatka"},
                {"Jalna Rd","Paithan Gate","Beed Bypass","Chikalthana"},
                {"Ujalaiwadi","Shahupuri","Gandhinagar","Ichalkaranji"},
                {"Karanje","Godoli","Degaon","MIDC"},
                {"Vijapur Rd","Barshi Rd","Pandharpur Rd","Akluj"}
        }[city - 1][p - 1];
    }

    static void showSeatLayout() {
        System.out.println("\nSeats Layout (1-36)");
        System.out.println("               ☮️");
        System.out.println("1w   2    3    4w");
        System.out.println("8w   7    6    5w");
        System.out.println("9w   10   11  12w");
        System.out.println("16w  15   14  13w");
        System.out.println("17w  18   19  20w");
        System.out.println("24w  23   22  21w");
        System.out.println("25w  26   27  28w");
        System.out.println("31w  30   30  29w");
        System.out.println("32w  34   35  36w");
    }
}

// ---------- SUPPORT CLASSES ----------
class User {
    String name, loginId;
    User(String name, String loginId) {
        this.name = name;
        this.loginId = loginId;
    }
}

class BusBooking {
    String name, number, gender;
    int age, seat;
    String fromCity, boardingPoint, toCity, droppingPoint;

    BusBooking(String name, String number, int age, int seat, String gender,
               String fromCity, String boardingPoint,
               String toCity, String droppingPoint) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.seat = seat;
        this.gender = gender;
        this.fromCity = fromCity;
        this.boardingPoint = boardingPoint;
        this.toCity = toCity;
        this.droppingPoint = droppingPoint;
    }

    public String toString() {
        return name + " | Seat " + seat + " | " + fromCity + " → " + toCity;
    }

    public int hashCode() { return Objects.hash(seat, number); }
    public boolean equals(Object o) {
        if (!(o instanceof BusBooking)) return false;
        BusBooking b = (BusBooking) o;
        return seat == b.seat && number.equals(b.number);
    }
}

class Amount {
    private static final int PRICE_PER_SEAT = 800;
    Amount(int seats) {
        System.out.println("\nTotal Amount: ₹" + (seats * PRICE_PER_SEAT));
    }
}
