import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyURLCodec {
  private final Map<String, String> shortToLong = new HashMap<>();
  private final Map<String, String> longToShort = new HashMap<>();
  private final String prefix = "https://tinyurl.com/";

  private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
  private final int idLength = 6;
  private final Random rand = new Random(System.currentTimeMillis());

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
    var storedId = longToShort.get(longUrl);
    if (storedId != null) {
      return shortToLong.get(storedId);
    }
    String id = generateId();
    String shortUrl = prefix + id;
    shortToLong.put(id, longUrl);
    longToShort.put(longUrl, id);
    return shortUrl;
  }

  private String generateId() {
    char[] id = new char[idLength];
    String result = null;
    do {
      for (int i = 0; i < idLength; ++i) {
        char c = alphabet[rand.nextInt(alphabet.length)];
        id[i] = c;
      }
      result = new String(id);
    } while (shortToLong.containsKey(result));
    return result;
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    if (shortUrl == null || shortUrl.length() < prefix.length()) {
      return null;
    }
    String id = shortUrl.substring(prefix.length());
    return shortToLong.get(id);
  }
}
