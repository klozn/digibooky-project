package com.vitsebeirenvantmaeskantje.digibookyproject.services.utility;

import java.util.regex.Pattern;

// CODEReview your most complex code
// this should be the most tested code too
// also: did you ask yourselves while writing this: is this really business logic of DigiBooky? Should this not already exist somewhere?
public class PatternMatcher {

    /**
     * Matches strings against glob like wildcard expressions where <code>?</code>
     * matches any single character and <code>*</code> matches any number of any
     * character. Multiple expressions can be separated with a colon (:). In this
     * case the expression matches if at least one part matches.
     */

    /**
     * Creates a new matcher with the given expression.
     *
     * @param expression wildcard expressions
     */
    private static Pattern patternMatcher(final String expression) {
        final String[] parts = expression.split("\\:");
        final StringBuilder regex = new StringBuilder(expression.length() * 2);
        boolean next = false;
        for (final String part : parts) {
            if (next) {
                regex.append('|');
            }
            regex.append('(').append(toRegex(part)).append(')');
            next = true;
        }
        return Pattern.compile(regex.toString());
    }

    private static CharSequence toRegex(final String expression) {
        final StringBuilder regex = new StringBuilder(expression.length() * 2);
        for (final char c : expression.toCharArray()) {
            switch (c) {
                case '?' -> regex.append(".");
                case '*' -> regex.append(".*");
                default -> regex.append(Pattern.quote(String.valueOf(c)));
            }
        }
        return regex;
    }

    /**
     * Matches the given string against the expressions of this matcher.
     *
     * @param wildcardInput     string to test
     * @param stringToCompareTo string to compare to
     * @return <code>true</code>, if the expression matches
     */
    public static boolean matches(String wildcardInput, String stringToCompareTo) {
        return patternMatcher(wildcardInput).matcher(stringToCompareTo).matches();
    }

}