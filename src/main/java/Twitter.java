import java.util.*;

class Twitter {

  private record Tweet(int tweetId, int seqId) implements Comparable<Tweet> {
    @Override
    public int compareTo(Tweet o) {
      return Integer.compare(o.seqId, seqId);
    }
  }
  private final Map<Integer, Set<Integer>> userFollowees = new HashMap<>();
  private final Map<Integer, List<Tweet>> userTweets = new HashMap<>();

  private int seqId = 1;

  public Twitter() {

  }

  public void postTweet(int userId, int tweetId) {
    userTweets.computeIfAbsent(userId, __ -> new ArrayList<>()).add(new Tweet(tweetId, seqId++));
  }

  public List<Integer> getNewsFeed(int userId) {
    var followees = userFollowees.getOrDefault(userId, Collections.emptySet());
    int tweetsLimit = 10;
    var result = new ArrayList<Integer>(tweetsLimit);
    var q = new PriorityQueue<Tweet>(Comparator.reverseOrder());
    grabTweets(userId, tweetsLimit, q);
    for (var followeeId : followees) {
      grabTweets(followeeId, tweetsLimit, q);
    }
    while (!q.isEmpty()) {
      result.add(q.poll().tweetId);
    }
    return result.reversed();
  }

  private void grabTweets(int userId, int limit, PriorityQueue<Tweet> q) {
    var tweets = userTweets.getOrDefault(userId, Collections.emptyList());
    var tweetsIt = tweets.listIterator(tweets.size());
    for (int i = 0; i < limit && tweetsIt.hasPrevious(); ++i) {
      Tweet tweet = tweetsIt.previous();
      q.add(tweet);
      if (!q.isEmpty() && q.peek().seqId > tweet.seqId && q.size() >= limit) {
        break;
      }
    }
    while (q.size() > limit) {
      q.poll();
    }
  }

  public void follow(int followerId, int followeeId) {
    userFollowees.computeIfAbsent(followerId, __ -> new HashSet<>()).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    var followees = userFollowees.getOrDefault(followerId, Collections.emptySet());
    followees.remove(followeeId);
    if (followees.isEmpty()) {
      userFollowees.remove(followerId);
    }
  }
}
