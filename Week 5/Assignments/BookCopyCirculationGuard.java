class BookCopyInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookCopyInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookCopyCirculationGuard {
    public static void main(String[] args) {
        BookCopyInventory book1 = new BookCopyInventory(3);

        book1.checkOut();
        book1.checkOut();
        book1.checkOut();
        book1.checkOut();

        System.out.println(book1.getCopiesAvailable());

        BookCopyInventory book2 = new BookCopyInventory(3);

        book2.checkOut();
        book2.checkOut();
        book2.checkOut();

        book2.checkIn();
        book2.checkIn();
        book2.checkIn();
        book2.checkIn();

        System.out.println(book2.getCopiesAvailable());
    }
}