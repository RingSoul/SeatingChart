public class Simulator {
    public static void main(String[] args) {
        String[][] oldSeats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao Huang", "Nicole", "Jennifer", "Michelle", null, null},
                          {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
                          {"Cheng Han", "Qihan", "Kevin", "Ryan", "Justin Liu", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};
        String[][] newSeats = new String[3][12];
        int[] doNotExistR1 = {11, 12};
        for (String[] row : oldSeats)
        {
            for (String c : row)
            {
                int randRow = (int) (Math.random() * 2);
                int randColumn = (int) (Math.random() * 11);
                boolean isNotSeat = true;
                if (randRow == 0)
                while (newSeats[randRow][randColumn] != null || (randRow == ))
                {
                    randRow = (int) (Math.random() * 2);
                    randColumn = (int) (Math.random() * 11);
                }
            }
        }
    }
}
