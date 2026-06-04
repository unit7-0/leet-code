import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionSystem {
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Arrays.sort(products);
    int i = 0;
    var result = new ArrayList<List<String>>();
    for (int prefixLen = 1; prefixLen <= searchWord.length(); ++prefixLen) {
      var suggestions = new ArrayList<String>();
      int j = 0;
      while (suggestions.size() < 3 && i + j < products.length) {
        var product = products[i + j];
        j++;
        if (product.length() < prefixLen && suggestions.isEmpty()) {
          continue;
        } else if (product.length() < prefixLen) {
          break;
        }
        int comp = product.substring(0, prefixLen).compareTo(searchWord.substring(0, prefixLen));
        if (comp == 0) {
          suggestions.add(product);
        } else if (comp > 0) {
          if (suggestions.isEmpty()) {
            i = products.length;
          }
          break;
        }
      }
      i += Math.max(0, j - 3);
      result.add(suggestions);
    }
    return result;
  }
}
