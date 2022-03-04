public class NotRelevant {
    public static void main(String[] args) {
        try {
            String[] strList = {"apple", "tree"};
            strList[-1] = "what";
        }
        catch (IndexOutOfBoundsException i) {
            System.out.println(i.getMessage());
        }
    }
}
