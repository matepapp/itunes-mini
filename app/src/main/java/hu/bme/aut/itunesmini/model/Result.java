package hu.bme.aut.itunesmini.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by matepapp on 2016. 12. 09..
 */

public class Result {
    @SerializedName("artistName")
    public String artistName;

    @SerializedName("trackName")
    public String trackName;

    @SerializedName("artworkUrl60")
    public String artworkUrl60;

    @SerializedName("collectionName")
    public String collectionName;

    public Result(String artistName, String trackName, String artworkUrl60, String collectionName) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.artworkUrl60 = artworkUrl60;
        this.collectionName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
