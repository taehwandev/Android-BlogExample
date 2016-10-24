package tech.thdev.customlistviewsample.data;

import java.util.ArrayList;

/**
 * Created by tae-hwan on 10/24/16.
 */

public class GitHubUserResponse {

    public String message;
    public String documentation_url;

    public String total_count;
    public boolean incomplete_results;
    public ArrayList<GitHubItem> items;
}
