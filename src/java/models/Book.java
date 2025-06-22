
package models;

public class Book {

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", genre=" + genre + ", publicationYear=" + publicationYear + ", availability=" + availability + ", description=" + description + '}';
    }

    public Book(int bookId, String title, String author, String isbn, String genre, int publicationYear, String availability, String description) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.availability = availability;
        this.description = description;
    }

     public Book() {
        this.bookId = 0;
        this.title = "empty";
        this.author = "Empty";
        this.isbn = "Empty";
        this.genre = "Empty";
        this.publicationYear = 0;
        this.availability = "No Availabile";
        this.description = "No Description";
    }
     
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int publicationYear;
    private String availability; // available, borrowed, reserved
    private String description;
}

