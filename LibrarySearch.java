public class LibrarySearch {

    // Binary Search function
    public static int searchBookByISBN(int[] sortedISBNs, int targetISBN) {
        int low = 0;
        int high = sortedISBNs.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sortedISBNs[mid] == targetISBN) {
                return mid; // Book found at index mid
            } else if (sortedISBNs[mid] < targetISBN) {
                low = mid + 1; // Search right half
            } else {
                high = mid - 1; // Search left half
            }
        }

        return -1; // Book not found
    }

    public static void main(String[] args) {
        int[] libraryISBNs = {1001, 1010, 1023, 1050, 1100, 1200, 1300}; // Sorted ISBNs
        int target = 1050;

        int index = searchBookByISBN(libraryISBNs, target);

        if (index != -1) {
            System.out.println("Book found at index: " + index);
        } else {
            System.out.println("Book not found in the library.");
        }
    }
}