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

    @SerializedName("artworkUrl100")
    public String artworkUrl100;

    @SerializedName("collectionName")
    public String collectionName;

    @SerializedName("trackViewUrl")
    public String trackViewUrl;

    public Result(String artistName, String trackName, String artworkUrl100, String collectionName, String trackViewUrl) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.artworkUrl100 = artworkUrl100;
        this.collectionName = collectionName;
        this.trackViewUrl = trackViewUrl;
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

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl1000) {
        this.artworkUrl100 = artworkUrl1000;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }
}
