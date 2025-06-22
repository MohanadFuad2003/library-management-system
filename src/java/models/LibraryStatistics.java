/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

public class LibraryStatistics {
    private int totalBooks;
    private int activeBorrowers;
    private int reservedBooks;
    private double totalFinesCollected;
    private double outstandingFines;

    // getters and setters
    public int getTotalBooks() { return totalBooks; }
    public void setTotalBooks(int totalBooks) { this.totalBooks = totalBooks; }

    public int getActiveBorrowers() { return activeBorrowers; }
    public void setActiveBorrowers(int activeBorrowers) { this.activeBorrowers = activeBorrowers; }

    public int getReservedBooks() { return reservedBooks; }
    public void setReservedBooks(int reservedBooks) { this.reservedBooks = reservedBooks; }

    public double getTotalFinesCollected() { return totalFinesCollected; }
    public void setTotalFinesCollected(double totalFinesCollected) { this.totalFinesCollected = totalFinesCollected; }

    public double getOutstandingFines() { return outstandingFines; }
    public void setOutstandingFines(double outstandingFines) { this.outstandingFines = outstandingFines; }
}
