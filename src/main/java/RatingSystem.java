import java.util.Arrays;

class RatingSystem {
  public static void main(String[] args) {
    System.out.println(rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}));
  }

  public static String rankTeams(String[] votes) {
    int numOfTeams = 26;
    var numOfPlaces = votes[0].length();
    int[][] ratings = new int[numOfTeams][numOfTeams];
    for (var vote : votes) {
      for (int i = 0; i < vote.length(); ++i) {
        var team = vote.charAt(i);
        var idx = team - 'A';
        ratings[idx][i] += 1;
      }
    }

    var teams = new Integer[numOfPlaces];
    for (int i = 0; i < numOfPlaces; ++i) {
      int team = votes[0].charAt(i) - 'A';
      teams[i] = team;
    }

    Arrays.sort(teams, (i1, i2) -> {
      var o1 = ratings[i1];
      var o2 = ratings[i2];
      for (int i = 0; i < numOfPlaces; i++) {
        int a1 = o1[i];
        int a2 = o2[i];
        if (a1 > a2) {
          return -1;
        } else if (a1 < a2) {
          return 1;
        }
      }
      return i1 - i2;
    });

    var result = new StringBuilder();
    for (int team : teams) {
      result.append((char) (team + 'A'));
    }
    return result.toString();
  }
}