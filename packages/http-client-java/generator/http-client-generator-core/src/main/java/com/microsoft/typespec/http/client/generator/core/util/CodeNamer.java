// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.core.util;

import static com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer.getBasicLatinCharacter;

import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.atteo.evo.inflector.English;

public class CodeNamer {

    private static NamerFactory factory = new DefaultNamerFactory();

    private static final Pattern MERGE_UNDERSCORES = Pattern.compile("_{2,}");
    private static final Pattern CHARACTERS_TO_REPLACE_WITH_UNDERSCORE = Pattern.compile("[\\\\/.+ -]+");

    public static void setFactory(NamerFactory templateFactory) {
        factory = templateFactory;
    }

    public static ModelNamer getModelNamer() {
        return factory.getModelNamer();
    }

    private CodeNamer() {
    }

    public static String toCamelCase(String name) {
        return com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer.toCamelCase(name);
    }

    public static String toPascalCase(String name) {
        return com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer.toPascalCase(name);
    }

    public static String escapeXmlComment(String comment) {
        return com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer.escapeXmlComment(comment);
    }

    public static String escapeComment(String comment) {
        if (comment == null || comment.isEmpty()) {
            return comment;
        }

        StringBuilder sb = null;
        int prevStart = 0;
        int commentLength = comment.length();
        int replacementIndex;

        while ((replacementIndex = comment.indexOf("*/", prevStart)) != -1) {
            if (sb == null) {
                sb = newStringBuilder(commentLength);
            }

            sb.append(comment, prevStart, replacementIndex);
            sb.append("*&#47;");
            prevStart = replacementIndex + 2;
        }

        if (sb == null) {
            return comment;
        }

        sb.append(comment, prevStart, commentLength);
        return sb.toString();
    }

    /**
     * Escapes e.g. "domain\\username" (single backslash) to "domain{@code \}username".
     *
     * @param comment the Java comment
     * @return the escaped Java comment
     */
    public static String escapeIllegalUnicodeEscape(String comment) {
        if (comment == null || comment.isEmpty()) {
            return comment;
        }

        StringBuilder sb = null;
        int prevStart = 0;
        int prevProcessedStart = 0; // the start index of the last replacement, or 0 if no replacement has been made
        int commentLength = comment.length();
        int replacementIndex;

        while ((replacementIndex = comment.indexOf("\\u", prevStart)) != -1) {
            if (replacementIndex > 0 && comment.charAt(replacementIndex - 1) == '\\') {
                // skip escaped unicode escape
                prevStart = replacementIndex + 2;
                continue;
            } else if (replacementIndex + 6 <= commentLength) {
                boolean validUnicode = true;
                for (int unicodeIndex = replacementIndex + 2; unicodeIndex < replacementIndex + 6; unicodeIndex++) {
                    char ch = comment.charAt(unicodeIndex);
                    if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'F') && (ch < 'a' || ch > 'f')) {
                        // not a hexadecimal
                        validUnicode = false;
                        break;
                    }
                }

                if (validUnicode) {
                    // skip valid unicode escape
                    prevStart = replacementIndex + 6;
                    continue;
                }
            }

            if (sb == null) {
                sb = newStringBuilder(commentLength);
            }

            sb.append(comment, prevProcessedStart, replacementIndex);
            sb.append("{@code \\}u");
            prevStart = replacementIndex + 2;
            prevProcessedStart = prevStart;
        }

        if (sb == null) {
            return comment;
        }

        sb.append(comment, prevProcessedStart, commentLength);
        return sb.toString();
    }

    public static String removeInvalidCharacters(String name) {
        return com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer.getValidName(name,
            c -> c == '_' || c == '-');
    }

    public static String getPropertyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return com.microsoft.typespec.http.client.generator.core.preprocessor.namer.CodeNamer
            .getEscapedReservedName(toCamelCase(removeInvalidCharacters(name)), "Property");
    }

    public static String getPlural(String name) {
        if (name != null && !name.isEmpty() && !name.endsWith("s") && !name.endsWith("S")) {
            name = English.plural(name);
        }
        return name;
    }

    public static String getEnumMemberName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        // trim leading and trailing '_'
        name = trimUnderscores(name);

        String result = removeInvalidCharacters(CHARACTERS_TO_REPLACE_WITH_UNDERSCORE.matcher(name).replaceAll("_"));

        if (result.startsWith("_")) {
            // some "_" remains after "removeInvalidCharacters", e.g. "_100_Gift_Card"
            // trim it again, and call "removeInvalidCharacters" on it,
            // so that the "100" would be transformed to "OneZeroZero"
            result = trimUnderscores(result);
            result = removeInvalidCharacters(result);
        }

        // merge underscores
        result = MERGE_UNDERSCORES.matcher(result).replaceAll("_");  // merge multiple underlines

        // convert PascalCase to UPPER_CASE
        Function<Character, Boolean> isUpper = c -> c >= 'A' && c <= 'Z';
        Function<Character, Boolean> isLower = c -> c >= 'a' && c <= 'z';
        for (int i = 1; i < result.length() - 1; i++) {
            if (isUpper.apply(result.charAt(i))) {
                if (result.charAt(i - 1) != '_' && isLower.apply(result.charAt(i - 1))) {
                    result = result.substring(0, i) + "_" + result.substring(i);
                }
            }
        }

        if (result.startsWith("_") || result.endsWith("_")) {
            if (!result.chars().allMatch(c -> c == (int) '_')) {
                // some char is not '_', trim it

                StringBuilder sb = new StringBuilder(result);
                while (sb.length() > 0 && sb.charAt(0) == '_') {
                    sb.deleteCharAt(0);
                }
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '_') {
                    sb.setLength(sb.length() - 1);
                }
                result = sb.toString();
            } else {
                // all char is '_', then transform some '_' to
                String basicLatinCharacterReplacement = getBasicLatinCharacter(name.charAt(0));
                if (result.startsWith("_") && basicLatinCharacterReplacement != null) {
                    result = basicLatinCharacterReplacement + result.substring(1);

                    basicLatinCharacterReplacement = getBasicLatinCharacter(name.charAt(name.length() - 1));
                    if (result.endsWith("_") && basicLatinCharacterReplacement != null) {
                        result = result.substring(0, result.length() - 1) + basicLatinCharacterReplacement;
                    }
                }
            }
        }

        if (result.equals("_")) {
            // "_" cannot be used as variable name
            result = "UNDERSCORE";
        }

        return result.toUpperCase();
    }

    // the ServiceInterface local variable and the ManagementClient local variable
    private static final Set<String> RESERVED_CLIENT_METHOD_PARAMETER_NAME = Set.of("service", "client");

    public static String getEscapedReservedClientMethodParameterName(String name) {
        if (RESERVED_CLIENT_METHOD_PARAMETER_NAME.contains(name)) {
            name += "Param";
        }
        return name;
    }

    public static String removeSpaceCharacters(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = null;
        int prevStart = 0;
        int strLength = str.length();

        for (int i = 0; i < strLength; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                if (sb == null) {
                    sb = new StringBuilder(strLength);
                }

                if (prevStart != i) {
                    sb.append(str, prevStart, i);
                }

                prevStart = i + 1;
            }
        }

        if (sb == null) {
            return str;
        }

        sb.append(str, prevStart, strLength);
        return sb.toString();
    }

    private static String trimUnderscores(String name) {
        // trim leading and trailing '_'
        if ((name.startsWith("_") || name.endsWith("_")) && !name.chars().allMatch(c -> c == '_')) {
            StringBuilder sb = new StringBuilder(name);
            while (sb.length() > 0 && sb.charAt(0) == '_') {
                sb.deleteCharAt(0);
            }
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '_') {
                sb.setLength(sb.length() - 1);
            }
            name = sb.toString();
        }
        return name;
    }

    private static StringBuilder newStringBuilder(int length) {
        // Add enough overhead to account for 1/8 of the string to be replaced.
        return new StringBuilder(length + 3 * (length / 8));
    }
}
