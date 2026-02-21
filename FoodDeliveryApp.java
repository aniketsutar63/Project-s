package ViewResto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class FoodDeliveryApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // ---------------- Veg Restaurants ----------------
        List<String> vegRestaurants = Arrays.asList(
                "RamKrishna", "Sukanta Thali", "World Of Veg", "Vaishali",
                "Hotel Shreyas", "Mathura", "Kalyani Veg", "Sahana Veg Restaurant",
                "Hotel Abhishek Veg", "Keshar Pure Veg", "Madhav Pure Veg",
                "Laa Unico", "Te Amo Pure Veg"
        );

        // ---------------- Non-Veg Restaurants ----------------
        List<String> nonVegRestaurants = Arrays.asList(
                "Barbeque Nation", "Absolute Barbecues", "Surve’s Pure Non-Veg",
                "George Restaurant", "Hotel Sandeep", "Hotel Blue Nile",
                "PK Biryani House", "SP’s Biryani House", "Bedekar Misal (Non-Veg)",
                "Maratha Samrat", "Hotel Jagdamba",
                "Kaka Halwai (Non-Veg Items)", "Hotel Nisarg"
        );

        // ---------------- Veg Menu ----------------
        Map<String, Integer> vegMenu = new LinkedHashMap<>();
        vegMenu.put("Paneer Butter Masala", 180);
        vegMenu.put("Veg Biryani", 150);
        vegMenu.put("Dal Tadka", 120);
        vegMenu.put("Butter Naan", 40);
        vegMenu.put("Veg Thali", 220);

        // ---------------- Non-Veg Menu ----------------
        Map<String, Integer> nonVegMenu = new LinkedHashMap<>();
        nonVegMenu.put("Chicken Biryani", 220);
        nonVegMenu.put("Chicken Curry", 200);
        nonVegMenu.put("Mutton Biryani", 280);
        nonVegMenu.put("Fish Fry", 250);
        nonVegMenu.put("Egg Curry", 140);

        System.out.println("===== Welcome to Food Delivery App =====");
        System.out.println("1. Veg Restaurants");
        System.out.println("2. Non-Veg Restaurants");
        System.out.print("Select option: ");
        int typeChoice = sc.nextInt();

        List<String> selectedRestaurants;
        Map<String, Integer> selectedMenu;

        // ---------------- Selection Logic ----------------
        if (typeChoice == 1) {
            selectedRestaurants = vegRestaurants;
            selectedMenu = vegMenu;
        } else if (typeChoice == 2) {
            selectedRestaurants = nonVegRestaurants;
            selectedMenu = nonVegMenu;
        } else {
            System.out.println("Invalid Choice!");
            return;
        }

        // ---------------- Display Restaurants ----------------
        System.out.println("\nAvailable Restaurants:");
        for (int i = 0; i < selectedRestaurants.size(); i++) {
            System.out.println((i + 1) + ". " + selectedRestaurants.get(i));
        }

        System.out.print("Select Restaurant Number: ");
        int restChoice = sc.nextInt();

        String selectedRestaurant = selectedRestaurants.get(restChoice - 1);
        System.out.println("\nYou selected: " + selectedRestaurant);

        // ---------------- Display Correct Food Menu ----------------
        System.out.println("\nFood Menu:");
        List<String> foodList = new ArrayList<>();
        int index = 1;

        for (Map.Entry<String, Integer> entry : selectedMenu.entrySet()) {
            System.out.println(index + ". " + entry.getKey() + " - ₹" + entry.getValue());
            foodList.add(entry.getKey());
            index++;
        }

        // ---------------- Order Section ----------------
        int totalBill = 0;
        char more;
        String name;
        String number;
        String Gmail;
        String pincode;
        

        do {
            System.out.print("Select Food Item Number: ");
            int foodChoice = sc.nextInt();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String foodName = foodList.get(foodChoice - 1);
            int price = selectedMenu.get(foodName);
//---------------------- price  --------------------------------------------------------------
            totalBill = totalBill+ price * qty;
            System.out.println(foodName + " added to cart!");

            System.out.print("Do you want to order more? (Y/N): ");
            more = sc.next().charAt(0);

        }
        while (more == 'y' || more == 'Y');

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("------------ Please enter your delivery details: -------------------------------");
        System.out.println("----- Name, Mobile Number, Email ID (Gmail), and Pincode.-----------------------");
        System.out.println();
        System.out.println();
        
        
        System.out.println("Enter Your name     :=");
        name=sc.next();
        name.toUpperCase();
        
        System.out.println("Enter Mobile Number :=");
        number=sc.next();
        if(number.length()==10) {
        	     
        }else {
        	System.out.println("Invalid number !");
        	return ;
        }
        
        System.out.println("Enter Email id      := ");
        Gmail=sc.next();
        if(Gmail.contains("@gmail.com")) {
            
        } else {
            System.out.println("Invalid Email ID");
            return ;
        }
        
        System.out.println("Enter the Pincode     :=");
        pincode=sc.next();
        if(pincode.length()==6) {
        	
        }else {
        	System.out.println("Invalid Pincode");
            return ;
        }
     
        
        
        
        // ---------------- Final Bill ----------------
      
        System.out.println("\n===== Order Summary =====");
        System.out.println("Name:          "+name);
        System.out.println("Mobile number: "+number);
        System.out.println("Email ID:      "+Gmail);
        System.out.println("Pincode:       "+pincode);
        System.out.println("Restaurant:    " + selectedRestaurant);
        System.out.println("Total Amount: ₹" + totalBill);
        System.out.println("Thank you for ordering! 😊");
    }
}
