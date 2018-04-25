package com.bigdata.app.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Query {

    public static void main(String args[]) {

        //query
        String query = "update tweetanalysis.sentiments set positive_sentiments = positive_sentiments + 1, "+
                "negative_sentiments = negative_sentiments + 2, neutral_sentiments = neutral_sentiments + 3 "+
                "where hashtag = 'hash1';"
        ;

        //Creating Cluster object
        Cluster cluster = Cluster.builder().addContactPoint("54.245.62.87").build();

        //Creating Session object
        Session session = cluster.connect("tweetanalysis");

        //Executing the query
        session.execute(query);

        System.out.println("Changes done");
    }
}
