import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  public static void main(String[] args) {
    System.out.println(new GenerateParentheses().generateParenthesis(8).size());
  }

  public List<String> generateParenthesis(int n) {
    var result = new ArrayList<String>();
    generate(n, 0, result, new StringBuilder());
    return result;
  }

  public void generate(int openRemain, int closeBrackets, List<String> result, StringBuilder seq) {
    if (openRemain == 0) {
      result.add(seq.toString());
      return;
    }
    if (closeBrackets > 0) {
      seq.append(")");
      generate(openRemain - 1, closeBrackets - 1, result, seq);
      seq.delete(seq.length() - 1, seq.length());
    }
    if (openRemain - closeBrackets > 0) {
      seq.append("(");
      generate(openRemain, closeBrackets + 1, result, seq);
      seq.delete(seq.length() - 1, seq.length());
    }
  }
}