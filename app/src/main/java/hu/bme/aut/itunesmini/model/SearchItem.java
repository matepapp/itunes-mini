package hu.bme.aut.itunesmini.model;

import com.orm.SugarRecord;

/**
 * Created by matepapp on 2016. 12. 05..
 */

public class SearchItem extends SugarRecord {
    public enum Type {
        MOVIE("Movie"),
        PODCAST("Podcast"),
        MUSIC("Music"),
        MUSICVIDEO("Music Video"),
        AUDIOBOOK("Audiobook"),
        SHORTFILM("Shortfilm"),
        TVSHOW("TV Show"),
        SOFTWARE("Software"),
        EBOOK("Ebook"),
        ALL("All");

        private final String text;

        private Type(final String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }

        public static Type typeOf(String string) {
            for (Type type: Type.values())
                if (string.equals(type.toString()))
                    return type;

            return Type.ALL;
        }

        public static Type getByOrdinal(int ordinal) {
            Type ret = null;
            for (Type cat : Type.values()) {
                if (cat.ordinal() == ordinal) {
                    ret = cat;
                    break;
                }
            }
            return ret;
        }
    }

    public String expression;
    public Integer resultCount;
    public Type type;

    public SearchItem(String expression, Integer resultCount, Type type) {
        this.expression = expression;
        this.resultCount = resultCount;
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}