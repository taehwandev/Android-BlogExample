package tech.thdev.customlistviewsample.data;

/**
 * Created by tae-hwan on 10/24/16.
 * <p>
 * GitHub api <a href="https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000">GitHub User search sample</a>
 */

public class GitHubItem {
    public String login;
    public String avatar_url;
    public String html_url;
    public float score;
}