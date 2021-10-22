package com.vitsebeirenvantmaeskantje.digibookyproject.services.utility;

public class PatternMatcher {
    public static boolean patternMatcher(String wildcardInput, String stringToCompareTo, Character wildcard) {

        // If we reach at the end of both strings,
        // we are done
        if (wildcardInput.length() == 0 && stringToCompareTo.length() == 0)
            return true;

        // Make sure that the characters after '*'
        // are present in second string.
        // This function assumes that the first
        // string will not contain two consecutive '*'
        if (wildcardInput.length() > 1 && wildcardInput.charAt(0) == wildcard &&
                stringToCompareTo.length() == 0)
            return false;

        // If the first string contains '*',
        // or current characters of both strings match
        if ((wildcardInput.length() > 1 && wildcardInput.charAt(0) == wildcard) ||
                (wildcardInput.length() != 0 && stringToCompareTo.length() != 0 &&
                        wildcardInput.charAt(0) == stringToCompareTo.charAt(0)))
            return patternMatcher(wildcardInput.substring(1),
                    stringToCompareTo.substring(1), wildcard);

        // If there is *, then there are two possibilities
        // a) We consider current character of second string
        // b) We ignore current character of second string.
        if (wildcardInput.length() > 0 && wildcardInput.charAt(0) == wildcard)
            return patternMatcher(wildcardInput.substring(1), stringToCompareTo, wildcard) ||
                    patternMatcher(wildcardInput, stringToCompareTo.substring(1), wildcard);
        return false;
    }
}
