package hu.bme.aut.itunesmini.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matepapp on 2016. 12. 09..
 */

public class ResultData {
    private Integer resultCount;
    private List<Result> results;

    public ResultData(Integer resultCount, List<Result> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public ResultData() {
        this.resultCount = 0;
        this.results = new ArrayList<>();
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
