package hu.bme.aut.itunesmini.model;

import com.orm.SugarRecord;

/**
 * Created by matepapp on 2016. 12. 05..
 */

public class SearchItem extends SugarRecord {
    public enum Type {
        all("All"),
        audiobook("Audiobook"),
        ebook("Ebook"),
        movie("Movie"),
        music("Music"),
        musicVideo("Music Video"),
        podcast("Podcast"),
        software("Software"),
        tvShow("TV Show");

        private final String text;

        private Type(final String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }

        public static int indexOf(String string) {
            for (Type type: Type.values())
                if (string.equals(type.toString()))
                    return type.ordinal();
            return 0;
        }

        public static Type getByOrdinal(int ordinal) {
            Type result = null;
            for (Type type : Type.values()) {
                if (type.ordinal() == ordinal) {
                    result = type;
                    break;
                }
            }
            return result;
        }
    }

    public String expression;
    public Integer resultCount;
    public Type type;
}