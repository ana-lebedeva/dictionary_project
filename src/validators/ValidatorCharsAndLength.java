package validators;

import enums.CharRange;
import enums.Status;
import repositories.PropertiesDictionary;

public class ValidatorCharsAndLength implements Validator {
    @Override
    public Status checkKey(PropertiesDictionary properties, String key) {
        if (properties.getLengthKey() == 0 || properties.getLengthKey() == key.length()) {
            char[] charsKey = key.toCharArray();
            if (properties.getCharRanges() == null || checkCharsKey(charsKey, properties.getCharRanges())) {
                return Status.OK;
            } else
                return Status.OUT_OF_BOUNDS;
        } else
            return Status.INVALID_LENGTH;
    }

    private boolean checkCharsKey(char[] chars, CharRange[] charRanges) {
        for (char c : chars) {
            boolean isHave = false;
            for (CharRange range : charRanges) {
                if (c >= range.getStart() && c <= range.getEnd())
                    isHave = true;
            }
            if (!isHave)
                return false;
        }
        return true;
    }

    @Override
    public Status checkValue(String value) {
        char[] charsValue = value.toCharArray();
        if (checkCharsValue(charsValue)) {
            return Status.OK;
        } else
            return Status.OUT_OF_BOUNDS;
    }

    private boolean checkCharsValue(char[] chars) {
        if (chars.length == 0)
            return false;
        for (char c : chars) {
            if (c < CharRange.CYRILLIC.getStart()
                    || c > CharRange.CYRILLIC.getEnd())
                return false;
        }
        return true;
    }
}
