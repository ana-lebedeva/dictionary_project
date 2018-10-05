package validators;

import enums.CharRange;
import enums.CharRangeImpl;
import enums.Message;
import enums.Status;
import repositories.PropertiesDictionary;

public class ValidatorCharsAndLength implements Validator {
    @Override
    public Status checkKey(PropertiesDictionary properties, String key) {
        if (properties.getLengthKey() == 0 || properties.getLengthKey() == key.length()) {
            char[] charsKey = key.toCharArray();
            if (properties.getCharRanges() == null || checkCharsKey(charsKey, properties.getCharRanges())) {
                return Status.OK;
            } else{
                Status ranges = Status.OUT_OF_BOUNDS;
                ranges.setCharRanges(properties.getCharRanges());
                ranges.setFieldError(Message.KEY.getDescription());
                return ranges;
            }
        } else {
            Status length = Status.INVALID_LENGTH;
            length.setLength(properties.getLengthKey());
            return length;
        }
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
        } else{
            Status ranges = Status.OUT_OF_BOUNDS;
            ranges.setCharRanges(new CharRangeImpl[]{CharRangeImpl.CYRILLIC});
            ranges.setFieldError(Message.VALUE.getDescription());
            return ranges;
        }
    }

    private boolean checkCharsValue(char[] chars) {
        if (chars.length == 0)
            return false;
        for (char c : chars) {
            if (c < CharRangeImpl.CYRILLIC.getStart()
                    || c > CharRangeImpl.CYRILLIC.getEnd())
                return false;
        }
        return true;
    }
}
