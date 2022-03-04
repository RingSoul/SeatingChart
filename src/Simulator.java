public class Simulator {
    public static void main(String[] args) {
        // 31 people, possible triplets
        String[][] oldSeats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao", "Nicole", "Jennifer", "Michelle", null, null},
                          {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
                          {"ChengHan", "Qihan", "Kevin", "Ryan", "Justin", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};
        String[][] newSeats = new String[3][12];
        int[] invalidRow0 = {10, 11};
        // preparing for the only move-able null
        int invalidR = -1;
        int invalidC = -1;

        for (int r = 0; r < oldSeats.length; r++)
        {
            for (int c = 0; c < oldSeats[0].length; c++)
            {
                /* beginning */
                String current = oldSeats[r][c];
                // random seat
                int randRow = (int) (Math.random() * 3); // 0 - 2 (total 3)
                int randColumn = (int) (Math.random() * 12); // 0 - 11 (total 12)
                if (current != null) {
                    // duplicate?
                    boolean isRepeated = false;
                    if (r == randRow && c == randColumn) {
                        isRepeated = true;
                    }
                    // check validity (row 0: column 10 & 11)
                    boolean isNotSeat = false;
                    if (randRow == 0) {
                        for (int i : invalidRow0) {
                            if (oldSeats[0][i] == null) {
                                isNotSeat = true;
                            }
                        }
                    }
                    // check neighbor
                    boolean hasSameNeighbors = false;
                    String oldLeft = "";
                    String oldRight = "";
                    String newLeft = "";
                    String newRight = "";
                    if (c != 0)
                        oldLeft = oldSeats[r][c-1];
                    if (c != oldSeats[0].length - 1)
                        oldRight = oldSeats[r][c+1];
                    if (randColumn != 0)
                        newLeft = newSeats[randRow][randColumn - 1];
                    if (randColumn != newSeats[0].length - 1)
                        newRight = newSeats[randRow][randColumn + 1];
                    if (oldLeft != null && oldRight != null && newLeft != null && newRight != null)
                    {
                        if (oldLeft.equals(newLeft)) {
                            hasSameNeighbors = true;
                        } else if (oldLeft.equals(newRight)) {
                            hasSameNeighbors = true;
                        } else if (oldRight.equals(newLeft)) {
                            hasSameNeighbors = true;
                        } else if (oldRight.equals(newRight)) {
                            hasSameNeighbors = true;
                        }
                    }


                    // first condition checks if the seat is already sat by someone, rest are described above
                    // same steps of checking are repeated in this loop, possible improvement
                    // last two conditions checks if the random seat is at the place where the only move-able null is fixed at
                    while (newSeats[randRow][randColumn] != null || isNotSeat || isRepeated || hasSameNeighbors || (randRow == invalidR && randColumn == invalidC))
                    {
                        // random seat
                        randRow = (int) (Math.random() * 3);
                        randColumn = (int) (Math.random() * 12);
                        // duplicate?
                        isRepeated = false;
                        if (r == randRow && c == randColumn) {
                            isRepeated = true;
                        }
                        // check validity (row 0: column 10 & 11)
                        isNotSeat = false;
                        if (randRow == 0) {
                            for (int i : invalidRow0) {
                                if (randColumn == i) {
                                    isNotSeat = true;
                                }
                            }
                        }
                        // check neighbor
                        hasSameNeighbors = false;
                        oldLeft = "";
                        oldRight = "";
                        newLeft = "";
                        newRight = "";
                        if (c != 0)
                            oldLeft = oldSeats[r][c-1];
                        if (c != oldSeats[0].length - 1)
                            oldRight = oldSeats[r][c+1];
                        if (randColumn != 0)
                            newLeft = newSeats[randRow][randColumn - 1];
                        if (randColumn != newSeats[0].length - 1)
                            newRight = newSeats[randRow][randColumn + 1];
                        if (oldLeft != null && oldRight != null && newLeft != null && newRight != null)
                        {
                            if (oldLeft.equals(newLeft)) {
                                hasSameNeighbors = true;
                            } else if (oldLeft.equals(newRight)) {
                                hasSameNeighbors = true;
                            } else if (oldRight.equals(newLeft)) {
                                hasSameNeighbors = true;
                            } else if (oldRight.equals(newRight)) {
                                hasSameNeighbors = true;
                            }
                        }
                    }
                    newSeats[randRow][randColumn] = current;
                }
                else // managing the only null that is move-able
                {
                    if (randRow != 0) // not confused with two invalid seats
                    {
                        boolean isNotValid = true;
                        while (randColumn == 1 || randColumn == newSeats[0].length - 2 || isNotValid) // index 1 or index length-2 for column will cause separation
                        {
                            isNotValid = true; // refresh if failed
                            randRow = (int) (Math.random() * 3);
                            randColumn = (int) (Math.random() * 12);
                            if (randRow == 0)
                            {
                                for (int i : invalidRow0)
                                {
                                    if (i != randColumn)
                                    {
                                        isNotValid = false;
                                    }
                                }
                                // index length-4 at row 0 also separates one person if unlucky enough
                                if (randColumn == newSeats[0].length - 4)
                                {
                                    isNotValid = false;
                                }
                            }
                        }
                    }
                    invalidC = randColumn;
                    invalidR = randRow;
                }
            }
        }

        // print
        System.out.println("New Seating");
        for (String[] names : newSeats)
        {
            for (String name : names)
            {
                System.out.print(name + "  ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Old seating (for comparison)");
        for (String[] names : oldSeats)
        {
            for (String name : names)
            {
                System.out.print(name + "  ");
            }
            System.out.println();
        }

        System.out.println();

        // seat duplicate check
        System.out.println("Same seat, different people?");
        int num = 1;
        for (int r = 0; r < oldSeats.length; r++)
        {
            for (int c = 0; c < oldSeats[0].length; c++)
            {
                System.out.println(num + ". " + oldSeats[r][c] + "-->" + newSeats[r][c]);
                num++;
            }
        }
    }
}
